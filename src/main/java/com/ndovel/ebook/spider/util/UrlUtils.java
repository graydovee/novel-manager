package com.ndovel.ebook.spider.util;

import com.ndovel.ebook.exception.UrlNullException;
import com.ndovel.ebook.utils.StringUtils;

public class UrlUtils {

    private final static String httpHeader = "http://";
    private final static String httpsHeader = "https://";


    public static String getURI(String url) {
        int pos = url.indexOf("://");
        if (pos > -1) {
            String domainAndUrl = url.substring(pos + 3);
            int domainEnd = domainAndUrl.indexOf('/');
            if (domainEnd > -1) {
                return domainAndUrl.substring(domainEnd);
            } else {
                return "/";
            }
        }
        return url;
    }

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

        if(path.startsWith("http://") || path.startsWith("https://")){
            return path;
        }

        if(StringUtils.isEmpty(path)){
            return url;
        }

        String newStr = urlFormat(url);

        if (path.startsWith("/")){
            //跳转至根路径
            newStr = newStr.substring(0,url.indexOf("/",httpsHeader.length())) + path;
        }else{
            //相对路径跳转
            newStr = newStr.substring(0,url.lastIndexOf("/") + 1) + path;
        }
        return newStr;
    }

}
