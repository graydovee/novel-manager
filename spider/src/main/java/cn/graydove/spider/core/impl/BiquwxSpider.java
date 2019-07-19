package cn.graydove.spider.core.impl;

import cn.graydove.spider.core.CommonSpider;

public class BiquwxSpider extends CommonSpider {

    static {
        url = "http://www.biqugewx.com/";
        encode = "GBK";
    }

    public BiquwxSpider(String path, String page) {
        super(path, page);
    }


    @Override
    protected String getConetntFormCode(String code) {
        String content = super.getConetntFormCode(code);
        content = content.substring(0, content.indexOf("<script"));
        return content;
    }
}
