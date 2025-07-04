package com.graphy.unit.alibabafonts;

import cn.hutool.core.util.StrUtil;
import com.steadystate.css.parser.CSSOMParser;
//import org.w3c.css.sac.InputSource;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;
import org.w3c.dom.css.CSSStyleSheet;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * 阿里巴巴矢量图库管理
 */
@SuppressWarnings("all")
public class Api {


    /**
     * 远程解析阿里巴巴矢量图库样式类
     *
     * @param cssUrl 样式文件链接地址
     * @return
     */
    public static List<String> getAliyunIcons(String cssUrl) {
        List<String> icons = new ArrayList<String>();
        try {
            String css = com.graphy.unit.http.Api.GET(cssUrl, null, null);
            if (!StrUtil.hasEmpty(css)) {
                String[] items = css.split(":before");
                for (String item : items) {
                    try {
                        Integer last = item.lastIndexOf(".");
                        String cssName = item.substring(last + 1);
                        icons.add(cssName);
                    } catch (Exception err) {

                    }
                }
            }
        } catch (Exception err) {

        }
        return icons;
    }

    public static void main(String[] args) throws Exception {

    }
}
