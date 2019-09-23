package com.ndovel.ebook.spider.core;


import com.ndovel.ebook.model.dto.ChapterDTO;
import com.ndovel.ebook.model.dto.ContentDTO;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.spider.util.UrlUtils;
import com.ndovel.ebook.utils.StringUtils;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractSpider implements NovelSpider {
    protected String url;
    protected boolean urlUes = false;

    protected Integer bookId;
    protected ChapterDTO chapter;
    protected ContentDTO content;

    private String validUrl = null;

    protected AbstractSpider(Integer bookId, String firstPageUrl) {
        this.bookId = bookId;

        this.url = UrlUtils.urlFormat(firstPageUrl);
    }

    private String getFullPath() {
        return UrlUtils.urlFormat(url);
    }

    protected String getHtmlCode() {
        return HttpClientUtils.get(getFullPath());
    }



    protected abstract String getContentFormCode(String code);

    protected abstract String getTitleFormCode(String code);

    protected abstract String getNextPageFormCode(String code);

    @Override
    public boolean hasNext() {
        return !StringUtils.isEmpty(url) && !isUrlUes();
    }

    public void setNewUrl(String url){
        this.url = url;
        urlUes = false;
    }

    @Override
    public void run() {
        this.content = null;
        this.chapter = null;
        if(this.urlUes){
            return;
        }

        this.urlUes = true;

        //获取页面源代码
        String code = getHtmlCode();

        if (code == null) {
            return;
        }

        //获取正文内容
        String content = getContentFormCode(code);

        if (StringUtils.isEmpty(content)) {
            return;
        }

        //重新初始化
        this.content = new ContentDTO();
        this.chapter = new ChapterDTO();
        this.chapter.setBookId(bookId);


        this.content.setInfo(content);

        this.chapter.setTitle(getTitleFormCode(code));

        this.validUrl = url;

        String nextPage = getNextPageFormCode(code);

        if(!StringUtils.isEmpty(nextPage)){
            setNewUrl(UrlUtils.jump(url, nextPage));
        }


    }

    @Override
    public void update() {
        setNewUrl(validUrl);
    }

}
