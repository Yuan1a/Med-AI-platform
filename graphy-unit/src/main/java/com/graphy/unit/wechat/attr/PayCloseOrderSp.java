package com.graphy.unit.wechat.attr;

/**
 * 关闭订单后返回的结果
 */
public class PayCloseOrderSp {
   /**
    * 返回状态码
    * <p>SUCCESS/FAIL 此字段是通信标识，非交易标识，交易是否成功需要查看result_code来判断</p>
    */
   public String return_code;
   /**
    * 返回信息
    * <p>返回信息，如非空，为错误原因,签名失败,参数格式校验错误</p>
    */
   public String return_msg;
   /**
    * 小程序ID
    * <p>调用接口提交的小程序ID</p>
    */
   public String appid;
   /**
    * 商户号
    * <p>调用接口提交的商户号</p>
    */
   public String mch_id;
   /**
    * 设备号
    * <p>自定义参数，可以为请求支付的终端设备号等</p>
    */
   public String device_info;
   /**
    * 随机字符串
    * <p>微信返回的随机字符串</p>
    */
   public String nonce_str;

   /**
    * 签名
    * <p>微信返回的签名值</p>
    */
   public String sign;


   /**
    * 业务结果
    * <p>SUCCESS/FAIL</p>
    */
   public String result_code;


   /**
    * 错误代码
    * <p>INVALID_REQUEST	参数错误	参数格式有误或者未按规则上传	订单重入时，要求参数值与原请求一致，请确认参数问题</p>
    * <p>NOAUTH	商户无此接口权限	商户未开通此接口权限	请商户前往申请此接口权限</p>
    * <p>NOTENOUGH	余额不足	用户帐号余额不足	用户帐号余额不足，请用户充值或更换支付卡后再支付</p>
    * <p>ORDERPAID	商户订单已支付	商户订单已支付，无需重复操作	商户订单已支付，无需更多操作</p>
    * <p>ORDERCLOSED	订单已关闭	当前订单已关闭，无法支付	当前订单已关闭，请重新下单</p>
    * <p>SYSTEMERROR	系统错误	系统超时	系统异常，请用相同参数重新调用</p>
    * <p>APPID_NOT_EXIST	APPID不存在	参数中缺少APPID	请检查APPID是否正确</p>
    * <p>MCHID_NOT_EXIST	MCHID不存在	参数中缺少MCHID	请检查MCHID是否正确</p>
    * <p>APPID_MCHID_NOT_MATCH	appid和mch_id不匹配	appid和mch_id不匹配	请确认appid和mch_id是否匹配</p>
    * <p>LACK_PARAMS	缺少参数	缺少必要的请求参数	请检查参数是否齐全</p>
    * <p>OUT_TRADE_NO_USED	商户订单号重复	同一笔交易不能多次提交	请核实商户订单号是否重复提交</p>
    * <p>SIGNERROR	签名错误	参数签名结果不正确	请检查签名参数和方法是否都符合签名算法要求</p>
    * <p>XML_FORMAT_ERROR	XML格式错误	XML格式错误	请检查XML参数格式是否正确</p>
    * <p>REQUIRE_POST_METHOD	请使用post方法	未使用post传递参数 	请检查请求参数是否通过post方法提交</p>
    * <p>POST_DATA_EMPTY	post数据为空	post数据不能为空	请检查post数据是否为空</p>
    * <p>NOT_UTF8	编码格式错误	未使用指定编码格式	请使用UTF-8编码格式</p>
    */
   public String err_code;

   /**
    * 错误代码描述
    */
   public String err_code_des;

   public String getReturn_code() {
      return return_code;
   }

   public void setReturn_code(String return_code) {
      this.return_code = return_code;
   }

   public String getReturn_msg() {
      return return_msg;
   }

   public void setReturn_msg(String return_msg) {
      this.return_msg = return_msg;
   }

   public String getAppid() {
      return appid;
   }

   public void setAppid(String appid) {
      this.appid = appid;
   }

   public String getMch_id() {
      return mch_id;
   }

   public void setMch_id(String mch_id) {
      this.mch_id = mch_id;
   }

   public String getDevice_info() {
      return device_info;
   }

   public void setDevice_info(String device_info) {
      this.device_info = device_info;
   }

   public String getNonce_str() {
      return nonce_str;
   }

   public void setNonce_str(String nonce_str) {
      this.nonce_str = nonce_str;
   }

   public String getSign() {
      return sign;
   }

   public void setSign(String sign) {
      this.sign = sign;
   }

   public String getResult_code() {
      return result_code;
   }

   public void setResult_code(String result_code) {
      this.result_code = result_code;
   }

   public String getErr_code() {
      return err_code;
   }

   public void setErr_code(String err_code) {
      this.err_code = err_code;
   }

   public String getErr_code_des() {
      return err_code_des;
   }

   public void setErr_code_des(String err_code_des) {
      this.err_code_des = err_code_des;
   }
}
