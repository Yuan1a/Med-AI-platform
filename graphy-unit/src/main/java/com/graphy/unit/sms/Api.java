package com.graphy.unit.sms;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.sms.attr.SmsResult;

import java.util.Map;

/**
 * 手机短信工具类
 */
@SuppressWarnings("all")
public class Api {


   public static void main(String[] args) throws Exception {

   }

   /**
    * 发送短信-阿里云
    *
    * @param appID      键值
    * @param appKey     密匙
    * @param appSign    短信签名-可在短信控制台中找到
    * @param endPoint   阿里短信endPoint  例如:cn-hangzhou
    * @param templateId 短信模板-可在短信控制台中找到  例如:SMS_175138681
    * @param mobile     待发送手机号
    * @param params     短信内容
    * @return
    */
   public static SmsResult ALY(String appID, String appKey, String appSign, String endPoint, String templateId, String mobile, Map<String, String> params) {
      try {
         //云通信短信API产品,开发者无需替换
         String product = "Dysmsapi";
         //产品域名,开发者无需替换
         String domain = "dysmsapi.aliyuncs.com";
         System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
         System.setProperty("sun.net.client.defaultReadTimeout", "10000");
         //初始化acsClient,暂不支持region化
         IClientProfile profile = DefaultProfile.getProfile(endPoint, appID, appKey);
         DefaultProfile.addEndpoint(endPoint, endPoint, product, domain);
         IAcsClient acsClient = new DefaultAcsClient(profile);
         //组装请求对象-具体描述见控制台-文档部分内容
         SendSmsRequest request = new SendSmsRequest();
         //必填:待发送手机号
         request.setPhoneNumbers(mobile);
         //必填:短信签名-可在短信控制台中找到
         request.setSignName(appSign);
         //必填:短信模板-可在短信控制台中找到
         request.setTemplateCode(templateId);
         //必填:短信内容
         request.setTemplateParam(JSONObject.toJSONString(params));
         SendSmsResponse result = acsClient.getAcsResponse(request);
         if (result == null) {
            return SmsResult.error("短信发送发生错误");
         } else if (!OwnCommon.isNullOrEmpty(result.getMessage()) && !result.getMessage().toLowerCase().equals("ok")) {
            return SmsResult.error(result.getMessage());
         } else {
            return SmsResult.ok();
         }
      } catch (Exception err) {
         return SmsResult.error(err.getMessage());
      }
   }


   /**
    * 发送短信-腾讯云
    *
    * @param appID      键值
    * @param appKey     密匙
    * @param appSign    签名
    * @param templateId 短信模板ID
    * @param mobile     手机号码
    * @param params     短信内容
    * @return
    */
   public static SmsResult TXY(String appID,
                               String appKey,
                               String appSign,
                               String templateId,
                               String mobile,
                               String[] params) {
      try {
         if (OwnCommon.isNullOrEmpty(mobile)) throw new Exception("接收的手机号码不能为空");
         SmsMultiSender msender = new SmsMultiSender(Integer.valueOf(appID), appKey);
         String[] mobiles = new String[]{mobile};
         SmsMultiSenderResult result = msender.sendWithParam("86", mobiles, Integer.valueOf(templateId), params, appSign, "", "");
         if (result == null) {
            return SmsResult.error("短信发送发生错误");
         } else if (!OwnCommon.isNullOrEmpty(result.errMsg) && !result.errMsg.toLowerCase().equals("ok")) {
            return SmsResult.error(result.errMsg);
         } else {
            return SmsResult.ok();
         }
      } catch (Exception err) {
         return SmsResult.error(err.getMessage());
      }
   }

}
