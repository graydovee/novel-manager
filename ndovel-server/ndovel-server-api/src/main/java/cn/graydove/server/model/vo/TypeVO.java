package cn.graydove.server.model.vo;

import cn.graydove.common.model.BaseApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class TypeVO extends BaseApi {

    private static final long serialVersionUID = -2702974365853687620L;

    private String name;
}