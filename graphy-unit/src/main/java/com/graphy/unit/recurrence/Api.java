package com.graphy.unit.recurrence;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 递归类生成树形结构
 */
@SuppressWarnings("all")
public class Api {

   /**
    * 递归创建父子结构
    *
    * @param source         数据源
    * @param idFieldName    主键ID字段名称
    * @param pidFieldName   父字段名称
    * @param childFieldName 子节点字段名称
    * @param rootPidValue   跟节点的父节点值 默认=0
    * @param <T>
    * @return
    */
   public static <T> List<T> recurrence(List<T> source, String idFieldName, String pidFieldName, String childFieldName, String rootPidValue) throws Exception {
      if (rootPidValue == null) rootPidValue = "0";
      List<T> returnValue = recurrence_getChildren(source, pidFieldName, rootPidValue);
      for (T item : returnValue) {
         recurrence_buidChildren(item, source, idFieldName, pidFieldName, childFieldName);
      }
      return returnValue;

   }

   /**
    * 递归创建父子结构-对象属性
    *
    * @param cla
    * @param fieldName
    * @return
    * @throws Exception
    */
   private static Field recurrence_getField(Class<?> cla, String fieldName) throws Exception {
      Integer toTopLayer = 4;
      Field field = cla.getField(fieldName);
      while (field == null && toTopLayer > 0) {
         cla = cla.getSuperclass();
         field = cla.getField(fieldName);
         toTopLayer--;
      }
      if (field != null) {
         field.setAccessible(true);
      }
      return field;
   }

   /**
    * 递归创建父子结构-获取字段数据
    *
    * @param field
    * @param obj
    * @return
    * @throws Exception
    */
   private static String recurrence_getFieldValue(Field field, Object obj) throws Exception {
      field.setAccessible(true);
      String value = "";
      if (field.getGenericType().equals(Integer.class) || field.getGenericType().equals(int.class)) {
         value = String.valueOf(field.getInt(obj));
      } else if (field.getGenericType().equals(String.class)) {
         value = field.get(obj).toString();
      } else {
         throw new Exception("无效的递归依据");
      }
      return value;
   }

   /**
    * 递归创建父子结构-向下递归
    *
    * @param item         父节点对象
    * @param source       数据源
    * @param pidFieldName 父字段名称
    * @param <T>
    * @return
    */
   private static <T> void recurrence_buidChildren(T item, List<T> source, String idFieldName, String pidFieldName, String childFieldName) throws Exception {
      Field fieldChildren = recurrence_getField(item.getClass(), childFieldName);
      String pid = recurrence_getFieldValue(recurrence_getField(item.getClass(), idFieldName), item);
      List<T> children = recurrence_getChildren(source, pidFieldName, pid);
      if (children != null && children.size() > 0) {
         for (T tc : children) {
            recurrence_buidChildren(tc, source, idFieldName, pidFieldName, childFieldName);
         }
      }
      fieldChildren.set(item, children);
   }

   /**
    * 递归创建父子结构-根据父节点值获取下级子节点列表
    *
    * @param source       数据源
    * @param pidFieldName 父字段名称
    * @param pidValue     父节点值
    * @param <T>
    * @return
    */
   private static <T> List<T> recurrence_getChildren(List<T> source, String pidFieldName, String pidValue) throws Exception {
      List<T> returnRows = new ArrayList<T>();
      for (T item : source) {
         Field field = recurrence_getField(item.getClass(), pidFieldName);
         field.setAccessible(true);
         String value = recurrence_getFieldValue(field, item);
         if (value.equals(pidValue)) returnRows.add(item);
      }
      return returnRows;
   }

}
