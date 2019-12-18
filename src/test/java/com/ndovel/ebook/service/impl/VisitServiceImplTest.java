package com.ndovel.ebook.service.impl;

import com.ndovel.ebook.ApplicationTests;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

public class VisitServiceImplTest extends ApplicationTests {

    @Test
    public void getSum() {
    }

    @Test
    public void updateVisit() {
        //visitService.updateVisit();
    }

    //@Test
    public void getData() throws ParseException {
        Date begin = null, end = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        System.out.println(visitService.getData(begin, end));

        begin = simpleDateFormat.parse("2000/12/18");
        end = simpleDateFormat.parse("2020/12/18");

        System.out.println(visitService.getData(begin, end));

        begin = simpleDateFormat.parse("2019/12/18");
        System.out.println(visitService.getData(begin, end));
    }

    //@Test
    public void testGetData() throws ParseException {
        Date begin = null, end = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

        System.out.println(visitService.getData(1505, begin, end));

        begin = simpleDateFormat.parse("2019/12/18");
        end = simpleDateFormat.parse("2019/12/18");
        System.out.println(visitService.getData(1505, begin, end));

        begin = simpleDateFormat.parse("2019/12/17");
        System.out.println(visitService.getData(1505, begin, end));
    }
}