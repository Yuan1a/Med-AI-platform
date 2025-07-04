package com.graphy.unit.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

/**
 * 生成二维码
 */
@SuppressWarnings("all")
public class Api {
   private static final int BLACK = 0xFF000000;//颜色
   private static final int WHITE = 0xFFFFFFFF; //背景色
   private static final String FOMART = ".jpg";
   private static final String yyyyMMddFormat = "yyyy-MM-dd";//用于当天时间格式创建dir目录
   private static final Object OBJ = new Object();

   /**
    * 生成二维码字节
    *
    * @param content 内容
    * @param width   宽度
    * @param height  高度
    * @param logo    中间的图片
    * @return
    * @throws Exception
    */
   public static byte[] getQrCode(String content, Integer width, Integer height, BufferedImage logo) throws Exception {
      //如果存储大小的不为空，那么对我们图片的大小进行设定
    /*   if(size.length==2){
    	   width=size[0];
    	   height=size[1];
       }else if(size.length==1){
          width=height=size[0];
       }*/
      //二维码参数设置-- 纠错级别（L 7%、M 15%、Q 25%、H 30%）--字符集编码
      Hashtable<EncodeHintType, Object> setMap = new Hashtable<EncodeHintType, Object>();
      setMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      setMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
      setMap.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数
      //hints.put(EncodeHintType.MAX_SIZE, 350);// 图片的最大值
      //hints.put(EncodeHintType.MIN_SIZE, 100);// 图片的最小值
      //构造矩形--要编码的内容，编码类型，宽度，高度，生成条形码时的一些配置,此项可选
      BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
              BarcodeFormat.QR_CODE, //编码类型 ：
              width, // 宽度
              height, // 高度
              setMap);
      BufferedImage bufferedImage = toBufferedImage(bitMatrix);
      bufferedImage = setMatrixLogo(bufferedImage, logo);
      byte[] imageBytes = com.graphy.unit.image.Api.imageToBytes(bufferedImage, "jpg");
      return imageBytes;
   }

   /**
    * 生成二维码字节
    *
    * @param content 内容
    * @param width   宽度
    * @param height  高度
    * @param logo    中间的图片
    * @return
    * @throws Exception
    */
   public static BufferedImage getQrCode_Image(String content, Integer width, Integer height, BufferedImage logo) throws Exception {

      //二维码参数设置-- 纠错级别（L 7%、M 15%、Q 25%、H 30%）--字符集编码
      Hashtable<EncodeHintType, Object> setMap = new Hashtable<EncodeHintType, Object>();
      setMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      setMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
      setMap.put(EncodeHintType.MARGIN, 4);//设置二维码边的空度，非负数

      //构造矩形--要编码的内容，编码类型，宽度，高度，生成条形码时的一些配置,此项可选
      BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
              BarcodeFormat.QR_CODE, //编码类型 ：
              width, // 宽度
              height, // 高度
              setMap);
      bitMatrix = deleteWhite(bitMatrix);

      BufferedImage bufferedImage = toBufferedImage(bitMatrix);
      bufferedImage = setMatrixLogo(bufferedImage, logo);
      return bufferedImage;
   }

   public static BitMatrix deleteWhite(BitMatrix matrix) {
      int[] rec = matrix.getEnclosingRectangle();
      int resWidth = rec[2] + 1;
      int resHeight = rec[3] + 1;

      BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
      resMatrix.clear();
      for (int i = 0; i < resWidth; i++) {
         for (int j = 0; j < resHeight; j++) {
            if (matrix.get(i + rec[0], j + rec[1]))
               resMatrix.set(i, j);
         }
      }
      return resMatrix;
   }

   /**
    * @Author:tuzhi-cai
    * @Description: 二维码构画uploadAndCreateAndReuturnPath
    * @time:2018年2月2日下午2:03:26
    */
   private static BufferedImage toBufferedImage(BitMatrix matrix) {
      int width = matrix.getWidth();
      int height = matrix.getHeight();
      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
      for (int x = 0; x < width; x++) {
         for (int y = 0; y < height; y++) {
            image.setRGB(x, y, (matrix.get(x, y) ? BLACK : WHITE));
         }
      }
      return image;
   }


   /**
    * @throws Exception
    * @throws IOException
    * @Author:tuzhi-cai（参数说明：用于匹配修改服务器时获取动态访问路径问题）
    * @Param1 fielname ：生成二维码文件名称
    * @Param2 url ：二维码参数地址
    * @Param2.2 logUrl :中间log图片的路径 (可为null)
    * @Param3 serverMapping:server服务器虚拟路径映射路径
    * @param4 truePAth: 映射服务器的真实目录路径
    * @Param4 serverIp: 服务器的域名地址
    * @param5 request : 当前请求的request，用于获取端口号，
    * @Param6 size : 二维码宽度和高度 （可为null）
    * @Description:
    * @time:2018年2月3日上午1:34:23
    */
   private static String uploadAndCreateAndReuturnPath(String filename, String url, String logUrl, String serverMappingPath,
                                                       String truePath, String serverIp, HttpServletRequest request, int[] size) throws IOException, Exception {
      String serverPath = "http://" + serverIp + ":" + request.getLocalPort() + serverMappingPath;//拼接服务器访问地址
      SimpleDateFormat sdf = new SimpleDateFormat(yyyyMMddFormat); //将当天的日期作为新的文件夹
      Date currDay = new Date();
      String currentDate = sdf.format(currDay) + "/";
      String savePath = truePath + currentDate;//拼接要保存的实际文件路径
      String lastName = filename + FOMART;
      File file = new File(savePath);
      //判断当前日期文件夹是否存在不存在就创建
      synchronized (OBJ) {
         if (!file.exists()) {
            file.mkdirs();
         }
      }
      //调用方法创建返回访问url
      creatQrImage(url, savePath + lastName, logUrl, size);
      return serverPath + currentDate + lastName;
   }

   /**
    * 二维码参数设定
    *
    * @param content    二维码内url/内容
    * @param outFileUri 二维码的生成地址
    * @param logUri     二维码中间logo的地址
    * @param size       用于设定图片大小（可变参数，宽，高）
    */
   private static void creatQrImage(String content, String outFileUri, String logUri, int[] size) throws IOException, WriterException {
      int width = 430; // 二维码图片宽度 430
      int height = 430; // 二维码图片高度430
      //如果存储大小的不为空，那么对我们图片的大小进行设定
    /*   if(size.length==2){
    	   width=size[0];
    	   height=size[1];
       }else if(size.length==1){
          width=height=size[0];
       }*/
      //二维码参数设置-- 纠错级别（L 7%、M 15%、Q 25%、H 30%）--字符集编码
      Hashtable<EncodeHintType, Object> setMap = new Hashtable<EncodeHintType, Object>();
      setMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
      setMap.put(EncodeHintType.CHARACTER_SET, "utf-8");
      setMap.put(EncodeHintType.MARGIN, 1);//设置二维码边的空度，非负数
      //hints.put(EncodeHintType.MAX_SIZE, 350);// 图片的最大值
      //hints.put(EncodeHintType.MIN_SIZE, 100);// 图片的最小值
      //构造矩形--要编码的内容，编码类型，宽度，高度，生成条形码时的一些配置,此项可选
      BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
              BarcodeFormat.QR_CODE, //编码类型 ：
              width, // 宽度
              height, // 高度
              setMap);
      // 构造要生成路径的file对象
      File outputFile = new File(outFileUri);
      writeToFile(bitMatrix, outputFile, logUri);
   }


   /**
    * @Author:tuzhi-cai
    * @Description:生成二维码的方法，根据File对象
    * @time:2018年2月2日下午2:00:19
    */
   private static void writeToFile(BitMatrix matrix, File file, String logUri) throws IOException {
      BufferedImage image = toBufferedImage(matrix);//new一个Image对象，对当前的矩阵对象进行绘画
      //设置logo图标,返回带log的二维码,判断是否需要设置二维码的logo
      if (logUri != null && logUri != "") {
         image = setMatrixLogo(image, logUri);//设置logo图标,返回带log的二维码
      }
      Boolean isSuccess = ImageIO.write(image, "jpg", file);
      if (!isSuccess) {
         throw new IOException("二维码生成失败");
      }
   }

   /**
    * @Author:tuzhi-cai
    * @Description: 根据输出流对象
    * @time:2018年2月2日下午2:14:09
    */
   private static void writeToStream(BitMatrix matrix, OutputStream stream, String logUri) throws IOException {
      BufferedImage image = toBufferedImage(matrix);
      //设置logo图标,返回带log的二维码,判断是否需要设置二维码的logo
      if (logUri != null && logUri != "") {
         image = setMatrixLogo(image, logUri);
      }
      Boolean isSuccess = ImageIO.write(image, "jpg", stream);
      if (isSuccess) {
         throw new IOException("二维码生成失败");
      }
   }


   /**
    * @param matrixImage 生成的二维码
    * @param logUri      logo地址
    * @return 带有logo的二维码
    * @Author:tuzhi-cai
    * @Description: 传递二维码，设置带logo的二维码
    * @time:2018年2月2日下午1:58:01
    */
   private static BufferedImage setMatrixLogo(BufferedImage matrixImage, String logUri) throws IOException {
      BufferedImage logo = ImageIO.read(new File(logUri));//读取logo图片
      return setMatrixLogo(matrixImage, logo);

   }

   /**
    * @param matrixImage 生成的二维码
    * @param logo        logo图片
    * @return 带有logo的二维码
    * @Author:tuzhi-cai
    * @Description: 传递二维码，设置带logo的二维码
    * @time:2018年2月2日下午1:58:01
    */
   private static BufferedImage setMatrixLogo(BufferedImage matrixImage, BufferedImage logo) throws IOException {
      /**
       * 读取二维码图片，并构建绘图对象
       */
      Graphics2D g2 = matrixImage.createGraphics();
      int matrixWidth = matrixImage.getWidth();
      int matrixHeigh = matrixImage.getHeight();
      //开始绘制图片前两个、/5*2/5*2 后/5
      g2.drawImage(logo, matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, null);
      //绘制边框
      BasicStroke stroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
      // 设置笔画对象
      g2.setStroke(stroke);
      //指定弧度的圆角矩形
      RoundRectangle2D.Float round = new RoundRectangle2D.Float(matrixWidth / 5 * 2, matrixHeigh / 5 * 2, matrixWidth / 5, matrixHeigh / 5, 20, 20);
      g2.setColor(Color.white);
      // 绘制圆弧矩形
      g2.draw(round);

      //设置logo 有一道灰色边框
      BasicStroke stroke2 = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);
      // 设置笔画对象
      g2.setStroke(stroke2);
      RoundRectangle2D.Float round2 = new RoundRectangle2D.Float(matrixWidth / 5 * 2 + 2, matrixHeigh / 5 * 2 + 2, matrixWidth / 5 - 4, matrixHeigh / 5 - 4, 20, 20);
      g2.setColor(new Color(128, 128, 128));
      g2.draw(round2);// 绘制圆弧矩形
      g2.dispose(); //执行刷出返回带logo二维码
      matrixImage.flush();
      return matrixImage;
   }

   public static void main(String[] args) throws Exception {
      BufferedImage logo = com.graphy.unit.image.Api.loadImageByUrl("https://img.lanrentuku.com/img/allimg/1502/14227487064502.jpg");
      byte[] qrCode = getQrCode("http://www.baidu.com", 380, 380, logo);
      FileOutputStream fout = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\wwwsss.jpg");
      fout.write(qrCode);
      fout.close();

   }
}
