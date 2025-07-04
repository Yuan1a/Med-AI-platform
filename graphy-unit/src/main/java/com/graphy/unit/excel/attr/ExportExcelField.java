package com.graphy.unit.excel.attr;

/**
 * 导出表格的列配置
 */
public class ExportExcelField {
   public ExportExcelField(String field, String title) {
      this.setField(field);
      this.setTitle(title);
   }

   /**
    * 列字段
    */
   public String field;
   /**
    * 列标题
    */
   public String title;

   public String getField() {
      return field;
   }

   public void setField(String field) {
      this.field = field;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

}
