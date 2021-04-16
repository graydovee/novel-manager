package cn.graydove.ndovel.author.controller;

import cn.graydove.ndovel.author.model.dto.UploadDTO;
import cn.graydove.ndovel.author.service.UploadService;
import cn.graydove.ndovel.common.oss.OssTemplate;
import cn.graydove.ndovel.common.response.Response;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author graydove
 */
@RestController
@AllArgsConstructor
public class CoverController {

    private UploadService uploadService;

    @PostMapping("/cover_url")
    public Response<String> upload(@RequestBody @Valid UploadDTO uploadDTO) {
        return uploadService.upload(uploadDTO.getUrl(), uploadDTO.getBookId());
    }
}
