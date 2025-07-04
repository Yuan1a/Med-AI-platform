package com.graphy.unit.crypt;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.MessageDigest;

@SuppressWarnings("all")
public class Api {

    private static byte[] myIV = {50, 51, 52, 53, 54, 55, 56, 57};

    public static void main(String[] args) throws Exception {
//        System.out.println(encrypt("lzcmz@2021", "linshida@own.com.cn!123456789101"));
      System.out.println(decrypt("SVWIFSg9nAIXtdv32tb+zA==", "linshida@own.com.cn!123456789101"));



    }

    /**
     * 加密
     *
     * @param input  被加密字符串
     * @param strkey 密匙
     * @return
     * @throws Exception
     */
    public static String encrypt(String input, String strkey) throws Exception {

        DESedeKeySpec p8ksp = null;
        p8ksp = new DESedeKeySpec(new BASE64Decoder().decodeBuffer(strkey));

        Key key = null;
        key = SecretKeyFactory.getInstance("DESede").generateSecret(p8ksp);

        input = padding(input);
        byte[] plainBytes = null;
        Cipher cipher = null;
        byte[] cipherText = null;

        plainBytes = input.getBytes(StandardCharsets.UTF_8);
        cipher = Cipher.getInstance("DESede/CBC/NoPadding");
        SecretKeySpec myKey = new SecretKeySpec(key.getEncoded(), "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(myIV);
        cipher.init(1, myKey, ivspec);
        cipherText = cipher.doFinal(plainBytes);
        return removeBR(new BASE64Encoder().encode(cipherText));
    }

    /**
     * 解密
     *
     * @param cipherText
     * @param strkey     解密密匙
     * @return
     * @throws Exception
     */
    public static String decrypt(String cipherText, String strkey) throws Exception {
        BASE64Decoder base64d = new BASE64Decoder();
        DESedeKeySpec p8ksp = null;
        p8ksp = new DESedeKeySpec(new BASE64Decoder().decodeBuffer(strkey));
        Key key = null;
        key = SecretKeyFactory.getInstance("DESede").generateSecret(p8ksp);
        Cipher cipher = null;
        byte[] inPut = base64d.decodeBuffer(cipherText);
        cipher = Cipher.getInstance("DESede/CBC/NoPadding");
        SecretKeySpec myKey = new SecretKeySpec(key.getEncoded(), "DESede");
        IvParameterSpec ivspec = new IvParameterSpec(myIV);
        cipher.init(2, myKey, ivspec);
        byte[] output = removePadding(cipher.doFinal(inPut));
        return new String(output, StandardCharsets.UTF_8);

    }

    /**
     * md5加密
     *
     * @param str
     * @return
     */
    public static String md5(String str) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = str.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString().toUpperCase();
    }

    private static String removeBR(String str) {
        StringBuffer sf = new StringBuffer(str);
        for (int i = 0; i < sf.length(); ++i) {
            if (sf.charAt(i) == '\n') {
                sf = sf.deleteCharAt(i);
            }
        }
        for (int i = 0; i < sf.length(); ++i)
            if (sf.charAt(i) == '\r') {
                sf = sf.deleteCharAt(i);
            }
        return sf.toString();
    }

    private static String padding(String str) {
        byte[] oldByteArray;
        oldByteArray = str.getBytes(StandardCharsets.UTF_8);
        int numberToPad = 8 - oldByteArray.length % 8;
        byte[] newByteArray = new byte[oldByteArray.length + numberToPad];
        System.arraycopy(oldByteArray, 0, newByteArray, 0, oldByteArray.length);
        for (int i = oldByteArray.length; i < newByteArray.length; ++i) {
            newByteArray[i] = 0;
        }
        return new String(newByteArray, StandardCharsets.UTF_8);
    }

    private static byte[] removePadding(byte[] oldByteArray) {
        int numberPaded = 0;
        for (int i = oldByteArray.length; i >= 0; --i) {
            if (oldByteArray[(i - 1)] != 0) {
                numberPaded = oldByteArray.length - i;
                break;
            }
        }
        byte[] newByteArray = new byte[oldByteArray.length - numberPaded];
        System.arraycopy(oldByteArray, 0, newByteArray, 0, newByteArray.length);
        return newByteArray;
    }
}
