package cn.graydove.ebook.web.service;

public interface SpiderService {
    void downBook(String bookName, String authorName, String bookId, String firstPage, String finalPage);
}
