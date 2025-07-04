package com.graphy.unit.image.attr;

import lombok.Data;

/**
 * 图片上传对象
 */
@Data
public class UploadImageInfo {
    /**
     * 图片名称
     */
    private String name;
    /**
     * 状态 1=新图片 0=移除的图片 2=原始图片
     */
    private String status;
    /**
     * 状态名称
     */
    private String statusName;
    /**
     * 图片地址/图片base64
     */
    private String url;
}
