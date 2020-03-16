package com.ndovel.ebook.model.dto;

import lombok.Data;

@Data
public class SpiderIndex {
    private String bookName;

    private String authorName;

    private String firstChapterUrl;

    private String coverUrl;

    private String introduce;

    private Integer matchRexId;

}
