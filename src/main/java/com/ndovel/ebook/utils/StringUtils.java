package com.ndovel.ebook.utils;

public class StringUtils {
    private StringUtils(){

    }

    public static boolean isEmpty(String str){
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str){
        return str != null && !"".equals(str);
    }

    public static String formatContent(String content){
        return content.replaceAll("</?br\\s?/?>","\n")
                .replace("&nbsp;"," ")
                .replaceAll("<[/]?p>","\n")
                .replaceAll("<a[^<]*?</a>","")
                .replaceAll("<script[^<]*?</script>","")
                .replace("　"," ")
                .replaceAll("\\t","    ")
                .replaceAll(" {2,}","  ")
                .replaceAll("\\r","\n")
                .replaceAll(" +\\n","\n")
                .replaceAll("\\n+","\n\n");
    }
}
