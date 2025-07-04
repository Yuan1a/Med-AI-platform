package com.graphy.unit.wechat.attr;

import lombok.Data;

@Data
public class FaceVerifyResult {

    /**
     * 错误码, 0表示本次api调用成功
     */
    private Integer errcode;
    /**
     * 本次api调用的错误信息
     */
    private String errmsg;

    /**
     * 人脸核身最终认证结果
     */
    private Integer identify_ret;
    /**
     * 认证时间
     */
    private Integer identify_time;
    /**
     * 用户读的数字（如是读数字）
     */
    private String validate_data;
    /**
     * 用户openid
     */
    private String openid;

    /**
     * 比对结果消息
     */
    private String check_msg;
    /**
     * 用于后台交户表示用户姓名、身份证的凭证
     */
    private String user_id_key;
    /**
     * 认证结束时间
     */
    private Integer finish_time;
    /**
     * 身份证号的md5（最后一位X为大写）
     */
    private String id_card_number_md5;
    /**
     * 姓名MD5
     */
    private String name_utf8_md5;
}
