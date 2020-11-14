package com.ndovel.novel.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchResult extends TempBook{
    private String url;

    public String getTitle() {
        return getBookName();
    }

    public String getAuthor() {
        return getAuthorName();
    }

    public String getUrl() {
        return url;
    }
}
