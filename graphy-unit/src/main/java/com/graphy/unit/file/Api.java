package com.graphy.unit.file;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.graphy.unit.OwnCommon;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Date;

/**
 * 文件操作
 */
public class Api {

    /**
     * 获取文件类型
     *
     * @param fileName
     * @return
     */
    public static String getFileType(String fileName) {
        if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        else return "";
    }

    /**
     * 获取文件类型
     *
     * @param file
     * @return
     */
    public static String getFileType(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        return type;
    }

    /**
     * 获取文件大小(单位:kb)
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static Integer getFileSize(MultipartFile file) throws Exception {
        return file.getBytes().length / 1024;
    }

    /**
     * 获取文件大小(单位:kb)
     *
     * @param path 文件路径
     * @return
     * @throws Exception
     */
    public static Integer getFileSize(String path) throws Exception {
        File file = new File(path);
        if (!file.exists()) throw new Exception("不存在【" + path + "】文件路径");
        return (int) file.length() / 1024;
    }

    /**
     * 文件转字节
     *
     * @param filePath
     * @return
     */
    public static byte[] readFileByte(String filePath) throws Exception {
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }

    /**
     * 读取文件
     *
     * @param filePath 文件路径
     * @param encoding 编码 默认:utf-8
     * @return
     * @throws Exception
     */
    public static String readFileString(String filePath, String encoding) throws Exception {
        if (StrUtil.hasEmpty(encoding)) encoding = "utf-8";
        return FileUtil.readString(filePath, encoding);
    }

    /**
     * 验证文件类型
     *
     * @param fileType
     * @param limitType 例如:jpg,jpge,png,zip....
     * @return
     * @throws Exception
     */
    public static boolean verifyFileType(String fileType, String limitType) throws Exception {
        if (StrUtil.hasEmpty(fileType)) return false;
        if (StrUtil.hasEmpty(limitType)) return false;
        if (!limitType.startsWith(",")) limitType = "," + limitType;
        if (!limitType.endsWith(",")) limitType = limitType + ",";
        if (!limitType.toLowerCase().contains("," + fileType.toLowerCase() + ",")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 保存文件
     *
     * @param base64     图片的base64字符 例如: data:image/jpeg;base64,iVBORw0KGgo=..
     * @param baseFolder 基础文件夹路径
     *                   例如:D:/data
     * @param fileFolder 路径文件夹路径
     *                   为空则自动生成文件名
     *                   例如:/2022/03/02
     * @param fileName   保存的文件名
     *                   为空则自动生成文件名,注意不能带后缀名
     * @param limitType  限制文件类型
     *                   为空不限制 例如[jpg][jpge][png][doc][docx].....
     * @param limitSize  限制大小
     *                   为空不限制 单位:kb
     * @return 保存路径
     * @throws Exception
     */
    public static String base64SaveImage(String base64, String baseFolder, String fileFolder, String fileName, String limitType, Long limitSize) throws Exception {
        if (StrUtil.hasEmpty(base64)) throw new Exception("base64字符字符串不能为空");
        if (!base64.startsWith("data:image/")) throw new Exception("base64字符格式错误,正确格式例: \"data:image/jpeg;base64,iVBORw0KGgo=..\"");
        String head = base64.substring(0, base64.indexOf(",") + 1);
        String fileType = head.substring(head.indexOf("/") + 1, head.indexOf(";"));
        if (StrUtil.hasEmpty(fileType)) throw new Exception("图片类型不能为空,正确格式例: \"data:image/jpeg;base64,iVBORw0KGgo=..\"");
        if (!StrUtil.hasEmpty(limitType) && !verifyFileType(fileType, limitType)) throw new Exception("不允许上传[" + fileType + "]类型的文件");
        base64 = base64.substring(base64.indexOf(",") + 1);
        BufferedImage bufferedImage = ImgUtil.toImage(base64);
        byte[] bs = bufferedImageToBytes(bufferedImage, fileType);
        if (limitSize != null && bs.length / 1024 > limitSize) throw new Exception("文件不得大于" + limitSize + "kb");
        if (StrUtil.hasEmpty(fileFolder)) fileFolder = DateUtil.format(new Date(), "/yyyy/MM/dd");
        if (StrUtil.hasEmpty(fileName)) fileName = OwnCommon.getNewId();
        fileName = fileName + "." + fileType;
        if (!new File(baseFolder + fileFolder).exists()) new File(baseFolder + fileFolder).mkdirs();
        String saveFile = fileFolder + "/" + fileName;
        FileUtil.writeBytes(bs, baseFolder + saveFile);
        return saveFile;
    }

    /**
     * BufferedImage转字节
     *
     * @param image
     * @param format
     * @return
     */
    private static byte[] bufferedImageToBytes(BufferedImage image, String format) {
        byte[] imageBytes = null;
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            ImageIO.write(image, format, out);
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
     * 保存文件
     *
     * @param file       上传的文件对象
     * @param baseFolder 基础文件夹路径
     *                   例如:D:/data
     * @param fileFolder 路径文件夹路径
     *                   为空则自动生成文件名
     *                   例如:/2022/03/02
     * @param fileName   保存的文件名
     *                   为空则自动生成文件名,注意不能带后缀名
     * @param limitType  限制文件类型
     *                   为空不限制 例如[jpg][jpge][png][doc][docx].....
     * @param limitSize  限制大小
     *                   为空不限制 单位:kb
     * @return 保存路径
     * @throws Exception
     */
    public static String fileUpload(MultipartFile file, String baseFolder, String fileFolder, String fileName, String limitType, Long limitSize) throws Exception {
        String fileType = getFileType(file);
        if (StrUtil.hasEmpty(baseFolder)) throw new Exception("基础文件夹路径不能为空");
        if (StrUtil.hasEmpty(fileType)) throw new Exception("上传的文件类型不能为空");
        if (!StrUtil.hasEmpty(limitType) && !verifyFileType(fileType, limitType)) throw new Exception("不允许上传[" + fileType + "]类型的文件");
        if (limitSize != null && (int) file.getSize() / 1024 > limitSize) throw new Exception("文件不得大于" + limitSize + "kb");
        if (StrUtil.hasEmpty(fileFolder)) fileFolder = DateUtil.format(new Date(), "/yyyy/MM/dd");
        if (StrUtil.hasEmpty(fileName)) fileName = OwnCommon.getNewId();
        fileName = fileName + "." + fileType;
        if (!new File(baseFolder + fileFolder).exists()) new File(baseFolder + fileFolder).mkdirs();
        byte[] bs = file.getBytes();
        String saveFile = fileFolder + "/" + fileName;
        FileUtil.writeBytes(bs, baseFolder + saveFile);
        return saveFile;
    }

    /**
     * 打开文件
     *
     * @param localPath 本地文件路径
     * @param response  响应对象
     */
    public static void openFile(String localPath, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(localPath);
            if (!file.exists()) return;
            String type = getFileType(localPath);
            openFileResponse(FileUtil.readBytes(file), type, response);
        } catch (IOException ex) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 打开文件-传输文件
     *
     * @param buffer   文件数据
     * @param type     文件类型
     * @param response 响应对象
     */
    public static void openFileResponse(byte[] buffer, String type, HttpServletResponse response) throws Exception {
        String videoType = "avi,mov,rmvb,rm,flv,mp4,3gp";
        String imageType = "jpg,jpeg,png,gif,ico,bmp";
        if (("," + videoType + ",").contains("," + type + ",")) {
            response.setContentType("video/" + type + ""); // 设置返回的文件类型
            response.addHeader("Content-Type", "audio/" + type + ";charset=UTF-8");
            response.getOutputStream().write(buffer);
            response.flushBuffer();
        } else if (("," + imageType + ",").contains("," + type + ",")) {
            response.setContentType("image/" + type + ""); // 设置返回的文件类型
            response.addHeader("Content-Type", "image/" + type + ";charset=UTF-8");
            response.getOutputStream().write(buffer);
            response.flushBuffer();
        } else if (type.equals("pdf")) {
            response.setContentType("application/pdf"); // 设置返回的文件类型
            response.addHeader("Content-Type", "application/pdf");
            response.getOutputStream().write(buffer);
            response.flushBuffer();
        } else {
            response.setContentType("text/html"); // 设置返回的文件类型
            response.addHeader("Content-Type", "text/html;charset=UTF-8");
            response.getOutputStream().write((type + "文件类型不支持在线预览").getBytes());
        }
    }


    /**
     * 下载文件
     *
     * @param localPath 本地文件路径
     * @param fileName  下载下载传输到客户端的文件名
     * @param response  响应对象
     */
    public static void downloadFile(String localPath, String fileName, HttpServletResponse response) {
        try {
            // path是指欲下载的文件的路径。
            File file = new File(localPath);
            if (!file.exists()) return;
            if (StrUtil.hasEmpty(fileName)) fileName = OwnCommon.getNewId();
            String fileType = localPath.substring(localPath.lastIndexOf(".") + 1);
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(localPath));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            downloadFileResponse(fileName, buffer, fileType, response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 下载文件-传输
     *
     * @param fileName 客户端下载的文件名称
     * @param buffer   文件数据
     * @param fileType 文件类型
     * @param response 响应对象
     */
    public static void downloadFileResponse(String fileName, byte[] buffer, String fileType, HttpServletResponse response) {
        try {

            if (StrUtil.hasEmpty(fileName)) fileName = OwnCommon.getNewId();
            response.reset();
            // 设置response的Header
            if (!fileName.endsWith("." + fileType)) fileName += "." + fileType;
            response.addHeader("Content-FileNameFull", new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            response.addHeader("Content-FileName", new String(fileName.replace("." + fileType, "").getBytes("UTF-8"), "ISO-8859-1"));
            response.addHeader("Content-FileType", fileType);
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1"));
            response.addHeader("Content-Length", "" + buffer.length);
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


}
