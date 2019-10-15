package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.model.dto.*;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.spider.util.UrlUtils;
import com.ndovel.ebook.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 爬取章节目录
 */
public class IndexSpiderImpl implements IndexSpider {

    private List<TempChapter> make(String url){
        List<TempChapter> index = new ArrayList<>();

        Document document = Jsoup.parse(HttpClientUtils.get(url));

        Optional.ofNullable(document.getElementById("list"))
                .map(element -> element.getElementsByTag("dl"))
                .map(elements -> elements.get(0))
                .map(Element::getAllElements)
                .ifPresent(eles -> {
                    for (Element ele : eles){
                        if (ele.tag().getName().equals("dd")) {
                            Optional.of(ele).map(element -> {
                                    Elements a = element.getElementsByTag("a");
                                    return a.size()>0 ? a : null;
                                })
                                .map(elements -> elements.get(0))
                                .ifPresent(element -> {
                                    if (!StringUtils.isEmpty(element.attr("href")))  {
                                        String u = UrlUtils.jump(url, element.attr("href"));

                                        TempChapter tempChapter = new TempChapter();
                                        tempChapter.setUrl(u);
                                        tempChapter.setTitle(element.text());
                                        index.add(tempChapter);
                                    }
                                });
                        }
                    }
                });

        return index;
    }

    @Override
    public List<TempChapter> getIndex(String url) {
        return make(url);
    }

}
