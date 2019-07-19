package cn.graydove.ebook.web.service;

public interface SpiderService {
    void downBook(String bookName, String author, String bookId, String firstPage, String finalPage);
}
