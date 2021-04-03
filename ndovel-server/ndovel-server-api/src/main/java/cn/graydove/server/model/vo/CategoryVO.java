package cn.graydove.server.model.vo;

import cn.graydove.common.model.BaseApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryVO extends BaseApi {

    private static final long serialVersionUID = -2702974365853687620L;

    private String name;
}
