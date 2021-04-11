package cn.graydove.ndovel.spider.service.impl;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.service.SpiderService;
import cn.graydove.ndovel.server.api.BookWriteFacade;
import cn.graydove.ndovel.server.enums.BookStatusEnum;
import cn.graydove.ndovel.server.model.request.BookRequest;
import cn.graydove.ndovel.server.model.request.ChapterRequest;
import cn.hutool.core.bean.BeanUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author graydove
 */
@Service
public class SpiderServiceImpl implements SpiderService {

    @DubboReference
    private BookWriteFacade bookWriteFacade;

    @Override
    public void spider(BookDTO bookDTO) {
        BookRequest bookRequest = BeanUtil.toBean(bookDTO, BookRequest.class);
        bookRequest.setStatus(BookStatusEnum.RELEASE);
        Long bookId = bookWriteFacade.createBook(bookRequest);

        for (int i=0; i < 25; ++i) {
            ChapterRequest chapterRequest = new ChapterRequest();
            chapterRequest.setBookId(bookId);
            chapterRequest.setTitle("测试标题" + i);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(i);
            for (int j=0;j<10;++j) {
                stringBuilder.append(stringBuilder);
            }
            chapterRequest.setContent(stringBuilder.toString());
            bookWriteFacade.appendChapter(chapterRequest);
        }
    }

}
