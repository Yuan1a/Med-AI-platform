package com.graphy.unit.excel;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.*;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.excel.attr.ExportExcelField;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 电子表格操作
 */
@SuppressWarnings("all")
public class Api {
   /**
    * 导出电子表格
    *
    * @param response   响应对象
    * @param fileName   表格名称
    * @param dataSource 数据源
    * @param fields     字段
    */
   public static <T> void httpExportExcel(
           HttpServletResponse response,
           String fileName,
           List<T> dataSource,
           List<ExportExcelField> fields,
           Class<T> clazz
   ) throws Exception {
      XSSFWorkbook workbook = new XSSFWorkbook();
      //创建工作表对象
      XSSFSheet sheet = workbook.createSheet();
      //创建工作表的行
      XSSFRow title = sheet.createRow(0);//设置第一行，从零开始
      for (Integer i = 0; i < fields.size(); i++) {
         title.createCell(i).setCellValue(fields.get(i).title);
      }
      Integer rowIndex = 0;
      for (Integer i = 0; i < dataSource.size(); i++) {
         T item = dataSource.get(i);
         Map<String, Object> maps = OwnCommon.getObjectValue(item);
         rowIndex += 1;
         XSSFRow dataRow = sheet.createRow(rowIndex);//设置第一行，从零开始
         for (Integer j = 0; j < fields.size(); j++) {
            if (maps.containsKey(fields.get(j).field)) {
               Object value = maps.get(fields.get(j).field);
               if (value != null) {
                  dataRow.createCell(j).setCellValue(value.toString());
               }
            }
         }
      }
      write(response,workbook,fileName);
   }
   public static XSSFWorkbook getXSSFWorkbook(String sheetName, String[] title, String[][] values, XSSFWorkbook wb, XSSFCellStyle cs) {
      if(wb==null){
         wb = new XSSFWorkbook();
      }
      //创建一个sheet
      XSSFSheet sheet = wb.createSheet(sheetName);
      //创建表头
      XSSFRow row = sheet.createRow(0);
      //设置表格样式
      XSSFCellStyle cellStyle = null;
      if(cs==null){
         cellStyle = wb.createCellStyle();
      }else{
         cellStyle = cs ;
      }
      //设置表头的每一列的值
      XSSFCell cell = null;
      for (int i = 0; i < title.length; i++) {
         cell = row.createCell(i);
         cell.setCellValue(title[i]);
         cell.setCellStyle(cellStyle);
         sheet.setColumnWidth(cell.getColumnIndex(),  256  *  20 );
      }
      //设置每一列的值
      for (int i = 0; i < values.length; i++) {
         row = sheet.createRow(i+1);
         for (int j = 0; j < values[i].length; j++) {
            row.createCell(j).setCellValue(values[i][j]);
         }
      }
      return wb;
   }
   public static void write(HttpServletResponse response,XSSFWorkbook workbook,String fileName) throws IOException {
      response.setContentType("application/x-msdownload");
      response.setCharacterEncoding("utf-8");
      response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
      OutputStream out = response.getOutputStream();
      workbook.write(out);
      out.flush();
      out.close();
   }
}
