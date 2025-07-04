package com.graphy.unit.verify;

import cn.hutool.core.util.StrUtil;

import java.util.regex.Pattern;

/**
 * 字符串格式验证
 */
@SuppressWarnings("all")
public class Api {

    /**
     * 验证手机号码是否正确
     *
     * @param str 手机号码
     * @return
     */
    public static Boolean mobile(String str) {
        if (StrUtil.hasEmpty(str) || str.length() != 11 || !number(str)) return false;
        if (!String.valueOf(str.toCharArray()[0]).equals("1") && String.valueOf(str.toCharArray()[1]).equals("0")) return false;
        return true;
    }

    /**
     * 是否为数字
     *
     * @param string
     * @return
     */
    public static boolean number(String string) {
        if (string == null)
            return false;
        Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");
        return pattern.matcher(string).matches();
    }

}
