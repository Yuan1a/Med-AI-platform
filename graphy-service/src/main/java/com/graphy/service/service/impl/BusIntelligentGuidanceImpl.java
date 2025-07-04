package com.graphy.service.service.impl;
import com.alibaba.fastjson.JSONObject;
import com.graphy.service.bean.param.IntelligentImageAnalysiParam;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.db.entity.TbBusPatientEntity;
import com.graphy.service.bean.dto.IllnessStatisticsDto;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.IllnessStatisticsParam;
import com.graphy.service.service.BusPatientService;
import com.graphy.unit.image.Api;
import com.graphy.unit.image.attr.Base64FileInfo;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusIntelligentGuidanceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.graphy.service.service.BusIntelligentGuidanceService;
import lombok.extern.slf4j.Slf4j;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.imageio.ImageIO;

/**
 * @auther qwt
 * @create 2023-06-15 09:06:08
 * @describe 智能导诊分析
 */
@Service("com.graphy.service.service.impl.busintelligentguidanceimpl")
@RequiredArgsConstructor
@Slf4j
public class BusIntelligentGuidanceImpl implements BusIntelligentGuidanceService {

    private final BusIntelligentGuidanceMapper busIntelligentGuidanceMapper;

    private final BusPatientService busPatientService;

    /**
     * auther： qwt
     * create： 2023-06-15 09:41:48
     * describe： 获取病种信息统计详情
     * @param param   参数
     * @return
     */
    public OwnResult<List<IllnessStatisticsDto>> getIllnessStatistics(IllnessStatisticsParam param) throws Exception {
        List<IllnessStatisticsDto> list = busIntelligentGuidanceMapper.getIllnessStatistics(param);
        for (IllnessStatisticsDto illnessStatisticsDto : list) {
            List<TbBusPatientEntity> tbBusPatientEntities = busPatientService.list(new LambdaQueryWrapper<TbBusPatientEntity>().eq(TbBusPatientEntity::getStatus, "1").eq(TbBusPatientEntity::getIllnessId, illnessStatisticsDto.getIllnessId()));
            illnessStatisticsDto.setNumber(tbBusPatientEntities.size());
        }
        return OwnResult.result(list);
    }

    /**
    * auther： qwt
    * create： 2023-11-15 11:56:00
    * describe： 影像图片智能分析
    * @param param   参数
    * @return
    */
    public OwnResult<String> intelligentImageAnalysi(IntelligentImageAnalysiParam param) throws Exception {
        /* String requestUrl="http://pacs.micro-recruit.com:6080/process_image";*/
        String requestUrl="http://175.178.128.43:3333/image";
        String base64ImageString  = param.getFile().substring(param.getFile().indexOf(",") + 1);
        byte[] imageBytes = Base64.getDecoder().decode(base64ImageString);
        ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);

        // 读取图片
        BufferedImage image = ImageIO.read(bis);
        bis.close();

        // 写入JPEG文件
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", bos);
        // 转回Base64
        byte[] imageBytesJpeg = bos.toByteArray();
        String base64ImageStringJpeg = "data:image/jpeg;base64,"+Base64.getEncoder().encodeToString(imageBytesJpeg);

        JSONObject jsonObject = this.requestIntelligentImageAnalysi(requestUrl, base64ImageStringJpeg);
        String img = String.valueOf(jsonObject.get("img"));
        return OwnResult.result(img);
    }
    public  JSONObject requestIntelligentImageAnalysi(String requestUrl,String base64) throws Exception {

            // 创建 URL 对象
            URL url = new URL(requestUrl);

            // 打开连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 设置请求方法为 POST
            conn.setRequestMethod("POST");

            // 设置请求头的内容类型为 application/json
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");

            // 允许输出
            conn.setDoOutput(true);

            // Base64 字符串
            String base64String = base64;

            // 获取输出流
            OutputStream os = conn.getOutputStream();
            os.write(base64String.getBytes("UTF-8"));
            os.close();

            // 获取响应码
            int responseCode = conn.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode);
            JSONObject jsonObject=null;
            // 如果响应码为 200（HTTP_OK），则读取响应
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // 打印响应
//                System.out.println("Response :: " + response.toString());
                jsonObject = JSONObject.parseObject(response.toString());
            }

            conn.disconnect();
           return jsonObject;

    }
}