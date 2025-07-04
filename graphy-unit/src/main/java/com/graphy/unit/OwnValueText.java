package com.graphy.unit;

import lombok.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Data
public class OwnValueText {
    /**
     * 值
     */
    private String value;
    /**
     * 文本
     */
    private String text;

    public OwnValueText(String value, String text) {
        this.setValue(value);
        this.setText(text);
    }

    public static List<OwnValueText> list(List<?> objList, String valueFieldName, String textFieldName) throws Exception {
        List<OwnValueText> returnValue = new ArrayList<>();
        if (objList != null && objList.size() > 0) {
            for (Integer i = 0; i < objList.size(); i++) {
                Object item = objList.get(i);
                Class<?> obj = item.getClass();
                Field valueField = obj.getDeclaredField(valueFieldName);
                Field textField = obj.getDeclaredField(textFieldName);
                valueField.setAccessible(true);
                textField.setAccessible(true);

                String value = "";
                String text = "";
                if (valueField.getGenericType().equals(Integer.class) || valueField.getGenericType().equals(int.class)) {
                    value = String.valueOf(valueField.getInt(item));
                } else if (valueField.getGenericType().equals(String.class)) {
                    value = valueField.get(item).toString();
                }
                if (textField.getGenericType().equals(Integer.class) || textField.getGenericType().equals(int.class)) {
                    text = String.valueOf(textField.getInt(item));
                } else if (textField.getGenericType().equals(String.class)) {
                    text = textField.get(item).toString();
                }
                returnValue.add(new OwnValueText(value, text));
            }
        }
        return returnValue;

    }

}
