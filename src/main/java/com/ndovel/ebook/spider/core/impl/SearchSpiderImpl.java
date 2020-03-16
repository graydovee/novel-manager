package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.model.dto.SearchResult;
import com.ndovel.ebook.model.dto.TempBook;
import com.ndovel.ebook.model.dto.TempChapter;
import com.ndovel.ebook.spider.core.AbstractSpider;
import com.ndovel.ebook.spider.core.SearchSpider;
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
    private final static String url = "https://sou.xanbhx.com/search?siteid=xsla&q=";

    //https://sou.xanbhx.com/search?siteid=xsla&q=%E6%96%97%E7%A0%B4%E8%8B%8D%E7%A9%B9

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
            List<Element> elements = document.select("li");
            for(var element : elements){
                Elements titleNode = element.select(".s2>a");
                Elements authorNode = element.select(".s4");
                if (titleNode.size() > 0 && authorNode.size() > 0) {
                    SearchResult result = new SearchResult();
                    result.setTitle(titleNode.text());
                    result.setUrl(titleNode.attr("href"));
                    result.setAuthor(authorNode.text());
                    ret.add(result);
                }
            }
        }
        return ret;
    }

}
