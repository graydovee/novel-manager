package cn.graydove.ndovel.reader.controller;

import cn.graydove.ndovel.common.response.Paging;
import cn.graydove.ndovel.common.utils.IpUtils;
import cn.graydove.ndovel.logger.rocketmq.NovelProducer;
import cn.graydove.ndovel.reader.model.dto.BookPageDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterIdDTO;
import cn.graydove.ndovel.reader.model.dto.ChapterPageDTO;
import cn.graydove.ndovel.reader.model.dto.NovelSearchDTO;
import cn.graydove.ndovel.reader.service.BookService;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.server.api.model.vo.NovelVO;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author graydove
 */
@Slf4j
@RestController
@AllArgsConstructor
public class BookController {

    private BookService bookService;

    private NovelProducer novelProducer;

    @GetMapping("/chapter")
    public ChapterVO chapter(@Valid ChapterIdDTO chapterIdDTO, HttpServletRequest request) {
        ChapterVO chapter = bookService.findChapter(chapterIdDTO);
        if (chapter != null) {
            novelProducer.logVisit(IpUtils.getIp(request), chapter.getBookId(), chapter.getId());
        }
        return chapter;
    }

    @GetMapping("/page_chapter")
    public Paging<ChapterVO> chapter(@Valid ChapterPageDTO chapterPageDTO) {
        return bookService.pageChapter(chapterPageDTO);
    }

    @GetMapping("/page_book")
    public Paging<BookVO> book(@Valid BookPageDTO bookPageDTO) {
        return bookService.pageBook(bookPageDTO);
    }

    @GetMapping("/search_novel")
    public Paging<NovelVO> search(@Valid NovelSearchDTO novelSearchDTO) {
        if (StrUtil.isBlank(novelSearchDTO.getSortField())) {
            novelSearchDTO.setSortField("visit");
            if (null != novelSearchDTO.getDesc()) {
                novelSearchDTO.setDesc(true);
            }
        }
        return bookService.searchNovel(novelSearchDTO);
    }
}
