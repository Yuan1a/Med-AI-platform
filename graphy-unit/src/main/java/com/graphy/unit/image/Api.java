package com.graphy.unit.image;

import com.graphy.unit.OwnCommon;
import com.graphy.unit.image.attr.Base64FileInfo;
import sun.font.FontDesignMetrics;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Date;
import java.util.UUID;

/**
 * 图片管理
 */
@SuppressWarnings("all")
public class Api {
    /**
     * 根据文件路径获取文件字节
     *
     * @param imagePath
     * @return
     * @throws Exception
     */
    public static byte[] getImageByteByPath(String imagePath) throws Exception {
        try {
            if (imagePath == null || imagePath.equals("")) throw new Exception("文件地址不能为空");
            imagePath = imagePath.replaceAll("\\\\", "/");
            File file = new File(imagePath);
            if (file.exists()) {
                byte[] fileByte = Files.readAllBytes(file.toPath());
                return fileByte;
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 字节转BufferedImage
     *
     * @param bytes
     * @param format
     * @return
     */
    public static BufferedImage bytesToImage(byte[] bytes, String format) throws Exception {
        BufferedImage returnImage = null;
        ByteArrayInputStream in = null;
        try {
            in = new ByteArrayInputStream(bytes);
            BufferedImage image = ImageIO.read(in);
            returnImage = image;
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return returnImage;
    }

    /**
     * 字节转BufferedImage
     *
     * @param bytes
     * @return
     */
    public static BufferedImage bytesToImage(byte[] bytes) throws Exception {
        return bytesToImage(bytes, null);
    }

    /**
     * BufferedImage转字节
     *
     * @param image
     * @param format
     * @return
     */
    public static byte[] imageToBytes(BufferedImage image, String format) {
        byte[] imageBytes = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            boolean flag = ImageIO.write(image, format, out);
            imageBytes = out.toByteArray();
        } catch (Exception err) {
            err.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception err) {
                err.printStackTrace();
            }
        }
        return imageBytes;
    }


    /**
     * 远程加载图片
     *
     * @param imgUrl
     * @return
     */
    public static BufferedImage loadImageByUrl(String imgUrl) throws Exception {
        byte[] result = null;
        InputStream inStream = null;
        BufferedImage bImage = null;
        String photo = "";
        try {
            //创建URL
            URL url = new URL(imgUrl);
            //创建连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5 * 1000);
            inStream = conn.getInputStream();
            int count = conn.getContentLength();//获取远程资源长度
            result = new byte[count];
            int readCount = 0;
            while (readCount < count) {//循环读取数据
                readCount += inStream.read(result, readCount, count - readCount);
            }
            try {
                ByteArrayInputStream in = new ByteArrayInputStream(result);    //将b作为输入流；
                bImage = ImageIO.read(in);
                in.close();
            } catch (Exception err) {
                err.printStackTrace();
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bImage;
    }


    /**
     * 验证图片类型
     *
     * @param fileInfo
     * @param fileType
     * @return
     * @throws Exception
     */
    public static boolean verifyImageType(Base64FileInfo fileInfo, String fileType) throws Exception {
        if (fileType == null || fileType.equals("")) return false;
        if (fileInfo.getType() == null || fileInfo.getType().equals("")) return false;
        if (!fileType.toLowerCase().contains("[" + fileInfo.getType().toLowerCase() + "]")) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * base64转图片
     *
     * @param base64string
     * @return
     */
    public static Base64FileInfo base64ToImage(String base64string) throws Exception {
        Base64FileInfo fi = new Base64FileInfo();
        if (base64string.startsWith("data")) {
            String head = base64string.substring(0, base64string.indexOf(",") + 1);
            String type = head.substring(head.indexOf("/") + 1, head.indexOf(";"));
            fi.setType(type);
            base64string = base64string.substring(base64string.indexOf(",") + 1);
        }
        try {
            BufferedImage bufferedImage = bytesToImage(new BASE64Decoder().decodeBuffer(base64string), fi.getType());
            fi.setFile(bufferedImage);
            byte[] imageByte = imageToBytes(bufferedImage, fi.getType());
            fi.setSize(imageByte.length / 1024);
            fi.setData(imageByte);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fi;
    }

    /**
     * 图片转base64
     *
     * @param image
     * @return
     * @throws Exception
     */
    public static String imageToBase64(BufferedImage image,String fileType) throws Exception {


        ByteArrayOutputStream baos = new ByteArrayOutputStream();//io流
        ImageIO.write(image, fileType, baos);//写入流中
        byte[] bytes = baos.toByteArray();//转换成字节
        String base64 = new BASE64Encoder().encodeBuffer(bytes).trim();//转换成base64串

        base64 = base64.replaceAll("\n", "").replaceAll("\r", "");
        base64 = "data:image/"+fileType+";base64," + base64;
        baos.close();
        return base64;
    }


    /**
     * 保存base64位的图片
     *
     * @param image      图片对象
     * @param baseFolder 图片保存目录
     * @param fileName   文件名称 如果为空则自动生成一个ID
     */
    public static Base64FileInfo base64SaveImage(Base64FileInfo image, String baseFolder, String fileName) throws Exception {
        Date now = new Date();
        String year = com.graphy.unit.date.Api.dateFormat(now, "yyyy");
        String mm = com.graphy.unit.date.Api.dateFormat(now, "MM");
        String dd = com.graphy.unit.date.Api.dateFormat(now, "dd");
        String folder = "/" + year + "/" + mm + "/" + dd + "/";
        File file = new File(baseFolder + folder);
        String fileId = UUID.randomUUID().toString().replaceAll("-", "");
        if (fileName == null || fileName.equals("")) {
            fileId = UUID.randomUUID().toString().replaceAll("-", "");
        } else {
            fileId = fileName;
        }
        if (!file.exists()) {
            file.mkdirs();
        }

        String savePath = folder + fileId + "." + image.getType();
        OutputStream os = new FileOutputStream(baseFolder + savePath);
        os.write(image.getData(), 0, image.getData().length);
        os.flush();
        os.close();
        image.setFileId(fileId);
        image.setLocalPath(savePath);
        return image;
    }


    /**
     * 保存base64位的图片
     *
     * @param base64string 图片base64编码
     * @param baseFolder   图片保存目录
     * @param fileName     文件名称 如果为空则自动生成一个ID
     */
    public static Base64FileInfo base64SaveImage(String base64string, String baseFolder, String fileName) throws Exception {
        Base64FileInfo image = base64ToImage(base64string);
        return base64SaveImage(image, baseFolder, fileName);
    }

    /**
     * 获取文字所占的宽度与高度
     *
     * @param font
     * @param content
     * @return
     */
    private static int[] getGraphicsStringWidthAndHeight(Font font, String content) {
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int width = 0;
        int height = metrics.getHeight();
        for (int i = 0; i < content.length(); i++) {
            width += metrics.charWidth(content.charAt(i));
        }
        return new int[]{width, height};
    }

    /**
     * 图片加水印
     *
     * @param source          源图片字节
     * @param watermark       文字
     * @param sourceImageType 源文件类型(默认jpeg) 例如:jpg,jpeg,png....
     * @param location        位置(默认4=右下角) 0=中心 1=左上角 2=右上角 3=左下角 4=右下角
     * @param fontStyle       文字样式(默认微软雅黑)
     * @param isBold          文字加粗(默认0=否) 0=否 1=是
     * @param fontSize        文字大小(默认32)
     * @return
     * @throws Exception
     */
    public static byte[] imageAddWatermark(byte[] source, String watermark, String sourceImageType, Integer location, String fontStyle, Integer isBold, Integer fontSize) throws Exception {
        if (OwnCommon.isNullOrEmpty(watermark)) return source;
        if (OwnCommon.isNullOrEmpty(sourceImageType)) sourceImageType = "jpeg";
        if (location == null) location = 4;
        if (OwnCommon.isNullOrEmpty(fontStyle)) fontStyle = "微软雅黑";
        if (isBold == null) isBold = 1;
        if (fontSize == null) fontSize = 32;
        Font font = new Font(fontStyle, isBold, fontSize);
        FontDesignMetrics metrics = FontDesignMetrics.getMetrics(font);
        int fontWidth = 0;
        int fontHeight = metrics.getHeight();
        for (int i = 0; i < watermark.length(); i++) {
            fontWidth += metrics.charWidth(watermark.charAt(i));
        }
        BufferedImage sourceImage = bytesToImage(source);
        BufferedImage bufferedImage = new BufferedImage(sourceImage.getWidth(), sourceImage.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = bufferedImage.createGraphics();

        graphics.drawImage(sourceImage, 0, 0, null);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        graphics.setFont(font);
        Integer distance = 40;
        Integer X = null;
        Integer Y = null;
        if (location.equals(1)) {
            X = distance;
            Y = metrics.getAscent() + distance;
        } else if (location.equals(2)) {
            X = bufferedImage.getWidth() - fontWidth - distance;
            Y = metrics.getAscent() + distance;
        } else if (location.equals(3)) {
            X = distance;
            Y = bufferedImage.getHeight() - fontHeight;
        } else if (location.equals(4)) {
            X = bufferedImage.getWidth() - fontWidth - distance;
            Y = bufferedImage.getHeight() - fontHeight;
        } else {
            X = (bufferedImage.getWidth() - fontWidth) / 2;
            Y = (bufferedImage.getHeight() - fontHeight) / 2;
        }
        Integer rectPaddingTB = 5;
        Integer rectPaddingLR = 10;
        graphics.setColor(new Color(169, 169, 169, 128));
        graphics.fillRoundRect(X - rectPaddingLR, Y - metrics.getAscent() - rectPaddingTB, fontWidth + rectPaddingLR * 2, fontHeight + rectPaddingTB * 2, 10, 10);
        graphics.setColor(new Color(255, 255, 255));
        graphics.drawString(watermark, X, Y);
        graphics.dispose();
        return imageToBytes(bufferedImage, sourceImageType);
    }


    public static void main(String[] args) throws Exception {
        byte[] source = com.graphy.unit.image.Api.getImageByteByPath("C:\\Users\\Administrator\\Desktop\\社保卡.jpg");
        byte[] sourceNew = imageAddWatermark(source, "@海南省妇女儿童医学院", "jpeg", 4, "微软雅黑", 1, 20);
        BufferedImage bufferedImage = bytesToImage(sourceNew);
        String base64 = imageToBase64(bufferedImage,"jpeg");
        base64SaveImage(base64, "C:\\Users\\Administrator\\Desktop", "测试一下");

    }


}
