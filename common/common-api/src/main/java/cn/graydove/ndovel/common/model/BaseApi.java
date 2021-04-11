package cn.graydove.ndovel.common.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author graydove
 */
@Data
public abstract class BaseApi implements Serializable {

    private static final long serialVersionUID = -1773992285471572932L;

    private Long id;

    private LocalDateTime updateTime;

    private LocalDateTime createTime;
}
