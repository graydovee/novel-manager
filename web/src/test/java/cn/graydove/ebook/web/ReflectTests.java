package cn.graydove.ebook.web;

import cn.graydove.ebook.web.model.dto.BookDTO;
import cn.graydove.ebook.web.model.dto.TypeDTO;
import cn.graydove.ebook.web.model.dto.base.BaseDTO;
import cn.graydove.ebook.web.model.entity.Book;
import cn.graydove.ebook.web.model.entity.Chapter;
import cn.graydove.ebook.web.model.entity.Type;
import cn.graydove.ebook.web.repository.BookRepository;
import cn.graydove.ebook.web.repository.ChapterRepository;
import cn.graydove.ebook.web.service.ChapterService;
import cn.graydove.ebook.web.utils.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sun.reflect.misc.ReflectUtil;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ReflectTests {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ChapterService chapterService;

    @Test
    public void test(){
        Book book = bookRepository.selBookByNameAndAuthor("天牧","厌笔萧生");
        Chapter template = chapterService.getFirstChapter(book);

        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(template.getClass());
        } catch (IntrospectionException e) {
            log.info("xxxxxxxxxxxxxxxx", e);
            return ;
        }

        List<PropertyDescriptor> descriptors = Arrays.stream(beanInfo.getPropertyDescriptors()).filter(p -> {
            String name = p.getName();
            //过滤掉不需要修改的属性
            //return !"class".equals(name) && !"id".equals(name);
            return !"class".equals(name);
        }).collect(Collectors.toList());

        for (PropertyDescriptor descriptor : descriptors) {
            //descriptor.getWriteMethod()方法对应set方法
            Method readMethod = descriptor.getReadMethod();
            System.out.println(descriptor.getName());
            try {
                Object o = readMethod.invoke(template);
                System.out.println(o);
            } catch (IllegalAccessException | InvocationTargetException e) {
                log.info("xxxxxxxxxxxxxxxx", e);
                return ;
            }
        }
    }

    @Test
    public void transTest(){
        Book book = bookRepository.selBookByNameAndAuthor("天牧","厌笔萧生");
        BookDTO bookDTO = new BookDTO().init(book);
        System.out.println(bookDTO);
        Book b = bookDTO.writeToDomain();
        System.out.println(b);
    }

    @Test
    public void transTest2(){
        Book book = bookRepository.selBookByNameAndAuthor("天牧","厌笔萧生");
        BookDTO bookDTO = new BookDTO().init(book);
//        System.out.println(ReflectUtils.getParameterizedType(HashSet.class));
        System.out.println(bookDTO);
    }
}
