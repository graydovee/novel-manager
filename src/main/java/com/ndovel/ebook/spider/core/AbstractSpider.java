package com.ndovel.ebook.spider.core;


import com.ndovel.ebook.spider.NovelSpider;
import com.ndovel.ebook.spider.connect.HttpClientUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractSpider implements NovelSpider {
    protected static String url;
    protected static String encode = "utf-8";

    private String bookId;
    private String nextPage;
    private String title;
    private String content;

    protected AbstractSpider(String bookId, String page) {
        this.bookId = bookId;
        this.nextPage = page;
    }

    private String getFullPath() {

        if (bookId == null || nextPage == null)
            return null;

        if (bookId.startsWith("/")) {
            bookId = bookId.substring(1);
        }

        if (bookId.endsWith("/")) {
            bookId = bookId.substring(0, bookId.length() - 1);
        }

        if (nextPage.lastIndexOf('/') > 0)
            nextPage = nextPage.substring(nextPage.lastIndexOf('/'));

        return url + bookId + "/" + nextPage;
    }

    protected String getHtmlCode() {
        return HttpClientUtil.get(getFullPath(), encode);
    }


    @Override
    public boolean hasNext() {
        return nextPage != null;
    }

    @Override
    public String getNextPage() {
        return this.nextPage;
    }

    protected abstract String getConetntFormCode(String code);

    protected abstract String getTitleFormCode(String code);

    protected abstract String getNextpageFormCode(String code);

    @Override
    public void nextPage() {

        if (nextPage == null) {
            this.content = null;
            this.title = null;
            return;
        }
        String code = getHtmlCode();
        if (code == null) {
            return;
        }
        this.content = getConetntFormCode(code);

        this.title = getTitleFormCode(code);

        this.nextPage = getNextpageFormCode(code);

        if(this.content == null){
            this.nextPage = null;
        }
    }
}
