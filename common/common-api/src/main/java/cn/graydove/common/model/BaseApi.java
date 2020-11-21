package cn.graydove.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public abstract class BaseApi implements Serializable {

    private static final long serialVersionUID = -1773992285471572932L;

    private Long id;

    private Date updateTime;

    private Date createTime;
}
