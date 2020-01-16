package com.ndovel.ebook.model.vo;

import com.ndovel.ebook.model.vo.base.BaseVO;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.List;

public class PageVO extends BaseVO<PageVO.PageDTO> {

    public<T> PageVO(HttpStatus status, Page<T> data) {
        super(status, new PageDTO<>(data));
    }

    @Data
    static class PageDTO<T>{

        private List<T> content;

        private Integer totalPages;

        private Long totalElements;

        private Integer number;

        private Integer size;

        private Integer numberOfElements;


        PageDTO(Page<T> page) {
            this.totalElements = page.getTotalElements();
            this.totalPages = page.getTotalPages();
            this.content = page.getContent();
            this.size = page.getSize();
            this.numberOfElements = page.getNumberOfElements();
            this.number = page.getNumber();
        }
    }
}
