package cn.graydove.ebook.web.service;

import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.entity.Chapter;

import java.util.List;

public interface BookService {

    List<Book> selBookByName(String name);

    Book selBookByNameAndAuthor(String name, String author);

    List<Book> getAllBook();
}
