package cn.graydove.common.query;

import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @author graydove
 */
@Data
public class PageQuery implements Serializable {

    private static final long serialVersionUID = -8096688588898561475L;

    private Integer pageNo = 1;

    private Integer pageSize = 10;

    public Pageable toPageable() {
        return PageRequest.of(pageNo, pageSize);
    }

    public Pageable toPageable(Sort sort) {
        return PageRequest.of(pageNo, pageSize, sort);
    }
}
