package cn.graydove.ndovel.author.service.impl;

import cn.graydove.ndovel.author.service.AssertService;
import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.utils.Assert;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.model.request.BookIdRequest;
import cn.graydove.ndovel.server.api.model.request.ChapterIdRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.server.api.model.vo.ChapterVO;
import cn.graydove.ndovel.user.api.enums.RoleEnum;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.hutool.core.util.ObjectUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.function.Function;

/**
 * @author graydove
 */
@Service
public class AssertServiceImpl implements AssertService {

    @DubboReference
    private BookReadFacade bookReadFacade;

    @Override
    public Boolean isBookOwner(Long id) {
        return isAdmin() || assertBook(id, bookVO -> ObjectUtil.notEqual(bookVO.getAuthor().getUserId(), UserContext.getUserId()));
    }

    @Override
    public Boolean isChapterOwner(Long id) {
        return isAdmin() || assertChapter(id, chapterVO -> isBookOwner(chapterVO.getBookId()));
    }

    @Override
    public void assertBookIsOwner(Long id) {
        Assert.assertTrue(isBookOwner(id), () -> new TaskException("无权限"));
    }

    @Override
    public void assertChapterIsOwner(Long id) {
        Assert.assertTrue(isChapterOwner(id), () -> new TaskException("无权限"));
    }

    @Override
    public Boolean assertBook(Long bookId, Function<BookVO, Boolean> function) {
        BookIdRequest bookIdRequest = new BookIdRequest();
        bookIdRequest.setBookId(bookId);
        BookVO book = bookReadFacade.findBook(bookIdRequest);
        return function.apply(book);
    }

    @Override
    public Boolean assertChapter(Long chapterId, Function<ChapterVO, Boolean> function) {
        ChapterIdRequest chapterIdRequest = new ChapterIdRequest();
        chapterIdRequest.setChapterId(chapterId);
        ChapterVO chapter = bookReadFacade.findChapter(chapterIdRequest);
        return function.apply(chapter);
    }


    private boolean isAdmin() {
        return Optional.ofNullable(UserContext.getUser())
                .map(UserVO::getRoles)
                .map(roles -> roles.contains(RoleEnum.ROLE_ADMIN.name()))
                .orElse(Boolean.FALSE);
    }
}
