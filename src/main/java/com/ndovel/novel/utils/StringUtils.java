package com.ndovel.novel.utils;


import org.apache.commons.text.StringEscapeUtils;

public class StringUtils {

    private StringUtils() {

    }

    public static boolean isEmpty(String str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(String str) {
        return str != null && !"".equals(str);
    }

    public static String formatContent(String content) {
        content = content
                .replaceAll("<[^>]*>", "  \n  ");
        content = StringEscapeUtils.unescapeHtml4(content);
        content = content
                .replace("　", " ")
                .replaceAll("\\t", "    ")
                .replaceAll(" {2,}", "  ")
                .replaceAll("\\r", "\n")
                .replaceAll(" +\\n", "\n")
                .replaceAll("\\n+", "\n\n");
        int start = -1;
        int end = content.length();
        for (int i = 0; i < content.length(); ++i) {
            if (content.charAt(i) == '\n') {
                start = i;
            } else {
                break;
            }
        }
        for (int i = content.length() - 1; i >= start; --i) {
            if (content.charAt(i) == '\n' || content.charAt(i)==' ') {
                end = i;
            } else {
                break;
            }
        }
        if (start < end && (start >= 0 || end < content.length())) {
            content = content.substring(start + 1, end);
        }
        return content;
    }
}
