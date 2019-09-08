package com.ndovel.ebook.utils;

public class StringUtils {
    private StringUtils(){

    }

    public static String formatContent(String content){
        return content.replaceAll("</?br\\s?/?>","\n")
                .replaceAll("&nbsp;"," ")
                .replaceAll("　"," ")
                .replaceAll("\\t","    ")
                .replaceAll(" {2,}","  ")
                .replaceAll(" +\\n","\n")
                .replaceAll("\\n+","\n\n");
    }
}
