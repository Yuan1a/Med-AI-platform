package com.graphy.unit.image.attr;

import java.awt.image.BufferedImage;

public class Base64FileInfo {
   private String type;
   private Integer size;
   private byte[] data;

   private BufferedImage file;
   /**
    * 文件ID 只有保存文件的时候才有值
    */
   private String fileId;
   /**
    * 本地保存路径 只有保存文件的时候才有值
    */
   private String localPath;

   public String getFileId() {
      return fileId;
   }

   public void setFileId(String fileId) {
      this.fileId = fileId;
   }

   public BufferedImage getFile() {
      return file;
   }

   public void setFile(BufferedImage file) {
      this.file = file;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public Integer getSize() {
      return size;
   }

   public void setSize(Integer size) {
      this.size = size;
   }

   public byte[] getData() {
      return data;
   }

   public void setData(byte[] data) {
      this.data = data;
   }

   public String getLocalPath() {
      return localPath;
   }

   public void setLocalPath(String localPath) {
      this.localPath = localPath;
   }
}
