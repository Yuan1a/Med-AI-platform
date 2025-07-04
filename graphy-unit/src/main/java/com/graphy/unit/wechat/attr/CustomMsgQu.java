package com.graphy.unit.wechat.attr;

/**
 * 公众号发送自定义文本
 */
public class CustomMsgQu {
    public String touser;
    public String msgtype;
    public CustomMsgTypeQu text;
    public CustomMsgTypeQu image;
    public String getTouser() {
        return touser;
    }

    public CustomMsgTypeQu getText() {
        return text;
    }

    public void setText(CustomMsgTypeQu text) {
        this.text = text;
    }

    public CustomMsgTypeQu getImage() {
        return image;
    }

    public void setImage(CustomMsgTypeQu image) {
        this.image = image;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }
}
