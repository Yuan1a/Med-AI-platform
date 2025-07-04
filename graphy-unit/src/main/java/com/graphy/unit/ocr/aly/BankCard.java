package com.graphy.unit.ocr.aly;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.ocr.OcrCommom;
import com.graphy.unit.ocr.aly.assist.AlyHttp;
import com.graphy.unit.ocr.aly.attr.BankCardResult;

import java.util.HashMap;
import java.util.Map;

/**
 * 阿里云Ocr智能识别银行卡
 */
public class BankCard {

    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\Administrator\\Desktop\\社保卡.jpg";
        byte[] bs = com.graphy.unit.file.Api.readFileByte(filePath);
        Base64 data = new Base64();
        String base64 = data.encodeAsString(bs);
        //妇女儿童 0128e4445c54448ea2c13ec437f5ec5c
        BankCardResult bcr = BankCard.analyze1(base64, "0128e4445c54448ea2c13ec437f5ec5c");
        System.out.println(JSONObject.toJSONString(bcr));
    }


    /**
     * 阿里云官方-银行卡解析
     *
     * @return
     */
    public static BankCardResult analyze1(String base64, String appCode) throws Exception {

        base64 = OcrCommom.cutBase64(base64);
        String host = "https://cardid.market.alicloudapi.com";
        String path = "/ai_market/ai_ocr_universal/yin_hang_ka/v1";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<>();
        Map<String, String> bodys = new HashMap<>();
        bodys.put("IMAGE", base64);
        bodys.put("IMAGE_TYPE", "0");
        HttpResponse response = AlyHttp.doPost(host, path, method, headers, querys, bodys);
        HttpEntity entity = response.getEntity();
        JSONObject res = JSONObject.parseObject(EntityUtils.toString(entity));
        if (res == null || res.getJSONObject("银行卡实体信息") == null) throw new Exception("无法解析银行卡信息");
        JSONObject jbody = res.getJSONObject("银行卡实体信息");
        if (OwnCommon.isNullOrEmpty(jbody.getString("银行名称"))) throw new Exception("无法解析银行卡开户行信息");
        if (OwnCommon.isNullOrEmpty(jbody.getString("银行卡号"))) throw new Exception("无法解析银行卡号信息");
        BankCardResult bcr = new BankCardResult();
        bcr.setBankName(jbody.getString("银行名称"));
        bcr.setCardNo(jbody.getString("银行卡号"));
        bcr.setOutTime(jbody.getString("银行卡有效期"));
        bcr.setTypeCode(jbody.getString("银行卡类型"));
        bcr.setTypeName(jbody.getString("银行卡类型名称"));
        return bcr;

    }


}
