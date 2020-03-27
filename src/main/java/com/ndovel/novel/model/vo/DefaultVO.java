package com.ndovel.novel.model.vo;

import com.ndovel.novel.model.vo.base.BaseVO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@Data
@EqualsAndHashCode(callSuper = true)
public class DefaultVO extends BaseVO<Object> {
    public DefaultVO(HttpStatus status, Object data) {
        super(status, data);
    }
}
