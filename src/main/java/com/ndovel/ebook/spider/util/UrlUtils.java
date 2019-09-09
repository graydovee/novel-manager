package com.ndovel.ebook.spider.util;

import com.ndovel.ebook.exception.UrlNullException;
import com.ndovel.ebook.utils.StringUtils;

public class UrlUtils {

    private static String httpHeader = "http://";
    private static String httpsHeader = "https://";


    /**
     * 格式化地址
     */
    public static String urlFormat(String url){
        if(url==null || url.equals(""))
            throw new UrlNullException("目标地址为空");
        if(!url.startsWith("http"))
            url = httpHeader + url;
        return url;
    }

    /**
     * 跳转到子网页网页的地址
     */
    public static String jump(String url, String path){
        if (StringUtils.isEmpty(url)) {
            throw new UrlNullException("跳转目标地址为空");
        }
        if(StringUtils.isEmpty(path)){
            return url;
        }

        String newStr = urlFormat(url);

        if (path.startsWith("/")){
            //跳转至根路径
            newStr = url.substring(0,url.indexOf("/",httpsHeader.length())) + path;
        }else{
            //相对路径跳转
            newStr = url.substring(0,url.lastIndexOf("/") + 1) + path;
        }
        return newStr;
    }

}
