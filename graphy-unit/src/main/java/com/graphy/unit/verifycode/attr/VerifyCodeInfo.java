package com.graphy.unit.verifycode.attr;

import java.awt.image.BufferedImage;

/**
 * 验证码信息
 */
public class VerifyCodeInfo {
   /**
    * 验证码图片
    */
   public BufferedImage image;
   /**
    * 验证码值
    */
   public String code;

   public BufferedImage getImage() {
      return image;
   }

   public void setImage(BufferedImage image) {
      this.image = image;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }
}
