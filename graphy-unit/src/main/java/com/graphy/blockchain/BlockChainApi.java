package com.graphy.blockchain;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.xml.bind.v2.model.core.ID;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@SuppressWarnings("all")

public class BlockChainApi {

    public static void main(String[] args) throws Exception {
        String host="http://192.168.6.231:9011/hdchain/v1/queryDepositCert";
        String id ="b8c734ae-1243-4770-bce8-9d828ddff24e";
        String s = blockChainExtract(host, id);

    }
    /**
     *post请求方法(请求区块链存储方法)
     *
     * @return
     */
    public static String blockChainStorage(String host,String FileHash,String DepositCertID,String FileName) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("DepositCertID",DepositCertID);
        map.put("Title",FileName);
        map.put("FileName",FileName);
        map.put("FileHash",FileHash);
        map.put("FileType","0");
        map.put("Mode","1");
        map.put("Memo","aaa");
        map.put("FileSize","0");
        map.put("CertTime","depositCert");
        map.put("CreateTime",String.valueOf(new Date().getTime()));
        System.out.println(JSONObject.toJSONString(map));
        String Txid = requestPostUrl(host, map, null);
        return Txid;
    }

    /**
     *post请求方法(请求区块链提取文件方法)
     *
     * @return
     */
    public static String blockChainExtract(String host,String id) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("ID",id);
        JSONObject jsonObject = requestPostqueryDepositCert(host, map, null);
        String fileHash = (String) jsonObject.get("FileHash");
        return fileHash;
    }


    /**
     *post请求区块链存储方法(只返回交易ID)
     *
     * @return
     */
    public static String requestPostUrl(String url, Map<String, Object> param, String token) throws Exception{

        InputStream is = null;
        String body = null;
        StringBuilder  res = new StringBuilder();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        if(token!=null){
            httpPost.addHeader("token",token);
        }
        // 设置请求的参数
        JSONObject jsonParam = new JSONObject();
        param.forEach((k,v)-> jsonParam.put(k,v));
        StringEntity stringEntity = new StringEntity(jsonParam.toString(), "utf-8");
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
        String Txid = res.toString();
        return Txid;
    }

    /**
     *post请求区块链提取文件方法
     *
     * @return
     */
    public static JSONObject requestPostqueryDepositCert(String url, Map<String, Object> param, String token) throws Exception{

        InputStream is = null;
        String body = null;
        StringBuilder  res = new StringBuilder();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        if(token!=null){
            httpPost.addHeader("token",token);
        }
        // 设置请求的参数
        JSONObject jsonParam = new JSONObject();
        param.forEach((k,v)-> jsonParam.put(k,v));
        StringEntity stringEntity = new StringEntity(jsonParam.toString(), "utf-8");
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
        return jsonObject;
    }
}
