package cn.graydove.spider;

public interface NovelSpider {

    boolean hasNext();

    String getNextPage();

    /**
     * 爬取下一章节
     */
    void nextPage();

    String getContent();

    String getTitle();
}
