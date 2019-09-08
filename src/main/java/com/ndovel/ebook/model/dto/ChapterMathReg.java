package com.ndovel.ebook.model.dto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class ChapterMathReg {

    private String titleReg;
    private String contentReg;
    private String nextPageReg;
}
