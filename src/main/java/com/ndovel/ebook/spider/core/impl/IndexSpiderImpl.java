package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.model.dto.*;
import com.ndovel.ebook.spider.core.AbstractSpider;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.util.UrlUtils;
import com.ndovel.ebook.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 爬取章节目录
 */
public class IndexSpiderImpl extends AbstractSpider implements IndexSpider {

    private TempBook make(String url){
        TempBook book = new TempBook();

        Document document = Jsoup.parse(sendHttpGetRequest(url));

        //解析章节目录
        List<TempChapter> index = new ArrayList<>();
        Optional.ofNullable(document.select("dd>a[href]"))
                .ifPresent((list)-> list.forEach((a)->{

                    if (a != null && !StringUtils.isEmpty(a.attr("href")))  {
                        String u = UrlUtils.jump(url, a.attr("href"));

                        TempChapter tempChapter = new TempChapter();
                        tempChapter.setUrl(u);
                        tempChapter.setTitle(a.text());
                        index.add(tempChapter);
                    }
                }));
        book.setChapters(index);

        //解析书名
        Elements select = document.select("#info>h1");
        if (select.size() > 0) {
            book.setBookName(select.get(0).text());
        }
        select = document.select("#info>p");
        if (select.size() > 0) {
            select.forEach((each)->{
                String text = each.text();
                text = text.replace("：", ":");
                if (text.indexOf(":") > 0){
                    String[] split = text.split(":");
                    if(split[0].contains("作") && split[0].contains("者")){
                        text = split[1];
                        book.setAuthorName(text);
                    }
                }
            });
        }
        select = document.select("#fmimg>img");
        if (select.size() > 0) {
            String src = select.get(0).attr("src");
            book.setCoverUrl(UrlUtils.jump(url, src));
        }
        select = document.select("#intro");
        if (select.size() > 0) {
            book.setIntroduce(select.get(0).text());
        }
        return book;
    }

    @Deprecated
    @Override
    public List<TempChapter> getIndex(String url) {
        return getTempBook(url).getChapters();
    }

    @Override
    public TempBook getTempBook(String url) {
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        return make(url);
    }


}
