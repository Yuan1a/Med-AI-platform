package com.graphy.unit.dangerstring;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringEscapeUtils;
import com.graphy.unit.OwnCommon;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 危险字符串处理
 */
@SuppressWarnings("all")
public class Api {
    public static void main(String[] args) throws Exception {
        String sb = "{\n" +
                "  \"id\": \"id\",\n" +
                "  \"name\": \"select * from aaa\"\n" +
                "}";
        findDangerSqlOnJson(sb, "select");
    }

    /**
     * 判断json字符串是否包含危险的sql关键字
     *
     * @param jsons   需要判断的数据
     * @param dangers 危险SQL关键字 格式：select|update|union|and|or|delete|insert|trancate.....
     * @return
     */
    public static String findDangerSqlOnJson(String jsons, String dangers) {
        if (OwnCommon.isNullOrEmpty(jsons)) return null;
        String danger = findDangerSql(jsons, dangers);
        if (OwnCommon.isNullOrEmpty(danger)) return null;
        JSONObject jsonObject = JSONObject.parseObject(jsons);
        StringBuilder sb = new StringBuilder();
        recursionJsons(jsonObject, sb, dangers);
        if (OwnCommon.isNullOrEmpty(jsonObject.toString())) return null;
        return sb.toString();
    }

    /**
     * 递归遍历提交的JSON数据并返回包含危险字符的参数信息
     *
     * @param objJson 判断的数据
     * @param sbError 返回的错误信息
     * @param danger  危险字符 select|update|union|and|or|delete|insert|trancate|char|into|....
     */
    private static void recursionJsons(Object objJson, StringBuilder sbError, String danger) {
        //如果obj为json数组
        if (objJson instanceof JSONArray) {
            JSONArray objArray = (JSONArray) objJson;
            for (int i = 0; i < objArray.size(); i++) {
                recursionJsons(objArray.get(i), sbError, danger);
            }
        }
        //如果为json对象
        else if (objJson instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) objJson;
            Iterator it = jsonObject.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next().toString();
                Object object = jsonObject.get(key);
                //如果得到的是数组
                if (object instanceof JSONArray) {
                    JSONArray objArray = (JSONArray) object;
                    recursionJsons(objArray, sbError, danger);
                }
                //如果key中是一个json对象
                else if (object instanceof JSONObject) {
                    recursionJsons((JSONObject) object, sbError, danger);
                }
                //如果key中是其他
                else {
                    String value = object.toString();
                    String dangerText = findDangerSql(value, danger);
                    if (!OwnCommon.isNullOrEmpty(dangerText)) {
                        if (sbError.toString().equals("")) {
                            sbError.append("参数名[" + key + "]" + dangerText);
                        } else {
                            sbError.append("\n参数名[" + key + "]" + dangerText);
                        }
                    }
                }
            }
        }
    }


    /**
     * 搜索sql非法字符
     *
     * @param text    被搜索的源字符串
     * @param dangers SQL危险字符串 格式：select|update|union|and|or|delete|insert|trancate|char|into|....
     * @return
     */
    public static String findDangerSql(String text, String dangers) {
        if (OwnCommon.isNullOrEmpty(dangers)) return null;
        String format = "(?:')|(?:--)|(/\\*(?:.|[\\n\\r])*?\\*/)|(\\b({dangers})\\b)";
        Pattern pattern = Pattern.compile(format.replace("{dangers}", dangers));
        Matcher matcher = pattern.matcher(text);
        boolean isIn = matcher.find();
        if (isIn) {
            String[] stringsDanger = dangers.split("[|]");
            String error = "";
            for (String item : stringsDanger) {
                pattern = Pattern.compile(format.replace("{dangers}", item));
                matcher = pattern.matcher(text);
                boolean itemIsIn = matcher.find();
                if (itemIsIn) {
                    error += error.equals("") ? item : "," + item;
                }
            }
            if (OwnCommon.isNullOrEmpty(error)) {
                return null;
            } else {
                return "值为[" + text + "]字符串中存在非法字符(" + error + ")";
            }
        } else {
            return null;
        }
    }

    private static List<Pattern> patterns = null;

    /**
     * 过滤Xss攻击字符串
     *
     * @param value
     * @return
     */
    public static String filterStripXss(String value) {
        if (OwnCommon.isNullOrEmpty(value)) return value;
        //过滤html
        //value = StringEscapeUtils.escapeHtml(value);
        //过滤js
        value = StringEscapeUtils.escapeJavaScript(value);
        //过滤SQL危险字符串
        //value = StringEscapeUtils.escapeSql(value);
        return value;
    }

}
