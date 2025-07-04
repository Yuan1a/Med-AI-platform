package com.graphy.unit.medicalImage;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: qwt
 * @Date: 2023/06/12/16:43
 * @Description: 医学映像智能导诊接口方法
 */
@Slf4j
public class Api {
    public static void main(String[] args) {
        String path ="C:/Users/qiuweitao/Desktop/11.bmp";
        File file = new File(path);
        byte[] bytes = new byte[0];
        try {
            bytes = com.graphy.unit.file.Api.readFileByte(path);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imageToBase64= "";
        try {
            BufferedImage bufferedImage = com.graphy.unit.image.Api.bytesToImage(bytes);
             imageToBase64 = com.graphy.unit.image.Api.imageToBase64(bufferedImage,null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("data",imageToBase64);
        try {
            JSONObject medicalImage = requestPostUrl("http://175.178.128.43:3333/image", imageToBase64, null);
            System.out.println(medicalImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *
     *
     * @return
     */
    public static void medicalImageUpload() throws Exception{


    }





    /**
     *
     *
     * @return
     */
    public static JSONObject requestPostUrl(String url, String imageToBase64, String token) throws Exception{

        InputStream is = null;
        String body = null;
        StringBuilder  res = new StringBuilder();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencode");
        if(token!=null){
            httpPost.addHeader("token",token);
        }
        // 设置请求的参数
        JSONObject jsonParam = new JSONObject();
        StringEntity stringEntity = new StringEntity(imageToBase64, "utf-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("text/plain");
        httpPost.setEntity(stringEntity);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).build();
        httpPost.setConfig(config);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        if(entity != null){
            is = entity.getContent();
            //转换为字节输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));
            while((body=br.readLine()) != null){
                res.append(body);
            }
        }
        Object jsonMap = JSON.parse(res.toString());
        JSONObject jsonObject = JSONObject.parseObject(jsonMap.toString());
//        String medicalImage = res.toString();
        return jsonObject;
    }
}