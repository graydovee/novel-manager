package com.ndovel.ebook.spider.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpClientUtilsTest {

    @Test
    public void get() {
        HttpClientUtils.get("http://www.biquge001.com/Book/16/16935/12799783.html");
    }
}