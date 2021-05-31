package com.ndovel.novel.spider.remote.model;

import lombok.Data;

import java.util.List;

/**
 * @author graydove
 */
@Data
public class SearchResult {

    List<TextLink> result;

    String nextPageUrl;
}
