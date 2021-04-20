package cn.graydove.ndovel.spider.model.dto;

import cn.graydove.ndovel.server.api.model.request.NovelPutRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class NovelPutDTO extends NovelPutRequest {

    private static final long serialVersionUID = 6567100409348380139L;

    private String uuid;
}
