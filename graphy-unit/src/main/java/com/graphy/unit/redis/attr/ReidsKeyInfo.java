package com.graphy.unit.redis.attr;

import lombok.Data;

import java.util.Date;

@Data
public class ReidsKeyInfo {
    /**
     * 键
     */
    private String key;
    /**
     * 值
     */
    private String value;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 过期时间
     */
    private Date expireTime;
}
