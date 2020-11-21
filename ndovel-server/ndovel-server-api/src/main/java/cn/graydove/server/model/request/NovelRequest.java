package cn.graydove.server.model.request;

import cn.graydove.common.model.BaseApi;
import cn.graydove.server.enums.NovelStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class NovelRequest extends BaseApi {

    private static final long serialVersionUID = 8212675880709769415L;

    private String name;

    private String introduce;

    private String author;

    private String cover;

    private List<String> type;

    private NovelStatusEnum status;
}