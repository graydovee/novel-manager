package com.ndovel.ebook.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class TempBook {

    private String coverUrl;

    private String authorName;

    private String bookName;

    private String introduce;

    private List<TempChapter> chapters;
}
