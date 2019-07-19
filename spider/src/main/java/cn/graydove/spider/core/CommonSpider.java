package cn.graydove.spider.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class CommonSpider extends AbstractSpider {
    protected CommonSpider(String path, String page) {
        super(path, page);
    }

    @Override
    protected String getConetntFormCode(String code) {
        return match(code, "<div id=\"content\">([\\s\\S]*)</div>");
    }

    @Override
    protected String getTitleFormCode(String code) {
        return match(code, "<h1>(.*)</h1>");
    }

    @Override
    protected String getNextpageFormCode(String code) {
        return match(code, "<a[^<]*href=\"([^<]*.html)\"[^<]*>下一章</a>");
    }

    private String match(String code, String rule) {
        Pattern nextPagePattern = Pattern.compile(rule);
        Matcher nextPageMarcher = nextPagePattern.matcher(code);
        if (nextPageMarcher.find()) {
            return nextPageMarcher.group(1);
        }
        return null;
    }
}
