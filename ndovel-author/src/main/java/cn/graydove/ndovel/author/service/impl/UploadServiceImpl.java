package cn.graydove.ndovel.author.service.impl;

import cn.graydove.ndovel.author.model.dto.BookDTO;
import cn.graydove.ndovel.author.service.AssertService;
import cn.graydove.ndovel.author.service.BookService;
import cn.graydove.ndovel.author.service.UploadService;
import cn.graydove.ndovel.author.util.CoverUtil;
import cn.graydove.ndovel.client.UserContext;
import cn.graydove.ndovel.common.exception.TaskException;
import cn.graydove.ndovel.common.oss.OssTemplate;
import cn.graydove.ndovel.common.response.Response;
import cn.graydove.ndovel.server.api.facade.BookReadFacade;
import cn.graydove.ndovel.server.api.facade.BookWriteFacade;
import cn.graydove.ndovel.server.api.model.request.BookIdRequest;
import cn.graydove.ndovel.server.api.model.request.UpdateBookRequest;
import cn.graydove.ndovel.server.api.model.vo.BookVO;
import cn.graydove.ndovel.user.api.model.vo.UserVO;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

/**
 * @author graydove
 */
@Service
public class UploadServiceImpl implements UploadService {

    private OssTemplate ossTemplate;

    private AssertService assertService;

    @DubboReference
    private BookWriteFacade bookWriteFacade;

    public UploadServiceImpl(OssTemplate ossTemplate, AssertService assertService) {
        this.ossTemplate = ossTemplate;
        this.assertService = assertService;
    }

    @Override
    public Response<String> upload(String url, Long bookId) {
        assertService.assertBookIsOwner(bookId);
        Response<String> coverUrl = ossTemplate.upload(url, CoverUtil.toCoverUri(bookId));
        if (coverUrl.getSuccess() && StrUtil.isNotBlank(coverUrl.getResult())) {
            UpdateBookRequest request = new UpdateBookRequest();
            request.setId(bookId);
            request.setCover(coverUrl.getResult());
            bookWriteFacade.updateBook(request);
        }
        return coverUrl;
    }
}
