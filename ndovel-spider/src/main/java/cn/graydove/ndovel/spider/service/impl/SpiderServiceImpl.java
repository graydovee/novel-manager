package cn.graydove.ndovel.spider.service.impl;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.server.api.BookWriteFacade;
import cn.graydove.server.enums.BookStatusEnum;
import cn.graydove.server.model.request.BookRequest;
import cn.graydove.server.model.request.ChapterRequest;
import cn.graydove.server.model.vo.ChapterVO;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author graydove
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    @DubboReference(lazy = true)
    private BookWriteFacade bookWriteFacade;

    @Override
    public void spider(BookDTO bookDTO) {
        BookRequest bookRequest = new BookRequest();
        BeanUtils.copyProperties(bookDTO, bookRequest);
        bookRequest.setStatus(BookStatusEnum.RELEASE);
        Long bookId = bookWriteFacade.createBook(bookRequest);

        ChapterRequest chapterRequest = new ChapterRequest();
        chapterRequest.setBookId(bookId);
        chapterRequest.setTitle("测试标题");
        chapterRequest.setContent("11111111111111111111111112222222222222222222222222222222222222223333333333333333333");
        bookWriteFacade.appendChapter(chapterRequest);
    }

}
