package com.ndovel.ebook.utils;

public class StringUtils {
    private StringUtils(){

    }

    public static Boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static String formatContent(String content){
        return content.replaceAll("</?br\\s?/?>","\n")
                .replaceAll("&nbsp;"," ")
                .replaceAll("<[/]?p>","\n")
                .replaceAll("<a[^<]*?</a>","")
                .replaceAll("<script[^<]*?</script>","")
                .replaceAll("　"," ")
                .replaceAll("\\t","    ")
                .replaceAll(" {2,}","  ")
                .replaceAll("\\r","\n")
                .replaceAll(" +\\n","\n")
                .replaceAll("\\n+","\n\n");
    }
}
