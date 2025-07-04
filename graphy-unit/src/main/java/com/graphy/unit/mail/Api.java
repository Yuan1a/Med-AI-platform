package com.graphy.unit.mail;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import java.util.List;

/**
 * 邮件发送
 */
@SuppressWarnings("all")
public class Api {
   public static void main(String[] args) throws Exception {

   }

   /**
    * 邮件发送
    *
    * @param from        发送方邮箱
    * @param user        发送方用户
    * @param pass        发送方授权码 登录QQ邮箱->设置->账户->POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务(开启)->复制授权码
    * @param title       内容标题
    * @param content     内容
    * @param receiveMail 接收邮箱地址
    */
   public static void sendQQMail(String from, String user, String pass, String title, String content, List<String> receiveMail) {
      try {
         MailAccount account = new MailAccount();
         account.setHost("smtp.qq.com");
         account.setPort(25);
         account.setAuth(true);
         account.setFrom(from);
         account.setUser(user);
         account.setPass(pass);
         MailUtil.send(account, CollUtil.newArrayList(receiveMail), title, content, false);
      } catch (Exception err) {
         err.printStackTrace();
      }
   }
}
