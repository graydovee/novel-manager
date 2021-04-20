package cn.graydove.ndovel.spider.service;

import cn.graydove.ndovel.spider.model.dto.BookDTO;
import cn.graydove.ndovel.spider.model.dto.BroadCastDTO;
import cn.graydove.ndovel.spider.model.dto.NovelPutDTO;

/**
 * @author graydove
 */
public interface SpiderService {

    void spider(BookDTO bookDTO);

    boolean publishNovel(NovelPutDTO novelPutDTO);

    void broadCaster(BroadCastDTO broadCastDTO);
}
