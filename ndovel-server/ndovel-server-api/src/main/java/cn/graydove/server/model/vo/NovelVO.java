package cn.graydove.server.model.vo;

import cn.graydove.common.model.BaseApi;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class NovelVO extends BaseApi {

    private static final long serialVersionUID = 6919515597453088200L;

    private String name;

    private String introduce;

    private AuthorVO author;

    private List<TypeVO> type;

    private String cover;

    private Long firstChapterId;

    private Long LastChapterId;
}
