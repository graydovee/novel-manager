package com.ndovel.novel.config;

import com.ndovel.novel.model.dto.SpiderInfoDTO;
import com.ndovel.novel.spider.core.IndexSpider;
import com.ndovel.novel.spider.core.NovelSpider;
import com.ndovel.novel.spider.core.SearchSpider;
import com.ndovel.novel.spider.core.SpiderFactory;
import com.ndovel.novel.spider.core.impl.CommonNovelSpider;
import com.ndovel.novel.spider.core.impl.RemoteIndexSpider;
import com.ndovel.novel.spider.core.impl.RemoteNovelSpider;
import com.ndovel.novel.spider.core.impl.RemoteSearchSpider;
import com.ndovel.novel.spider.remote.service.SpiderHttpService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author graydove
 */
@Configuration
public class SpiderConfiguration {

    private SpiderHttpService spiderHttpService;

    public SpiderConfiguration(SpiderHttpService spiderHttpService) {
        this.spiderHttpService = spiderHttpService;
    }

    @Bean
    public SpiderFactory spiderFactory() {
        return new SpiderFactory() {
            @Override
            public IndexSpider newIndexSpider() {
                return new RemoteIndexSpider(spiderHttpService);
            }

            @Override
            public SearchSpider newSearchSpider() {
                return new RemoteSearchSpider(spiderHttpService);
            }

            @Override
            public NovelSpider newNovelSpider(SpiderInfoDTO spiderInfoDTO) {
                return new CommonNovelSpider(spiderInfoDTO);
            }
        };
    }
}
