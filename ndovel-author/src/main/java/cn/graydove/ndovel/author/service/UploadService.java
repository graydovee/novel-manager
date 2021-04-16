package cn.graydove.ndovel.author.service;

import cn.graydove.ndovel.common.response.Response;

/**
 * @author graydove
 */
public interface UploadService {

    Response<String> upload(String url, Long bookId);
}
