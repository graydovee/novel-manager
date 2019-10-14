package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.spider.core.SearchSpider;
import com.ndovel.ebook.model.dto.SpiderIndex;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.utils.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SearchSpiderImpl implements SearchSpider {
    private final static String url = "https://www.biquge.tv/modules/article/search.php?searchkey=";

    private String htmlCode(String key){
        try {
            key = URLEncoder.encode(key, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return HttpClientUtils.get(url + key);
    }

    private List<SpiderIndex> parseIndex(String code){
        Document document = Jsoup.parse(code);
        Elements elements = document.select("tr");
        return elements.stream()
                .filter((trElement) -> trElement != null && !StringUtils.isEmpty(trElement.attr("id")) && trElement.attr("id").toLowerCase().equals("nr"))
                .map(element -> {
                    SpiderIndex spiderIndex = new SpiderIndex();

                    Optional.ofNullable(element.getElementsByClass("odd"))
                            .map(eles -> eles.get(0))
                            .map(ele -> ele.getElementsByTag("a"))
                            .map(eles -> eles.get(0))
                            .ifPresent((ele -> {
                                spiderIndex.setUrl(ele.attr("href"));
                                spiderIndex.setName(ele.text());
                            }));

                    Optional.ofNullable(element.getElementsByClass("odd"))
                            .map(eles -> eles.get(1))
                            .ifPresent(ele -> spiderIndex.setAuthor(ele.text()));
                    return spiderIndex;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<SpiderIndex> findAllIndex(String key) {
        String code = htmlCode(key);
        return parseIndex(code);
    }
}
