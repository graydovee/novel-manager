package com.ndovel.novel.spider.core.impl;

import com.ndovel.novel.model.dto.SearchResult;
import com.ndovel.novel.model.dto.TempBook;
import com.ndovel.novel.spider.core.AbstractSpider;
import com.ndovel.novel.spider.core.SearchSpider;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * 按小说名找到小说主页
 */
@Slf4j
public class SearchSpiderImpl extends AbstractSpider implements SearchSpider {

    /**
     * 已经不能用的URL:
     * https://sou.xanbhx.com/search?siteid=xsla&q=
     * https://sou.xanbhx.com/search?siteid=xsla&q=%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9
     */
    private final static String url = "https://www.xsbiquge.com/search.php?keyword=";



    private Document htmlCode(String key){
        String[] split = key.split("[\\s]+");
        for(int i = 0 ; i < split.length; ++i){
            split[i] = URLEncoder.encode(split[i], StandardCharsets.UTF_8);
        }
        key = String.join("+", split);
        String sendUrl = url + key;
        String s = sendHttpGetRequest(sendUrl);
        return Jsoup.parse(s);
}


    @Override
    public List<SearchResult> search(String key) {
        Document document = htmlCode(key);
        List<SearchResult> ret = new ArrayList<>();
        if (document != null) {
            List<Element> elements = document.select(".result-item");
            for(var element : elements){
                SearchResult result = new SearchResult();

                Element pic = element.select(".result-game-item-pic>a").get(0);
                result.setUrl(pic.attr("href"));

                Element img = pic.select("img").get(0);
                result.setCoverUrl(img.attr("src"));

                Element detail = element.select(".result-game-item-detail").get(0);
                Element title = detail.select(".result-item-title>a>span").get(0);
                result.setBookName(title.text());

                for (var e: detail.select(".result-game-item-info>p")) {
                    Elements span = e.select("span");
                    if (span.get(0).text().startsWith("作者")) {
                        result.setAuthorName(span.get(1).text());
                        break;
                    }
                }
                ret.add(result);
            }
        }
        return ret;
    }

}
