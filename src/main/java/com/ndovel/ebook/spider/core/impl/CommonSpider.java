package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.model.dto.MatchRexDTO;
import com.ndovel.ebook.spider.core.AbstractSpider;
import com.ndovel.ebook.utils.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonSpider extends AbstractSpider {
    private MatchRexDTO matchRex;

    public CommonSpider(Integer bookId, String firstPageUrl, MatchRexDTO matchRex) {
        super(bookId, firstPageUrl);
        this.matchRex = matchRex;
    }

    @Override
    protected String getContentFormCode(String code) {
        String content =  match(code, matchRex.getContentRex());
        if (content == null)
            return null;
        return StringUtils.formatContent(content);
    }

    @Override
    protected String getTitleFormCode(String code) {
        return match(code, matchRex.getTitleRex());
    }

    @Override
    protected String getNextPageFormCode(String code) {
        return match(code, matchRex.getNextPageRex());
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