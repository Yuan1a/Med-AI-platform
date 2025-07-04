package com.graphy.unit.wechat.attr;

/**
 * 支付-申请退款
 */
public class PayRefundSp {
   /**
    * 返回状态码
    * <p>SUCCESS/FAIL</p>
    */
   public String return_code;
   /**
    * 返回信息
    * <p>返回信息，如非空，为错误原因 </p>
    * <p>签名失败</p>
    * <p>参数格式校验错误</p>
    */
   public String return_msg;
   /**
    * 业务结果
    * <p>SUCCESS/FAIL</p>
    * <p>SUCCESS退款申请接收成功，结果通过退款查询接口查询</p>
    * <p>FAIL 提交业务失败</p>
    */
   public String result_code;
   /**
    * 错误代码
    */
   public String err_code;
   /**
    * 错误代码描述
    */
   public String err_code_des;

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
}
