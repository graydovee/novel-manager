package com.ndovel.novel.spider.core;

import com.ndovel.novel.model.dto.SpiderInfoDTO;

/**
 * @author graydove
 */
public interface SpiderFactory {

    IndexSpider newIndexSpider();

    SearchSpider newSearchSpider();

    NovelSpider newNovelSpider(SpiderInfoDTO spiderInfoDTO);
}
