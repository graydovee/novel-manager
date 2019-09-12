package com.ndovel.ebook.config;

import com.ndovel.ebook.spider.bean.TaskCollection;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpiderConfig {

    @Bean
    public TaskCollection taskCollection(){
        return new TaskCollection();
    }
}
