package cn.graydove.ndovel.common.query;

import cn.hutool.core.util.StrUtil;
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

    private String sortField;

    private Boolean desc;

    public Pageable toPageable() {
        return toPageable(getSort());
    }

    public Pageable toPageable(Sort sort) {
        int pageNo = this.pageNo == null || this.pageNo < 1 ? DEFAULT_PAGE_NO : this.pageNo - 1;
        int pageSize = this.pageSize == null ? DEFAULT_PAGE_SIZE : this.pageSize;
        return PageRequest.of(pageNo, pageSize, sort);
    }

    public Sort getSort() {
        if (StrUtil.isBlank(this.sortField)) {
            return Sort.unsorted();
        }
        Sort sort = Sort.by(this.sortField);
        if (Boolean.TRUE.equals(this.desc)) {
            return sort.descending();
        }
        return sort;
    }
}
