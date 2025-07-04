package com.graphy.unit.serialize;

import java.io.*;

/**
 * 序列化与反序列化
 */
@SuppressWarnings("all")
public class Api {
   /**
    * 序列化
    *
    * @param object
    * @return
    */
   public static byte[] serialize(Object object) {
      //如果不属于序列化对象，则返回错误
      if (!(object instanceof Serializable)) {
         throw new IllegalArgumentException("不可序列化的对象");
      }
      ObjectOutputStream oos = null;
      ByteArrayOutputStream baos = null;
      try {
         // 序列化
         baos = new ByteArrayOutputStream();
         oos = new ObjectOutputStream(baos);
         oos.writeObject(object);
         byte[] bytes = baos.toByteArray();
         return bytes;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }

   /**
    * 反序列化
    *
    * @param bytes
    * @return
    */
   public static Object deserialize(byte[] bytes) {
      ByteArrayInputStream bais = null;
      try {
         // 反序列化
         bais = new ByteArrayInputStream(bytes);
         ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bais));
         return ois.readObject();
      } catch (Exception e) {
         e.printStackTrace();
      }
      return null;
   }
}
