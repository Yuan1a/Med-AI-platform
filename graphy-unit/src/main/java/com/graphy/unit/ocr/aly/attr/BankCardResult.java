package com.graphy.unit.ocr.aly.attr;

/**
 * 银行卡解析结果
 */
public class BankCardResult {
    /**
     * 开户行名称
     */
    public String bankName;
    /**
     * 银行卡号
     */
    public String cardNo;
    /**
     * 有效期
     */
    public String outTime;
    /**
     * 银行卡类型
     */
    public String typeCode;
    /**
     * 银行卡类型名称
     */
    public String typeName;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
