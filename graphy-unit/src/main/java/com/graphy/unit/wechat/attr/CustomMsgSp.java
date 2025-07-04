package com.graphy.unit.wechat.attr;
/** 公众号发送自定义文本返回结果 */
public class CustomMsgSp {

  /** 错误编码 0=正常 */
  public Integer errcode;
  /** 错误信息 */
  public String errmsg;

  public Integer getErrcode() {
    return errcode;
  }

  public void setErrcode(Integer errcode) {
    this.errcode = errcode;
  }

  public String getErrmsg() {
    return errmsg;
  }

  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }
}
