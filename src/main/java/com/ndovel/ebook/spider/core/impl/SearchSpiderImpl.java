package com.ndovel.ebook.spider.core.impl;

import com.ndovel.ebook.model.dto.TempBook;
import com.ndovel.ebook.spider.core.AbstractSpider;
import com.ndovel.ebook.spider.core.SearchSpider;
import com.ndovel.ebook.model.dto.SpiderIndex;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 按小说名找到小说主页
 */
@Slf4j
public class SearchSpiderImpl extends AbstractSpider implements SearchSpider {
    private final static String url = "https://www.biquge.tv/modules/article/search.php?searchkey=";

    private String htmlCode(String key){
        try {
            key = URLEncoder.encode(key, "GBK");
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage(), e);
        }
        return sendHttpGetRequest(url + key);
    }


    @Override
    public List<TempBook> findAllIndex(String key) {
        return null;
    }
}
