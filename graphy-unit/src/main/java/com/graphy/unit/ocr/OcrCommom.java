package com.graphy.unit.ocr;

/**
 * ocr公共使用
 */
@SuppressWarnings("all")
public class OcrCommom {
    /**
     * 去除base64前置字符
     *
     * @param base64
     * @return
     */
    public static String cutBase64(String base64) {
        String cut = "base64,";
        Integer cutIndex = base64.indexOf(cut);
        if (cutIndex > -1) {
            base64 = base64.substring(cutIndex + cut.length());
        }
        return base64;
    }
}
