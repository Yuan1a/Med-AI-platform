package com.graphy.unit;

import com.alibaba.fastjson.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 公共使用类
 */
public class OwnCommon {


    /**
     * 获取唯一标识
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获取唯一标识
     *
     * @return
     */
    public static String getNewId() {
        return com.graphy.unit.idcreate.Api.getNewId();
    }
    /**
     * 生成令牌信息
     *
     * @param tokenCount
     * @return
     */
    public static String createNowToken(Integer tokenCount) {
        String val = "";
        Random random = new Random();
        //用循环输出六个字符进行拼接
        for (int i = 0; i < tokenCount; i++) {
            // 本次循环是数字还是字母
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "number";
            // 字母
            if ("char".equalsIgnoreCase(charOrNum)) {
                // 本次字母为大写还是小写（ASCII）
                int choice = random.nextInt(2) % 2 == 0 ? 65 : 97;
                //生成这个字符
                val += (char) (choice + random.nextInt(26));
            }
            // 数字
            else if ("number".equalsIgnoreCase(charOrNum)) {
                //数字可以直接生成
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    /**
     * 判断是否为空
     *
     * @param value
     * @return
     */
    public static boolean isNullOrEmpty(Object value) {
        if (value == null) return true;
        if ((value instanceof String) && value.toString().equals("")) return true;
        return false;
    }

    /**
     * 判断是否为空
     *
     * @param value
     * @param def   默认
     * @return
     */
    public static String isNullOrEmpty(String value, String def) {
        if (OwnCommon.isNullOrEmpty(value)) {
            return def;
        } else {
            return value;
        }
    }

    /**
     * 反射对象属性值
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static LinkedHashMap<String, Object> getObjectValue(Object object) throws Exception {
        if (object == null) return null;
        LinkedHashMap<String, Object> returnValue = new LinkedHashMap<String, Object>();
        String typeName = object.getClass().getTypeName();
        if (typeName.equals("java.util.HashMap") || typeName.equals("java.util.LinkedHashMap")) {
            String mapJson = JSONObject.toJSONString(object);
            JSONObject jsonObject = JSONObject.parseObject(mapJson);
            Iterator iter = jsonObject.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry) iter.next();
                if (!returnValue.containsKey(entry.getKey().toString())) returnValue.put(entry.getKey().toString(), entry.getValue());
            }
        } else {
            Class<?> clz = object.getClass();
            Field[] fields = clz.getFields();
            for (Field field : fields) {
                // 如果类型是String
                if (field.getGenericType().toString().equals("class java.lang.String")) {
                    // 拿到该属性的gettet方法
                    /**
                     * 这里需要说明一下：他是根据拼凑的字符来找你写的getter方法的
                     * 在Boolean值的时候是isXXX（默认使用ide生成getter的都是isXXX）
                     * 如果出现NoSuchMethod异常 就说明它找不到那个gettet方法 需要做个规范
                     */
                    Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                    String val = (String) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }
                // 如果类型是Integer
                if (field.getGenericType().toString().equals("class java.lang.Integer")) {
                    Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                    Integer val = (Integer) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }
                // 如果类型是Double
                if (field.getGenericType().toString().equals("class java.lang.Double")) {
                    Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                    Double val = (Double) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }

                // 如果类型是Boolean 是封装类
                if (field.getGenericType().toString().equals("class java.lang.Boolean")) {
                    Method m = (Method) object.getClass().getMethod(field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }

                if (field.getGenericType().toString().equals("boolean")) {
                    Method m = (Method) object.getClass().getMethod(field.getName());
                    Boolean val = (Boolean) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }
                // 如果类型是Date
                if (field.getGenericType().toString().equals("class java.util.Date")) {
                    Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                    Date val = (Date) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }
                // 如果类型是Short
                if (field.getGenericType().toString().equals("class java.lang.Short")) {
                    Method m = (Method) object.getClass().getMethod("get" + getMethodName(field.getName()));
                    Short val = (Short) m.invoke(object);
                    if (!returnValue.containsKey(field.getName())) returnValue.put(field.getName(), val);
                }
            }
        }
        return returnValue.size() == 0 ? null : returnValue;
    }

    /**
     * 把第一个字符串的第一个字母大写、效率是最高的
     *
     * @param fildeName
     * @return
     * @throws Exception
     */
    private static String getMethodName(String fildeName) throws Exception {
        byte[] items = fildeName.getBytes();
        items[0] = (byte) ((char) items[0] - 'a' + 'A');
        return new String(items);
    }

    public static void main(String[] args) throws Exception {

         /*
        Date now = new Date();
        String token = "39_Pw6OWoL2YYP7D-bfPYSkt3gA2XvnFNlecz08o9I9Rl4uZIdv7IFp32lQ7JBKhR8gaSiD8jNKBdagNn4UbGSzHcD383-nuBVZ8-0BJ-y_rUYCp_4y5hGw5CTmCM4g74ZGzavSTD1SHPfyOSINHTMeAGABNG";
        String openId = "ohqC0jvCsStT6edIfDXCfH-iU04M";
        String template_id = "B2sCW-O6q42zIDf28hPSLPdq94n6vcX5nkCQcor0LZE";



      WxTemplateQu wxTemplate = new WxTemplateQu(openId, template_id);
      wxTemplate.addDataItem("first", new WxTemplateDataItem("出生证领取提醒"));
      wxTemplate.addDataItem("keyword1", new WxTemplateDataItem("用户您好，您在线申请办理的出生证信息已审核通过，为防止出生证领取超时，请尽快前往医院领取出生证，或者通过在线邮寄申请快递到家！"));
      wxTemplate.addDataItem("keyword2", new WxTemplateDataItem(com.graphy.unit.date.IdCard.dateFormat(now, "yyyy-MM-dd HH:mm:ss")));
      wxTemplate.addDataItem("remark", new WxTemplateDataItem("点击进入小程序，在线申领出生证快递到家。", "red"));
      wxTemplate.setMiniprogram(new WxTemplateMiniProgram("wx8cb112aefec5aca1", "packageOther/pages/express/search/index?name=林诗达&idCard=460028198709180079"));
      WxResult<String> result = com.graphy.unit.wechat.IdCard.sendTemplateMsg(wxTemplate, token);
      */


    }
}
