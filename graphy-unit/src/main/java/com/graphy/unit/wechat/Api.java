package com.graphy.unit.wechat;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.wechat.assist.*;
import com.graphy.unit.wechat.attr.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.spec.AlgorithmParameterSpec;
import java.util.*;

/**
 * 微信api 微信支付接口api https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=9_4 微信开放平拍接口api
 * https://developers.weixin.qq.com/miniprogram/dev/framework/
 */
@SuppressWarnings("all")
public class Api {
    private static String appId = "wx8cb112aefec5aca1";
    private static String appSecret = "d184eb79329f718c237fd2283cc7fa14";
    private static String mchId = "1536157531";
    private static String mchKey = "C0C3AEC67D0211E99A7D02004C4F4F50";

    private static String openId = "ohqC0jvCsStT6edIfDXCfH-iU04M";

    public static void main(String[] args) throws Exception {
        String token =
                "39_uzeHzh_1WjBhUSZ4SzwSIhGCg1YX0SWjq8Zkokr2lR-ewcMsGmv591GamuFY2RA4GkEg0_55fWhQre17DUqpQRsybEJg4T_CbjQxfIXMkLtueUCIBcK3yKXDWMkGAL2usZHMVqSou8PgKuEfWAQfAJARVL";
        String openId = "ohqC0jvCsStT6edIfDXCfH-iU04M";
        //String openId = "ohqC0jipLHKGn2y05K6ogaFabZ6g";

        WxResult<String> aaa =
                sendCustomMsg_Text(
                        token,
                        openId,
                        "用户您好，您在线申请办理的出生证信息已审核通过，为防止出生证领取超时，请尽快前往医院领取出生证，或者通过在线邮寄申请快递到家！",
                        "wx8cb112aefec5aca1",
                        "packageOther/pages/express/search/index?name=林诗达&idCard=460028198709180079",
                        "点击进入小程序，在线申领出生证快递到家。");
        String asdasdad = "";
    }

    /**
     * 发送公众号自定义消息推送
     *
     * @param token            公众号令牌
     * @param openid           公众号openId
     * @param content          文本内容
     * @param miniProgramAppId 小程序
     * @param miniProgramUrl
     * @param miniProgramText
     * @throws Exception
     */
    public static WxResult<String> sendCustomMsg_Text(
            String token,
            String openid,
            String content,
            String miniProgramAppId,
            String miniProgramUrl,
            String miniProgramText)
            throws Exception {
        if (OwnCommon.isNullOrEmpty(miniProgramText)) miniProgramText = "点击此处链接小程序";
        CustomMsgQu msgtype = new CustomMsgQu();
        msgtype.touser = openid;
        msgtype.msgtype = "text";
        msgtype.text = new CustomMsgTypeQu();
        msgtype.text.content = content;
        if (!OwnCommon.isNullOrEmpty(miniProgramAppId) && !OwnCommon.isNullOrEmpty(miniProgramUrl)) {
            String mpu =
                    "<a href=\"http://www.qq.com\" data-miniprogram-appid=\"{data-miniprogram-appid}\" data-miniprogram-path=\"{data-miniprogram-path}\">{data-miniprogram-text}</a>";
            mpu = mpu.replace("{data-miniprogram-appid}", miniProgramAppId);
            mpu = mpu.replace("{data-miniprogram-path}", miniProgramUrl);
            mpu = mpu.replace("{data-miniprogram-text}", miniProgramText);
            msgtype.text.content += "\n" + mpu;
        }
        String url = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=" + token;
        String result = com.graphy.unit.http.Api.POST(url, msgtype, null, null, null);
        if (result == null || result.equals("")) return WxResult.error("无法请求微信发送模板消息远程接口");
        CustomMsgSp customMsgSp = JSONObject.parseObject(result, CustomMsgSp.class);
        if (!customMsgSp.errcode.equals(0))
            return WxResult.error(customMsgSp.errmsg, String.valueOf(customMsgSp.errcode));
        return WxResult.result(true);
    }

    /**
     * 发送模板消息
     *
     * @param wxTemplate
     * @return
     */
    public static WxResult<String> sendTemplateMsg(WxTemplateQu wxTemplate, String accessToken)
            throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
        url = String.format(url, accessToken);
        String result = com.graphy.unit.http.Api.POST(url, wxTemplate, null, null, null);
        if (result == null || result.equals("")) return WxResult.error("无法请求微信发送模板消息远程接口");
        WxTemplateSp wxTemplateSp = JSONObject.parseObject(result, WxTemplateSp.class);
        if (wxTemplateSp == null) return WxResult.error("请求接口返回数据为空");
        if (!wxTemplateSp.errcode.equals(0))
            return WxResult.error(wxTemplateSp.errmsg, String.valueOf(wxTemplateSp.errcode));
        return WxResult.result(String.valueOf(wxTemplateSp.msgid));
    }

    /**
     * 根据公众号的令牌与openId获取对应的用户信息
     *
     * @param accessToken 公众号的令牌
     * @param openId      公众号的openId
     * @return
     */
    public static WxResult<WxUserInfoSp> wxbUserInfo(String accessToken, String openId)
            throws Exception {
        List<String> openIds = new ArrayList<String>();
        openIds.add(openId);
        WxResult<List<WxUserInfoSp>> result = wxbUserInfo(accessToken, openIds);
        if (result.code.equals("1")) {
            if (result.result != null && result.result.size() > 0) {
                return WxResult.result(result.result.get(0));
            } else {
                return WxResult.error("当前的openId无法获取到用户信息");
            }
        } else {
            return WxResult.error(result.error, result.wxCode);
        }
    }

    /**
     * 根据公众号的令牌与openId获取对应的用户信息
     *
     * @param accessToken 公众号的令牌
     * @param openId      公众号的openId
     * @return
     */
    public static WxResult<List<WxUserInfoSp>> wxbUserInfo(String accessToken, List<String> openId)
            throws Exception {
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token={accessToken}";
        url = url.replace("{accessToken}", accessToken);
        JSONArray postUserList = new JSONArray();
        for (String item : openId) {
            JSONObject userItem = new JSONObject();
            userItem.put("openid", item);
            userItem.put("lang", "zh_CN");
            postUserList.add(userItem);
        }
        JSONObject jobj = new JSONObject();
        jobj.put("user_list", postUserList);
        String result = com.graphy.unit.http.Api.POST(url, jobj, null, null, null);
        if (OwnCommon.isNullOrEmpty(result)) return WxResult.error("请求微信接口返回空");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("errcode") != null && !jsonObject.getInteger("errcode").equals(0)) {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            return WxResult.error(errmsg, errcode);
        } else {
            String user_info_list = jsonObject.getString("user_info_list");
            List<WxUserInfoSp> users = JSONObject.parseArray(user_info_list, WxUserInfoSp.class);
            return WxResult.result(users);
        }
    }

    /**
     * 获取当前公众号的openId
     *
     * @param accessToken 通过公众号appId与appSecret获取的令牌信息
     * @param nextOpenId  开始的openId 为空则从头开始
     * @return
     */
    public static WxResult<WxbOpenIds> wxbOpenIds(String accessToken, String nextOpenId)
            throws Exception {
        WxbOpenIds openIds = new WxbOpenIds();
        String url =
                "https://api.weixin.qq.com/cgi-bin/user/get?access_token={accessToken}&next_openid={nextOpenId}";
        url = url.replace("{accessToken}", accessToken);
        url = url.replace("{nextOpenId}", nextOpenId == null ? "" : nextOpenId);
        String result = com.graphy.unit.http.Api.GET(url, null, null);
        if (OwnCommon.isNullOrEmpty(result)) return WxResult.error("请求微信接口返回空");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("errcode") != null && !jsonObject.getInteger("errcode").equals(0)) {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            return WxResult.error(errmsg, errcode);
        } else {
            JSONArray jsonArray = jsonObject.getJSONArray("openid");
            openIds.setNextOpenId(jsonObject.getString("next_openid"));
            List<String> returnOpenIds = new ArrayList<String>();
            if (jsonArray != null && jsonArray.size() > 0) {
                for (Integer i = 0; i < jsonArray.size(); i++) {
                    returnOpenIds.add(jsonArray.getString(i));
                }
            }
            openIds.setOpenId(returnOpenIds);
            return WxResult.result(openIds);
        }
    }

    /**
     * 公众号-获取凭证
     *
     * @param appId     公众号ID
     * @param appSecret 公众号密匙
     * @return
     */
    public static WxResult<AccessTokenSp> wxbAccessToken(String appId, String appSecret)
            throws Exception {
        if (OwnCommon.isNullOrEmpty(appId)) return WxResult.error("appId不能为空");
        if (OwnCommon.isNullOrEmpty(appSecret)) return WxResult.error("appSecret不能为空");
        String access_token_url =
                "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
        String requestUrl = String.format(access_token_url, appId, appSecret);
        String result = com.graphy.unit.http.Api.GET(requestUrl, null, null);
        if (OwnCommon.isNullOrEmpty(result)) return WxResult.error("请求微信接口返回空");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("errcode") != null) {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            return WxResult.error(errmsg, errcode);
        } else {
            AccessTokenSp value = JSONObject.parseObject(result, AccessTokenSp.class);
            return WxResult.result(value);
        }
    }

    /**
     * 根据微信登录编码code获取当前用户信息
     *
     * @param code               登录编码
     * @param appId              程序ID
     * @param appSecret          程序密匙
     * @param wxPlatformTypeEnum 平台类别 默认：微信小程序
     * @return
     * @throws Exception
     */
    public static WxResult<OpenIdInfoSp> getOpenIdInfo(
            String code, String appId, String appSecret, WxPlatformTypeEnum wxPlatformTypeEnum)
            throws Exception {
        if (wxPlatformTypeEnum == null) wxPlatformTypeEnum = WxPlatformTypeEnum.MiniProgram;
        if (OwnCommon.isNullOrEmpty(code)) return WxResult.error("code不能为空");
        if (OwnCommon.isNullOrEmpty(appId)) return WxResult.error("appId不能为空");
        if (OwnCommon.isNullOrEmpty(appSecret)) return WxResult.error("appSecret不能为空");
        String url = "";
        if (wxPlatformTypeEnum == WxPlatformTypeEnum.MiniProgram) {
            url =
                    "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
        } else if (wxPlatformTypeEnum == WxPlatformTypeEnum.OfficialAccounts) {
            url =
                    "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appid}&secret={secret}&code={code}&grant_type=authorization_code";
        }
        String requestUrl =
                url.replace("{appid}", appId).replace("{secret}", appSecret).replace("{code}", code);
        String resultValue = com.graphy.unit.http.Api.GET(requestUrl, null, null);
        JSONObject jsonObject = JSONObject.parseObject(resultValue);
        if (jsonObject.get("errcode") != null) {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            return WxResult.error(errmsg, errcode);
        } else {
            OpenIdInfoSp value = JSONObject.parseObject(resultValue, OpenIdInfoSp.class);
            return WxResult.result(value);
        }
    }

    /**
     * 支付-获取小程序调起支付API的参数
     *
     * @param appId      小程序ID
     * @param appSecret  小程序密匙
     * @param openId     openId
     * @param mchId      商户ID
     * @param mchKey     商户密匙
     * @param orderId    订单ID
     * @param money      支付金额 单位:分钱
     * @param succeedUrl 支付成功后回调地址
     * @param payIp      支付端IP
     * @param body       商品描述
     * @return
     */
    public static WxResult<PayConfigSp> payJsapi(
            String appId,
            String appSecret,
            String openId,
            String mchId,
            String mchKey,
            String orderId,
            Double money,
            String succeedUrl,
            String payIp,
            String body)
            throws Exception {
        Integer payMoney = (int) (money * 100);
        return payJsapi(
                appId, appSecret, openId, mchId, mchKey, orderId, payMoney, succeedUrl, payIp, body);
    }

    /**
     * 支付-获取小程序调起支付API的参数
     *
     * @param appId      小程序ID
     * @param appSecret  小程序密匙
     * @param openId     openId
     * @param mchId      商户ID
     * @param mchKey     商户密匙
     * @param orderId    订单ID
     * @param money      支付金额 单位:分钱
     * @param succeedUrl 支付成功后回调地址
     * @param payIp      支付端IP
     * @param body       商品描述
     * @return
     */
    public static WxResult<PayConfigSp> payJsapi(
            String appId,
            String appSecret,
            String openId,
            String mchId,
            String mchKey,
            String orderId,
            Integer money,
            String succeedUrl,
            String payIp,
            String body)
            throws Exception {
        if (OwnCommon.isNullOrEmpty(appId)) return WxResult.error("appId不能为空");
        if (OwnCommon.isNullOrEmpty(appSecret)) return WxResult.error("appSecret不能为空");
        if (OwnCommon.isNullOrEmpty(openId)) return WxResult.error("openId不能为空");
        if (OwnCommon.isNullOrEmpty(mchId)) return WxResult.error("mchId不能为空");
        if (OwnCommon.isNullOrEmpty(mchKey)) return WxResult.error("mchKey不能为空");
        if (OwnCommon.isNullOrEmpty(orderId)) return WxResult.error("orderId不能为空");
        if (money == null || money.equals(0)) return WxResult.error("money不能为空");
        if (OwnCommon.isNullOrEmpty(succeedUrl)) return WxResult.error("succeedUrl不能为空");
        if (OwnCommon.isNullOrEmpty(payIp)) return WxResult.error("payIp不能为空");
        if (OwnCommon.isNullOrEmpty(body)) return WxResult.error("body不能为空");
        PaySignData signData = new PaySignData(appId, mchId, mchKey);
        signData.addDataItem(new PaySignDataItem("body", body, PaySignDataItemEnum.CDATA));
        signData.addDataItem(new PaySignDataItem("notify_url", succeedUrl, PaySignDataItemEnum.String));
        signData.addDataItem(new PaySignDataItem("openid", openId, PaySignDataItemEnum.String));
        signData.addDataItem(new PaySignDataItem("out_trade_no", orderId, PaySignDataItemEnum.String));
        signData.addDataItem(
                new PaySignDataItem("spbill_create_ip", payIp, PaySignDataItemEnum.String));
        signData.addDataItem(
                new PaySignDataItem("total_fee", String.valueOf(money), PaySignDataItemEnum.String));
        signData.addDataItem(new PaySignDataItem("trade_type", "JSAPI", PaySignDataItemEnum.String));
        String payXml = signData.getSignXml();
        byte[] xml =
                WxRequest.WxPayRequest("https://api.mch.weixin.qq.com/pay/unifiedorder", "POST", payXml);
        if (xml == null || xml.length < 10) return WxResult.error("调用统一下单失败");
        String resXml = new String(xml, "UTF-8");
        Document retDoc = DocumentHelper.parseText(resXml);
        PayMiniConfigBeforeSp signResult =
                (PayMiniConfigBeforeSp) WxApiCommon.getObject(retDoc, PayMiniConfigBeforeSp.class);
        if (signResult != null
                && !signResult.return_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResult.return_msg, signResult.return_code);
        } else if (signResult != null
                && !signResult.result_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(
                    "调用统一下单失,result_code=" + signResult.result_code, signResult.result_code);
        }
        PayConfigSp config = new PayConfigSp();
        config.signType = "MD5";
        config.packageValue = "prepay_id=" + signResult.prepay_id;
        config.nonceStr = String.valueOf(Math.random());
        config.timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
        String fmt = "appId=%s&nonceStr=%s&package=%s&signType=%s&timeStamp=%s&key=%s";
        String signature =
                String.format(
                        fmt,
                        appId,
                        config.nonceStr,
                        config.packageValue,
                        config.signType,
                        config.timeStamp,
                        mchKey);
        config.paySign = WxApiCommon.getMessageDigest(signature.getBytes()).toUpperCase();

        return WxResult.result(config);
    }

    /**
     * 支付-向微信发送支付成功
     *
     * @param response
     */
    public static void payResponseSuccess(HttpServletResponse response) throws Exception {
        response.setContentType("text/xml; charset=UTF-8");
        response
                .getOutputStream()
                .print(
                        "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>");
    }

    /**
     * 支付-向微信发送支付失败
     *
     * @param response
     */
    public static void payResponseFail(HttpServletResponse response) throws Exception {
        response.setContentType("text/xml; charset=UTF-8");
        response
                .getOutputStream()
                .print(
                        "<xml><return_code><![CDATA[FAIL]]></return_code><return_msg><![CDATA[付款回调失败]]></return_msg></xml>");
    }

    /**
     * 支付-解析返回结果
     *
     * @param request
     * @return
     * @throws Exception
     */
    public static WxResult<PayResultSp> payAnalyzeResult(HttpServletRequest request)
            throws Exception {
        BufferedReader bufferedReader = null;
        String text;
        StringBuffer buff = new StringBuffer();
        String err = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setExpandEntityReferences(false);
        PayResultSp pr = new PayResultSp();
        try {
            bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(request.getInputStream(), Charset.forName("UTF-8")));
            while ((text = bufferedReader.readLine()) != null) {
                buff.append(text);
            }
            Document doc = DocumentHelper.parseText(buff.toString());
            Element root = doc.getRootElement();
            String returnCode = root.elementTextTrim("return_code");
            String openid = root.elementTextTrim("openid");
            String tradeNo = root.elementTextTrim("out_trade_no");
            String tradeId = root.elementTextTrim("transaction_id");
            pr.setReturnCode(returnCode);
            pr.setOpenId(openid);
            pr.setOutTradeNo(tradeNo);
            pr.setTransactionId(tradeId);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return WxResult.result(pr);
    }

    /**
     * 支付-关闭订单
     *
     * @param appId      小程序ID
     * @param mchId      商户号ID
     * @param mchKey     商户号密匙
     * @param outTradeNo 本地订单ID
     * @return
     * @throws Exception
     */
    public static WxResult<Boolean> payCloseOrder(
            String appId, String mchId, String mchKey, String outTradeNo) throws Exception {
        if (OwnCommon.isNullOrEmpty(appId)) return WxResult.error("appId不能为空");
        if (OwnCommon.isNullOrEmpty(mchId)) return WxResult.error("mchId不能为空");
        if (OwnCommon.isNullOrEmpty(mchKey)) return WxResult.error("mchKey不能为空");
        if (OwnCommon.isNullOrEmpty(outTradeNo)) return WxResult.error("outTradeNo不能为空");
        PaySignData paySignData = new PaySignData(appId, mchId, mchKey);
        paySignData.addDataItem(new PaySignDataItem("out_trade_no", outTradeNo, null));
        String postXml = paySignData.getSignXml();
        byte[] xml =
                WxRequest.WxPayRequest(
                        "https://api.mch.weixin.qq.com/pay/closeorder", "POST", postXml.toString());
        if (xml == null || xml.length < 10) return WxResult.error("调用关闭订单失败");
        String resXml = new String(xml, "UTF-8");
        Document retDoc = DocumentHelper.parseText(resXml);
        PayBuildSignResultSp signResultSp =
                (PayBuildSignResultSp) WxApiCommon.getObject(retDoc, PayBuildSignResultSp.class);
        if (!signResultSp.return_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResultSp.return_msg, signResultSp.return_code);
        } else if (!signResultSp.result_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResultSp.err_code_des, signResultSp.result_code);
        }
        return WxResult.result(true);
    }

    /**
     * 支付-申请退款
     *
     * <p>当交易发生之后一段时间内，由于买家或者卖家的原因需要退款时，卖家可以通过退款接口将支付款退还给买家，微信支付将在收到退款请求并且验证成功之后，按照退款规则将支付款按原路退到买家帐号上。
     *
     * <p>注意：
     *
     * <p>1、交易时间超过一年的订单无法提交退款
     *
     * <p>2、微信支付退款支持单笔交易分多次退款，多次退款需要提交原支付订单的商户订单号和设置不同的退款单号。申请退款总金额不能超过订单金额。
     * 一笔退款失败后重新提交，请不要更换退款单号，请使用原商户退款单号
     *
     * <p>3、请求频率限制：150qps，即每秒钟正常的申请退款请求次数不超过150次
     *
     * <p>错误或无效请求频率限制：6qps，即每秒钟异常或错误的退款申请请求不超过6次
     *
     * <p>4、每个支付订单的部分退款次数不能超过50次
     *
     * <p>5、如果同一个用户有多笔退款，建议分不同批次进行退款，避免并发退款导致退款失败
     *
     * @param appId         小程序ID
     * @param mchId         商户号ID
     * @param mchKey        商户号密匙
     * @param transactionId 微信生成的订单号，在支付通知中有返回
     * @param outTradeNo    本地订单ID
     * @param outRefundNo   商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * @param totalFee      订单总金额，单位为分，只能为整数
     * @param refundFee     退款总金额，订单总金额，单位为分，只能为整数
     * @param notifyUrl     异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     *                      如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
     * @return
     * @throws Exception
     */
    public static WxResult<PayRefundSp> payRefund(
            String appId,
            String mchId,
            String mchKey,
            String transactionId,
            String outTradeNo,
            String outRefundNo,
            Integer totalFee,
            Integer refundFee,
            String notifyUrl)
            throws Exception {
        if (OwnCommon.isNullOrEmpty(appId)) return WxResult.error("appId不能为空");
        if (OwnCommon.isNullOrEmpty(mchId)) return WxResult.error("mchId不能为空");
        if (OwnCommon.isNullOrEmpty(mchKey)) return WxResult.error("mchKey不能为空");
        if (OwnCommon.isNullOrEmpty(transactionId) && OwnCommon.isNullOrEmpty(outTradeNo)) {
            return WxResult.error("transactionId与outTradeNo二选一");
        }
        PaySignData paySignData = new PaySignData(appId, mchId, mchKey);
        if (!OwnCommon.isNullOrEmpty(transactionId)) {
            paySignData.addDataItem(new PaySignDataItem("transaction_id", transactionId, null));
        } else if (!OwnCommon.isNullOrEmpty(outTradeNo)) {
            paySignData.addDataItem(new PaySignDataItem("out_trade_no", outTradeNo, null));
        }
        paySignData.addDataItem(new PaySignDataItem("out_refund_no", outRefundNo, null));
        paySignData.addDataItem(new PaySignDataItem("total_fee", String.valueOf(totalFee), null));
        paySignData.addDataItem(new PaySignDataItem("refund_fee", String.valueOf(refundFee), null));
        if (!OwnCommon.isNullOrEmpty(notifyUrl)) {
            paySignData.addDataItem(new PaySignDataItem("notify_url", notifyUrl, null));
        }
        String postXml = paySignData.getSignXml();
        byte[] xml =
                WxRequest.WxPayRequest(
                        "https://api.mch.weixin.qq.com/secapi/pay/refund", "POST", postXml.toString());
        if (xml == null || xml.length < 10) return WxResult.error("调用申请退款失败");
        String resXml = new String(xml, "UTF-8");
        Document retDoc = DocumentHelper.parseText(resXml);
        PayRefundSp signResultSp = (PayRefundSp) WxApiCommon.getObject(retDoc, PayRefundSp.class);
        if (!signResultSp.return_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResultSp.return_msg, signResultSp.return_code);
        } else if (!signResultSp.result_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResultSp.err_code_des, signResultSp.result_code);
        }
        return WxResult.result(true);
    }

    /**
     * 支付-查询订单
     *
     * @param appId         小程序ID
     * @param mchId         商户号ID
     * @param mchKey        商户号密匙
     * @param transactionId 微信返回的订单ID
     * @param outTradeNo    本地订单ID
     * @return
     * @throws Exception
     */
    public static WxResult<PayOrderQuerySp> payOrderQuery(
            String appId, String mchId, String mchKey, String transactionId, String outTradeNo)
            throws Exception {
        if (OwnCommon.isNullOrEmpty(appId)) return WxResult.error("appId不能为空");
        if (OwnCommon.isNullOrEmpty(mchId)) return WxResult.error("mchId不能为空");
        if (OwnCommon.isNullOrEmpty(mchKey)) return WxResult.error("mchKey不能为空");
        if (OwnCommon.isNullOrEmpty(transactionId) && OwnCommon.isNullOrEmpty(outTradeNo)) {
            return WxResult.error("transactionId与outTradeNo二选一");
        }
        PaySignData paySignData = new PaySignData(appId, mchId, mchKey);
        if (!OwnCommon.isNullOrEmpty(transactionId)) {
            paySignData.addDataItem(new PaySignDataItem("transaction_id", transactionId, null));
        } else {
            paySignData.addDataItem(new PaySignDataItem("out_trade_no", outTradeNo, null));
        }
        String postXml = paySignData.getSignXml();
        byte[] xml =
                WxRequest.WxPayRequest(
                        "https://api.mch.weixin.qq.com/pay/orderquery", "POST", postXml.toString());
        if (xml == null || xml.length < 10) return WxResult.error("调用查询订单失败");
        String resXml = new String(xml, "UTF-8");
        Document retDoc = DocumentHelper.parseText(resXml);
        PayBuildSignResultSp signResultSp =
                (PayBuildSignResultSp) WxApiCommon.getObject(retDoc, PayBuildSignResultSp.class);
        if (!signResultSp.return_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResultSp.return_msg, signResultSp.return_code);
        } else if (!signResultSp.result_code.toLowerCase().equals("SUCCESS".toLowerCase())) {
            return WxResult.error(signResultSp.err_code_des, signResultSp.result_code);
        }
        PayOrderQuerySp orderQuerySp =
                (PayOrderQuerySp) WxApiCommon.getObject(retDoc, PayOrderQuerySp.class);
        return WxResult.result(orderQuerySp);
    }

    /**
     * 获取微信公众号或者小程序用户的绑定的手机号码
     *
     * @param appId
     * @param appSecret
     * @param code      小程序调用wx.login返回的code
     * @param encrypted
     * @param iv
     * @return
     * @throws Exception
     */
    public static WxResult<WxMobileSp> wxpAnalyzeMobile(
            String appId, String appSecret, String code, String encrypted, String iv) throws Exception {
        if (appId == null || appId.equals("")) throw new Exception("appId不能为空");
        if (appSecret == null || appSecret.equals("")) throw new Exception("appSecret不能为空");
        if (code == null || code.equals("")) throw new Exception("code不能为空");
        if (encrypted == null || encrypted.equals("")) throw new Exception("encrypted不能为空");
        if (iv == null || iv.equals("")) throw new Exception("iv不能为空");
        Map<String, String> info = new HashMap<String, String>();

        String url =
                "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";
        url = url.replace("{appid}", appId);
        url = url.replace("{secret}", appSecret);
        url = url.replace("{code}", code);
        // 向微信服务器发送get请求获取加密了的内容
        String result = com.graphy.unit.http.Api.GET(url, null, null);
        if (OwnCommon.isNullOrEmpty(result)) return WxResult.error("请求微信接口返回空");
        JSONObject jsonObject = JSONObject.parseObject(result);

        if (jsonObject.get("errcode") != null && !jsonObject.getInteger("errcode").equals(0)) {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            return WxResult.error(errmsg, errcode);
        } else {
            String sessionkey = jsonObject.getString("session_key");
            // 解密
            byte[] encrypData = WxBase64Utils.decodeFromString(encrypted);
            byte[] ivData = WxBase64Utils.decodeFromString(iv);
            byte[] sessionKey = WxBase64Utils.decodeFromString(sessionkey);
            AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivData);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            SecretKeySpec keySpec = new SecretKeySpec(sessionKey, "AES");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
            String resultString = new String(cipher.doFinal(encrypData), "UTF-8");
            JSONObject object = JSONObject.parseObject(resultString);
            WxMobileSp wxMobileSp = new WxMobileSp();
            wxMobileSp.setUnionId(jsonObject.getString("unionid"));
            wxMobileSp.setOpenId(jsonObject.getString("openid"));
            wxMobileSp.setMobile(object.getString("phoneNumber"));
            return WxResult.result(wxMobileSp);
        }
    }

    /**
     * 获取微信人证核验的结果
     *
     * @param accessToken  微信令牌
     * @param verifyResult 验证结果标识 此值由小程序提供
     * @return
     * @throws Exception
     */
    public static WxResult<FaceVerifyResult> faceVerify(String accessToken, String verifyResult) throws Exception {
        if (StrUtil.hasEmpty(accessToken)) throw new Exception("微信令牌不能为空");
        if (StrUtil.hasEmpty(verifyResult)) throw new Exception("验证结果标识不能为空");
        String url = "https://api.weixin.qq.com/cityservice/face/identify/getinfo?access_token=" + accessToken;
        Map<String, String> body = new HashMap<String, String>();
        body.put("verify_result", verifyResult);
        String result = com.graphy.unit.http.Api.POST(url, body, null, null, null);
        if (OwnCommon.isNullOrEmpty(result)) return WxResult.error("请求微信接口返回空");
        JSONObject jsonObject = JSONObject.parseObject(result);
        if (jsonObject.get("errcode") != null && !jsonObject.getInteger("errcode").equals(0)) {
            String errcode = jsonObject.getString("errcode");
            String errmsg = jsonObject.getString("errmsg");
            return WxResult.error(errmsg, errcode);
        } else {
            FaceVerifyResult res = jsonObject.parseObject(result, FaceVerifyResult.class);
            if (!res.getIdentify_ret().equals(0)) {
                return WxResult.error(res.getCheck_msg(), String.valueOf(res.getIdentify_ret()));
            } else {
                return WxResult.result(res);
            }
        }
    }
}
