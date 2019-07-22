package cn.graydove.ebook.web;

import cn.graydove.ebook.web.model.dto.BookDTO;
import cn.graydove.ebook.web.utils.ReflectUtils;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class UtilsTest {

    @Test
    public void test() {
        System.out.println(ReflectUtils.getCollectionType(BookDTO.class,"type")[0]);
    }
}
