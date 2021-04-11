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

    private static final int DEFAULT_PAGE_NO = 0;

    private static final int DEFAULT_PAGE_SIZE = 10;

    private static final long serialVersionUID = -8096688588898561475L;

    private Integer pageNo;

    private Integer pageSize;

    public Pageable toPageable() {
        return toPageable(Sort.unsorted());
    }

    public Pageable toPageable(Sort sort) {
        int pageNo = this.pageNo == null || this.pageNo < 1 ? DEFAULT_PAGE_NO : this.pageNo - 1;
        int pageSize = this.pageSize == null ? DEFAULT_PAGE_SIZE : this.pageSize;
        return PageRequest.of(pageNo, pageSize, sort);
    }
}
