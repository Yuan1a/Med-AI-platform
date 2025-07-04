package com.graphy.unit.wechat.attr;

import java.util.Date;

/**
 * 微信支付订单对象
 */
public class PayOrderQuerySp {
   /**
    * 用户在商户appid下的唯一标识
    */
   public String openid;
   /**
    * 用户是否关注公众账号
    * <p>Y-关注，N-未关注</p>
    */
   public String is_subscribe;
   /**
    * 调用接口提交的交易类型
    * <p>取值如下：JSAPI，NATIVE，APP，MICROPAY，详细说明见参数规定</p>
    */
   public String trade_type;
   /**
    * 交易状态
    * <p>SUCCESS—支付成功</p>
    * <p>REFUND—转入退款</p>
    * <p>NOTPAY—未支付</p>
    * <p>CLOSED—已关闭</p>
    * <p>REVOKED—已撤销（刷卡支付）</p>
    * <p>USERPAYING--用户支付中</p>
    * <p>PAYERROR--支付失败(其他原因，如银行返回失败)</p>
    */
   public String trade_state;
   /**
    * 付款银行 CMC	银行类型，采用字符串类型的银行标识
    */
   public String bank_type;
   /**
    * 订单总金额，单位为分
    */
   public Integer total_fee;
   /**
    * 标价币种 CNY	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
    */
   public String fee_type;
   /**
    * 现金支付金额订单现金支付金额
    */
   public Integer cash_fee;
   /**
    * 现金支付币种
    * <p>货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见</p>
    */
   public String cash_fee_type;


   /**
    * 微信支付订单号
    */
   public String transaction_id;

   /**
    * 商户订单号
    * <p>商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一</p>
    */
   public String out_trade_no;
   /**
    * 附加数据
    */
   public String attach;
   /**
    * 订单支付时间
    * <p>格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见</p>
    */
   public Date time_end;
   /**
    * 交易状态描述
    * <p>对当前查询订单状态的描述和下一步操作的指引</p>
    */
   public String trade_state_desc;

   public String getOpenid() {
      return openid;
   }

   public void setOpenid(String openid) {
      this.openid = openid;
   }

   public String getIs_subscribe() {
      return is_subscribe;
   }

   public void setIs_subscribe(String is_subscribe) {
      this.is_subscribe = is_subscribe;
   }

   public String getTrade_type() {
      return trade_type;
   }

   public void setTrade_type(String trade_type) {
      this.trade_type = trade_type;
   }

   public String getTrade_state() {
      return trade_state;
   }

   public void setTrade_state(String trade_state) {
      this.trade_state = trade_state;
   }

   public String getBank_type() {
      return bank_type;
   }

   public void setBank_type(String bank_type) {
      this.bank_type = bank_type;
   }

   public Integer getTotal_fee() {
      return total_fee;
   }

   public void setTotal_fee(Integer total_fee) {
      this.total_fee = total_fee;
   }

   public String getFee_type() {
      return fee_type;
   }

   public void setFee_type(String fee_type) {
      this.fee_type = fee_type;
   }

   public Integer getCash_fee() {
      return cash_fee;
   }

   public void setCash_fee(Integer cash_fee) {
      this.cash_fee = cash_fee;
   }

   public String getCash_fee_type() {
      return cash_fee_type;
   }

   public void setCash_fee_type(String cash_fee_type) {
      this.cash_fee_type = cash_fee_type;
   }

   public String getTransaction_id() {
      return transaction_id;
   }

   public void setTransaction_id(String transaction_id) {
      this.transaction_id = transaction_id;
   }

   public String getOut_trade_no() {
      return out_trade_no;
   }

   public void setOut_trade_no(String out_trade_no) {
      this.out_trade_no = out_trade_no;
   }

   public String getAttach() {
      return attach;
   }

   public void setAttach(String attach) {
      this.attach = attach;
   }

   public Date getTime_end() {
      return time_end;
   }

   public void setTime_end(Date time_end) {
      this.time_end = time_end;
   }

   public String getTrade_state_desc() {
      return trade_state_desc;
   }

   public void setTrade_state_desc(String trade_state_desc) {
      this.trade_state_desc = trade_state_desc;
   }
}
