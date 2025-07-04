package com.graphy.unit.wechat.attr;

import java.util.List;

/**
 * 公众号openId
 */
public class WxbOpenIds {
   public String nextOpenId;
   public List<String> openId;

   public String getNextOpenId() {
      return nextOpenId;
   }

   public void setNextOpenId(String nextOpenId) {
      this.nextOpenId = nextOpenId;
   }

   public List<String> getOpenId() {
      return openId;
   }

   public void setOpenId(List<String> openId) {
      this.openId = openId;
   }
}
