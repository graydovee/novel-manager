package com.ndovel.ebook;

import com.ndovel.ebook.exception.RequestException;
import com.ndovel.ebook.model.dto.*;
import com.ndovel.ebook.model.entity.Book;
import com.ndovel.ebook.spider.core.IndexSpider;
import com.ndovel.ebook.spider.core.SearchSpider;
import com.ndovel.ebook.spider.core.impl.IndexSpiderImpl;
import com.ndovel.ebook.spider.core.impl.SearchSpiderImpl;
import com.ndovel.ebook.spider.util.HttpClientUtils;
import com.ndovel.ebook.spider.util.UrlUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.List;

public class UtilsTests {

    @Test
    public void getImageTest() throws RequestException, IOException {
        String dirname = "./cover";
        File file = new File(dirname);
        if (!file.exists()) {
            file.mkdirs();
        }
        byte[] byteArray = HttpClientUtils.getByteArray("http://www.xbiquge.la/files/article/image/13/13959/13959s.jpg");

        try(OutputStream os = new BufferedOutputStream(new FileOutputStream(new File(file, "1.jpg")))){
            os.write(byteArray);
            os.flush();
        }

    }

    public void testSpider(){
    }

    public void testUrl(){
        String url = "https://www.bilibili.com/video/av67080456";
        String url2 = "www.bilibili.com/video/av67080456";
        System.out.println(UrlUtils.urlFormat(url2));
        System.out.println(UrlUtils.jump(url,"/video/av67080457"));
        System.out.println(UrlUtils.jump(url,"av67080458"));
    }

    public void reflectTest(){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("12345");

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("zuozhe");

        TypeDTO typeDTO = new TypeDTO();
        typeDTO.setName("leibie");

        bookDTO.setAuthor(authorDTO);
        bookDTO.getType().add(typeDTO);

        System.out.println(bookDTO);

        Book book = bookDTO.writeToDomain();
        System.out.println(book);

        BookDTO bookDTO1 = new BookDTO().init(book);
        System.out.println(bookDTO1);
    }
}
