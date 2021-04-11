package cn.graydove.ndovel.server.model.vo;

import cn.graydove.ndovel.common.model.BaseApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author graydove
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorVO extends BaseApi {

    private static final long serialVersionUID = 4303685617484700470L;

    private String name;
}
