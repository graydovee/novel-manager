package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.model.dto.AuthorDTO;
import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.model.dto.SpiderInfoDTO;
import com.ndovel.ebook.model.entity.SpiderInfo;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.spider.util.UrlUtils;
import com.ndovel.ebook.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Optional;

public class IndexSpiderImpl implements IndexSpider {

    private SpiderInfoDTO make(String url){
        SpiderInfoDTO spiderInfo = new SpiderInfoDTO();
        BookDTO book = new BookDTO();
        spiderInfo.setBook(book);

        Document document = Jsoup.parse(HttpClientUtils.get(url));
        Optional.ofNullable(document.getElementById("info"))
                .ifPresent(ele -> {
                    Optional.of(ele).map(element -> element.getElementsByTag("h1"))
                            .map(elements -> elements.get(0))
                            .ifPresent(element1 -> book.setName(element1.text()));

                    Optional.of(ele).map(element -> element.getElementsByTag("p"))
                            .map(elements -> elements.get(0))
                            .ifPresent(element -> {
                                String content = element.text();
                                content = content.substring(content.lastIndexOf("：") + 1);
                                book.setAuthor(new AuthorDTO(content));
                            });
                });

        Optional.ofNullable(document.getElementById("list"))
                .map(element -> element.getElementsByTag("dl"))
                .map(elements -> elements.get(0))
                .map(Element::getAllElements)
                .ifPresent(eles -> {
                    int dt = 0;
                    for (Element ele : eles){
                        if (ele.tag().getName().equals("dt"))
                            dt++;
                        else if (dt==2 && ele.tag().getName().equals("dd")) {
                            Optional.of(ele).map(element -> {
                                    Elements a = element.getElementsByTag("a");
                                    return a.size()>0 ? a : null;
                                })
                                .map(elements -> elements.get(0))
                                .ifPresent(element -> {
                                    if (!StringUtils.isEmpty(element.attr("href")))  {
                                        String u = UrlUtils.jump(url, element.attr("href"));
                                        spiderInfo.setUrl(u);
                                    }
                                });
                            if(!StringUtils.isEmpty(spiderInfo.getUrl()))
                                break;
                        }
                    }
                });

        return spiderInfo;
    }

    @Override
    public SpiderInfoDTO makeSpiderInfo(String url) {
        return make(url);
    }
}
