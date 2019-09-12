package com.ndovel.ebook.spider.bean;

import com.ndovel.ebook.model.dto.BookDTO;
import com.ndovel.ebook.spider.core.NovelSpider;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TaskCollection {

    private Map<BookDTO, NovelSpider> map = new ConcurrentHashMap<>();


    public void put(BookDTO bookDTO, NovelSpider novelSpider){
        map.put(bookDTO, novelSpider);
    }

    public synchronized Map<BookDTO, NovelSpider> popAll(){
        Map<BookDTO, NovelSpider> ret = new HashMap<>();

        for(BookDTO bookDTO:map.keySet()){
            ret.put(bookDTO, map.get(bookDTO));
        }

        map.clear();

        return ret;
    }


}
