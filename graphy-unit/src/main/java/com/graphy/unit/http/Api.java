package com.graphy.unit.http;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.http.assist.Common;
import com.graphy.unit.http.assist.TrustAnyHostnameVerifier;
import com.graphy.unit.http.assist.TrustAnyTrustManager;
import com.graphy.unit.http.attr.EnumOwnEncode;
import com.graphy.unit.http.attr.EnumOwnPostType;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * 网络请求
 */
@SuppressWarnings("all")
public class Api {

    /**
     * get请求数据
     *
     * @param url     请求地址 支持http与https
     * @param headers 请求头(可为null)
     * @param encode  响应编码 默认:EnumOwnEncode.utf_8
     * @return
     */
    public static String GET(String url, Map<String, String> headers, EnumOwnEncode encode) throws Exception {
        if (encode == null) encode = EnumOwnEncode.utf_8;
        String returnValue = "";

        URLConnection conn = null;
        InputStream is = null;
        try {
            URL console = new URL(url);
            conn = console.openConnection();
            if (url.startsWith("https://")) {
                HttpsURLConnection httpsConn = ((HttpsURLConnection) conn);
                httpsConn.setRequestMethod("GET");
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
                httpsConn.setSSLSocketFactory(sc.getSocketFactory());
                httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            } else {
                HttpURLConnection httpConn = ((HttpURLConnection) conn);
                httpConn.setRequestMethod("GET");
            }
            // 设置连接主机服务器的超时时间：15000毫秒
            conn.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            conn.setReadTimeout(60000);
            // 设置请求头
            Common.addHeaders(conn, headers);
            conn.connect();
            is = conn.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, (EnumOwnEncode.iso_8859_1.getValue())));
            String ret = "";
            while (ret != null) {
                ret = br.readLine();
                if (ret != null && !ret.trim().equals("")) {
                    returnValue += "\r\n" + new String(ret.getBytes(EnumOwnEncode.iso_8859_1.getValue()), encode.getValue());
                }
            }
        } catch (Exception err) {
            throw err;
        } finally {
            try {
                if (is != null) is.close();
            } catch (Exception err) {
                err.printStackTrace();
            }
            try {
                if (conn != null) {
                    if (url.startsWith("https://")) {
                        ((HttpsURLConnection) conn).disconnect();
                    } else {
                        ((HttpURLConnection) conn).disconnect();
                    }
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return returnValue;
    }

    /**
     * post提交数据
     *
     * @param url      请求地址
     * @param body     请求参数
     * @param headers  请求头
     * @param postType 参数类型 默认:EnumOwnPostType.object
     * @param encode   响应编码 默认:EnumOwnEncode.utf_8
     * @return
     * @throws Exception
     */
    public static String POST(String url,
                              Object body,
                              Map<String, String> headers,
                              EnumOwnPostType postType,
                              EnumOwnEncode encode) throws Exception {
        return POST(url, body, headers, postType, encode, 15);
    }

    /**
     * post提交数据
     *
     * @param url            请求地址
     * @param body           请求参数
     * @param headers        请求头
     * @param postType       参数类型 默认:EnumOwnPostType.object
     * @param encode         响应编码 默认:EnumOwnEncode.utf_8
     * @param connectTimeout 链接超时时间(单位：秒)
     * @return
     * @throws Exception
     */
    public static String POST(String url,
                              Object body,
                              Map<String, String> headers,
                              EnumOwnPostType postType,
                              EnumOwnEncode encode,
                              Integer connectTimeout) throws Exception {
        if (encode == null) encode = EnumOwnEncode.utf_8;
        if (postType == null) postType = EnumOwnPostType.object;
        if (connectTimeout == null) connectTimeout = 15;
        String encodeStr = encode.getValue();
        URLConnection conn = null;
        OutputStream out = null;
        BufferedReader reader = null;
        String postParam = null;
        String returnValue = "";


        try {
            conn = new URL(url).openConnection();
            if (url.startsWith("https://")) {
                HttpsURLConnection httpsConn = ((HttpsURLConnection) conn);
                httpsConn.setRequestMethod("POST");
                httpsConn.setInstanceFollowRedirects(true);
                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new java.security.SecureRandom());
                httpsConn.setSSLSocketFactory(sc.getSocketFactory());
                httpsConn.setHostnameVerifier(new TrustAnyHostnameVerifier());
                httpsConn.setConnectTimeout(connectTimeout * 1000);
            } else {
                HttpURLConnection httpConn = ((HttpURLConnection) conn);
                httpConn.setRequestMethod("POST");
                httpConn.setConnectTimeout(connectTimeout * 1000);
            }

            if (postType == EnumOwnPostType.object) {
                postParam = JSONObject.toJSONString(body);
            } else {
                postParam = changePostBodyToString(body);
            }
            Common.addHeaders(conn, headers);
            conn.setRequestProperty("Content-Type", postType.getValue() + "; charset=" + encode.getValue());
            conn.setRequestProperty("connection", "keep-alive");
            conn.setRequestProperty("accept", "*/*");
            conn.setUseCaches(false);//设置不要缓存
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.connect();
            //POST请求
            // 当有数据需要提交时
            if (null != postParam) {
                out = conn.getOutputStream();
                // 注意编码格式，防止中文乱码
                out.write(postParam.getBytes(encodeStr));
                out.close();
            }

            //读取响应
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), encodeStr));
            String lines;
            while ((lines = reader.readLine()) != null) {
                returnValue += lines;
            }
        } catch (Exception err) {
            throw err;
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    if (conn != null) {
                        if (url.startsWith("https://")) {
                            ((HttpsURLConnection) conn).disconnect();
                        } else {
                            ((HttpURLConnection) conn).disconnect();
                        }
                    }
                } catch (Exception err) {
                    err.printStackTrace();
                }
            }

        }
        return returnValue;
    }

    /**
     * 将对象转换为from参数
     *
     * @param from
     * @return
     */
    private static String changePostBodyToString(Object from) throws Exception {
        if (from == null) return null;
        String returnValue = "";
        LinkedHashMap<String, Object> map = OwnCommon.getObjectValue(from);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String mapKey = entry.getKey();
            Object mapValue = entry.getValue();
            if (mapValue != null) {
                returnValue += returnValue.equals("") ? mapKey + "=" + mapValue : "&" + mapKey + "=" + mapValue;
            }

        }
        return returnValue;
    }

    /**
     * 获取请求来源IP
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.equals("") && !"unKnown".equalsIgnoreCase(ip)) {
            //多次反向代理后会有多个ip值，第一个ip才是真实ip
            int index = ip.indexOf(",");
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        }
        ip = request.getHeader("X-Real-IP");
        if (ip != null && !ip.equals("") && !"unKnown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

    /**
     * 获取Cookie值
     *
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie item : cookies) {
            if (item.getName().equals(cookieName)) {
                String value = item.getValue();
                if (value != null && !value.equals("") && !value.replaceAll("null", "").equals("")) {
                    return item.getValue();
                }
            }
        }
        return null;
    }

    /**
     * 添加coookie
     *
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void setCookie(HttpServletResponse response, String cookieName, String cookieValue) {
        Cookie cookie = new Cookie(cookieName, cookieValue);
        cookie.setMaxAge(60 * 60 * 24 * 365 * 10);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 获取请求域名
     *
     * @param request
     * @return
     */
    public static String getHost(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        String host = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
        return host;
    }

    /**
     * 设置浏览器会话标识
     *
     * @param response      响应对象
     * @param clientKeyName 唯一标识名称
     */
    public static void setSessionId(HttpServletResponse response, String clientKeyName) {
        String keyValue = OwnCommon.createNowToken(60) + OwnCommon.getUUID();
        Api.setCookie(response, clientKeyName, keyValue);
    }

    /**
     * 设置会话标识
     *
     * @param request       请求对象
     * @param clientKeyName 唯一标识名称
     * @return
     */
    public static String getSessionId(HttpServletRequest request, String clientKeyName) {
        String keyValue = Api.getCookie(request, clientKeyName);
        if (OwnCommon.isNullOrEmpty(keyValue)) keyValue = request.getHeader(clientKeyName);
        if (OwnCommon.isNullOrEmpty(keyValue)) keyValue = request.getParameter(clientKeyName);
        return keyValue;
    }

    /**
     * 初始化会话标识
     *
     * @param request
     * @param response
     * @param clientKeyName
     */
    public static void initSessionId(HttpServletRequest request, HttpServletResponse response, String clientKeyName) {
        String keyValue = getSessionId(request, clientKeyName);
        if (OwnCommon.isNullOrEmpty(keyValue)) setSessionId(response, clientKeyName);
    }

    /**
     * 获取请求中的json数据
     *
     * @param request     请求对象
     * @param charsetName 编码 默认:utf-8 utf-8,GB2312,GBK
     * @return
     */
    public static String getRequestJson(HttpServletRequest request, String charsetName) {
        if (OwnCommon.isNullOrEmpty(charsetName)) charsetName = "utf-8";
        StringBuffer sb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), charsetName));
            while ((line = reader.readLine()) != null)
                sb.append(line);
        } catch (Exception e) {
        }
        //将空格和换行符替换掉避免使用反序列化工具解析对象时失败
        String jsonString = sb.toString().replaceAll("\\s", "").replaceAll("\n", "");
        return sb.toString();
    }

    /**
     * 向请求放发送对象
     *
     * @param response
     * @param obj
     */
    public static void responseObj(HttpServletResponse response, Object obj) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        String errorJson = JSONObject.toJSONString(obj);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(errorJson);
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            if (out != null) out.close();
        }
    }

    /**
     * 向请求发送图片
     *
     * @param response   响应对象
     * @param imageBytes 图片字节
     * @param format     图片格式 例:jpeg,png,bmp...
     */
    public static void responseImage(HttpServletResponse response, byte[] imageBytes, String format) throws Exception {
        BufferedImage image = com.graphy.unit.image.Api.bytesToImage(imageBytes, format);
        if (OwnCommon.isNullOrEmpty(format)) format = "jpeg";
        responseImage(response, image, format);
    }

    /**
     * 向请求发送图片
     *
     * @param response 响应对象
     * @param base64   图片base64字符串
     * @param format   图片格式 例:jpeg,png,bmp...
     */
    public static void responseImage(HttpServletResponse response, String base64, String format) throws Exception {
        byte[] imageBytes = new BASE64Decoder().decodeBuffer(base64);
        if (OwnCommon.isNullOrEmpty(format)) format = "jpeg";
        responseImage(response, imageBytes, format);
    }

    /**
     * 向请求发送图片
     *
     * @param response 响应对象
     * @param image    图片对象
     * @param format   图片格式 例:jpeg,png,bmp...
     */
    public static void responseImage(HttpServletResponse response, BufferedImage image, String format) throws Exception {
        if (OwnCommon.isNullOrEmpty(format)) format = "jpeg";
        response.setContentType("image/" + format);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setIntHeader("Expires", -1);
        ImageIO.write(image, format, response.getOutputStream());
    }


    /**
     * 验证来源IP
     *
     * @param limit
     * @param request
     * @return
     */
    public static boolean verifySourceIp(String limit, HttpServletRequest request) {
        if (StrUtil.hasEmpty(limit)) return true;
        String requestURL = request.getRequestURL().toString();
        String[] ips = limit.split(",");
        for (String item : ips) {
            if (requestURL.startsWith("http://" + item) || requestURL.startsWith("https://" + item)) {
                return true;
            }
        }

        return false;
    }


    public static void main(String[] args) throws Exception {

    }
}
