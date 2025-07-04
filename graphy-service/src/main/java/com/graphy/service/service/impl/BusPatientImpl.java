package com.graphy.service.service.impl;
import com.graphy.service.bean.dto.NameDto;
import com.graphy.service.bean.param.NameParam;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.graphy.blockchain.BlockChainApi;
import com.graphy.config.BaseConfig;
import com.graphy.db.entity.*;
import com.graphy.db.service.NameService;
import com.graphy.db.service.TbBusPortraitReportImagesService;
import com.graphy.service.bean.dto.OnChainDataDto;
import com.graphy.service.bean.param.SavePatientRecordParam;
import com.graphy.service.bean.dto.PatientRecordDto;
import com.graphy.service.bean.OwnPageResult;
import com.graphy.service.bean.OwnResult;
import com.graphy.service.bean.param.PatientRecordParam;
import com.graphy.service.service.BusPortraitReportService;
import com.graphy.unit.OwnCommon;
import com.graphy.unit.crypt.Api;
import lombok.var;
import org.apache.http.entity.ContentType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusPatientMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusPatientMapper;
import com.graphy.service.service.BusPatientService;
import lombok.extern.slf4j.Slf4j;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.util.StrUtil;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @auther lsd
 * @create 2022-03-01 11:22:06
 * @describe 患者管理
 */
@Service("com.graphy.service.service.impl.buspatientimpl")
@RequiredArgsConstructor
@Slf4j
public class BusPatientImpl extends ServiceImpl<TbBusPatientMapper, TbBusPatientEntity> implements BusPatientService {

    private final BusPatientMapper busPatientMapper;

    private final BusPortraitReportService busPortraitReportService;

    private final TbBusPortraitReportImagesService tbBusPortraitReportImagesService;

    @Value("${storage-host}")
    private String storageHost;
    @Value("${pro.file-folder}")
    private String fileFolder;
    private final NameService nameService;

    /**
     * auther： lsd
     * create： 2022-03-01 11:54:13
     * describe： 获取患者信息
     * @param param   参数
     * @return
     */
    public OwnResult<OwnPageResult<PatientRecordDto>> getPatientRecord(PatientRecordParam param) throws Exception {
        this.savePatientRecord1();
//        this.importPatient();
        IPage<PatientRecordDto> pageResult = busPatientMapper.getPatientInfo(new Page<>(param.getPage(), param.getSize()), param);
        return OwnResult.result(new OwnPageResult<>(pageResult.getTotal(), pageResult.getSize(), pageResult.getCurrent(), pageResult.getRecords()));
    }

    /**
     * auther： qwt
     * create： 2022-03-01 11:54:46
     * describe： 保存患者信息
     * @param param   参数
     * @return
     */
    public OwnResult<Boolean> savePatientRecord(SavePatientRecordParam param) throws Exception {
        if (StrUtil.hasEmpty(param.getGender()))
            return OwnResult.error("请设置性别");
        if (param.getAge() == null)
            return OwnResult.error("请设置年龄");
        // if ( "1".equals(param.getDiagnosisStatus())){
        // return OwnResult.error("患者已完成诊断无法修改患者信息");
        // }
//        if (StrUtil.hasEmpty(param.getCrisisValue())) {
//            return OwnResult.error("请设置患者危机值");
//        }
        // if (StrUtil.hasEmpty(param.getDiagnosisDescribe())){
        // return OwnResult.error("请填写诊断描述");
        // }
        if (StrUtil.hasEmpty(param.getDiagnosisDoctor())) {
            return OwnResult.error("请填写报告医生");
        }
        if (param.getDiagnosisTime() == null) {
            return OwnResult.error("请设置报告时间");
        }
        if (StrUtil.hasEmpty(param.getDiagnosisUnit())) {
            return OwnResult.error("请选择患者来源");
        }
//        if (param.getFiles().isEmpty()) {
//            return OwnResult.error("请上传患者影像图片");
//        }
        // Boolean isIdNull= false;
        TbBusPatientEntity orm = JSONObject.parseObject(JSONObject.toJSONString(param), TbBusPatientEntity.class);
        // orm.setGender(param.getGender());
        // orm.setAge(param.getAge());
        // orm.setDiagnosisStatus("0");
        // orm.setCrisisValue(param.getCrisisValue());
        // orm.setRadiationId(param.getRadiationId());
        if (StrUtil.hasEmpty(param.getId())) {
            orm.setId(UUID.randomUUID().toString());
            // isIdNull =true;
            this.save(orm);
        } else {
            // orm.setId(param.getId());
            // TbBusPatientEntity patientEntity = this.getById(param.getId());
            // if ("1".equals(patientEntity.getDiagnosisStatus())){
            // return OwnResult.error("患者已完成诊断，无法修改信息");
            // }
            this.updateById(orm);
        }
        // TbBusPortraitReportEntity reportEntity1 = new TbBusPortraitReportEntity();
        // reportEntity1.setStatus("0");
        // busPortraitReportService.update(reportEntity1,new LambdaQueryWrapper<TbBusPortraitReportEntity>()
        // .eq(TbBusPortraitReportEntity :: getPatientId,orm.getId())
        // .eq(TbBusPortraitReportEntity :: getStatus,"1"));
        // TbBusPortraitReportEntity reportEntity = null ;
        // if (isIdNull){
        // reportEntity = new TbBusPortraitReportEntity();
        // reportEntity.setId(UUID.randomUUID().toString());
        // reportEntity.setPatientId(orm.getId());
        // reportEntity.setRadiationId(param.getRadiationId());
        // reportEntity.setReportDoctor(param.getReportDoctor());
        // reportEntity.setReportUnit(param.getReportUnit());
        // reportEntity.setReportTime(param.getReportTime());
        // reportEntity.setRadiationId(param.getRadiationId());
        // reportEntity.setStraightMatter(param.getStraightMatter());
        // busPortraitReportService.save(reportEntity);
        // }else {
        // reportEntity = busPortraitReportService.getOne(new LambdaQueryWrapper<TbBusPortraitReportEntity>()
        // .eq(TbBusPortraitReportEntity::getPatientId, orm.getId())
        // .eq(TbBusPortraitReportEntity::getStatus, "1"));
        // reportEntity.setRadiationId(param.getRadiationId());
        // reportEntity.setReportDoctor(param.getReportDoctor());
        // reportEntity.setReportUnit(param.getReportUnit());
        // reportEntity.setReportTime(param.getReportTime());
        // reportEntity.setRadiationId(param.getRadiationId());
        // reportEntity.setStraightMatter(param.getStraightMatter());
        // busPortraitReportService.updateById(reportEntity);
        // }
         TbBusPortraitReportImagesEntity reportImagesEntity = new TbBusPortraitReportImagesEntity();
         reportImagesEntity.setStatus("0");
         tbBusPortraitReportImagesService.update(reportImagesEntity,new LambdaQueryWrapper<TbBusPortraitReportImagesEntity>()
         .eq(TbBusPortraitReportImagesEntity :: getPatientId,orm.getId())
         .eq(TbBusPortraitReportImagesEntity :: getStatus,"1"));
         List<String> imagesIdList = new ArrayList<>();
        for (String file : param.getFiles()) {
            if (file.startsWith("data")) {
                String saveImage = com.graphy.unit.file.Api.base64SaveImage(file, fileFolder, null,null, null, null);
                String head = file.substring(0, file.indexOf(",") + 1);
                String type = head.substring(head.indexOf("/") + 1, head.indexOf(";"));
                TbBusPortraitReportImagesEntity imagesEntity = new TbBusPortraitReportImagesEntity();
                imagesEntity.setId(UUID.randomUUID().toString());
                imagesEntity.setPatientId(orm.getId());
                imagesEntity.setImageFormat(type);
                imagesEntity.setImagePath(saveImage);
                tbBusPortraitReportImagesService.save(imagesEntity);
                imagesIdList.add(imagesEntity.getId());
            }
        }
        OnChainDataDto onChainDataDto = new OnChainDataDto();
        onChainDataDto.setTbBusPatientEntity(orm);
        onChainDataDto.setImagesIdList(imagesIdList);
//        System.out.println(JSONObject.toJSONString(onChainDataDto));
        String FileHash = Api.encrypt(JSONObject.toJSONString(onChainDataDto), BaseConfig.ENCRYPTION_KEY);
        String Txid = BlockChainApi.blockChainStorage(storageHost, FileHash, orm.getId(), orm.getRadiationId());

        TbBusPatientEntity tbBusPatientEntity = this.getById(orm.getId());
        tbBusPatientEntity.setTxid(Txid);
        this.updateById(tbBusPatientEntity);
        return OwnResult.result(true);
    }

    /**
     * auther： lsd
     * create： 2022-03-01 11:55:09
     * describe： 删除患者信息
     * @param id   主键
     * @return
     */
    public OwnResult<Boolean> delPatientRecord(String id) throws Exception {
        if (StrUtil.hasEmpty(id))
            return OwnResult.error("请设置主键");
        // TbBusDiagnoseEntity diagnoseServiceOne = busDiagnoseService.getOne(new LambdaQueryWrapper<TbBusDiagnoseEntity>()
        // .eq(TbBusDiagnoseEntity::getPatientId, id)
        // .eq(TbBusDiagnoseEntity::getStatus, "1"));
        // diagnoseServiceOne.setStatus("0");
        // busDiagnoseService.updateById(diagnoseServiceOne);
        // TbBusPortraitReportEntity busPortraitReportServiceOne = busPortraitReportService.getOne(new LambdaQueryWrapper<TbBusPortraitReportEntity>()
        // .eq(TbBusPortraitReportEntity::getPatientId, id)
        // .eq(TbBusPortraitReportEntity::getStatus, "1"));
        // busPortraitReportServiceOne.setStatus("0");
        // busPortraitReportService.updateById(busPortraitReportServiceOne);
        TbBusPatientEntity patientEntity = this.getById(id);
        // if ("1".equals(patientEntity.getDiagnosisStatus())){
        // return OwnResult.error("患者已诊断，请先删除患者诊断记录，随后才能删除患者");
        // }
        // TbBusPortraitReportEntity reportEntity1 = new TbBusPortraitReportEntity();
        // reportEntity1.setStatus("0");
        // busPortraitReportService.update(reportEntity1,new LambdaQueryWrapper<TbBusPortraitReportEntity>()
        // .eq(TbBusPortraitReportEntity :: getPatientId,id)
        // .eq(TbBusPortraitReportEntity :: getStatus,"1"));
        patientEntity.setStatus("0");
        this.updateById(patientEntity);
        return OwnResult.result(true);
    }

    private static boolean isAutoSyncData = false;


     /**
     * auther： lsd
     * create： 2022-03-01 11:55:09
     * describe： 导入患者信息
     */
     public void importPatient() throws Exception {
     List<NameEntity> nameEntities = nameService.list(new LambdaQueryWrapper<NameEntity>());
     for (int k=0;k<nameEntities.size();k++){
         NameEntity nameEntity = nameEntities.get(k);
         List<TbBusPatientEntity> patientEntity = this.list(new LambdaQueryWrapper<TbBusPatientEntity>().eq(TbBusPatientEntity::getStatus, "1")
                 .eq(TbBusPatientEntity::getRadiationId, nameEntity.getRadiationId())
         .eq(TbBusPatientEntity :: getDiagnosisTime,nameEntity.getApplyByUnitDate())
         .eq(TbBusPatientEntity::getDiagnosisDoctor,nameEntity.getCheckUnitFixedUserName())
                 .eq(TbBusPatientEntity :: getAge,0));
         if (!OwnCommon.isNullOrEmpty(patientEntity)&&patientEntity.size()>0){
            for (TbBusPatientEntity tbBusPatientEntity:patientEntity){
                if (nameEntity.getAge().contains("岁")){
                    String[] split = nameEntity.getAge().split("岁");
                    tbBusPatientEntity.setAge(Integer.valueOf(split[0]));
                }else if (!nameEntity.getAge().contains("天")&&!nameEntity.getAge().contains("月")){
                    tbBusPatientEntity.setAge(Integer.valueOf(nameEntity.getAge()));
                }
                this.updateById(tbBusPatientEntity);
            }
         }
//         TbBusPatientEntity tbBusPatientEntity = new TbBusPatientEntity();
//         tbBusPatientEntity.setAge(nameEntity.getAge());
//         tbBusPatientEntity.setRadiationId(nameEntity.getRadiationId());
////        tbBusPatientEntity.setGender(nameEntity.getGender());
//         if ("男".equals(nameEntity.getGender())){
//             tbBusPatientEntity.setGender("1");
//         }else {
//             tbBusPatientEntity.setGender("2");
//         }
////         if (k % 2 == 0){
////             tbBusPatientEntity.setIllnessId("c0a03dba7ea96514dd2d1ff89feb778d");
////         }else if (nameEntity.getGender().equals("2")){
////             tbBusPatientEntity.setIllnessId("8c108123ad7670056be93756be69723a");
////         }
//         tbBusPatientEntity.setIllnessId("8c108123ad7670056be93756be69723a");//
//         tbBusPatientEntity.setDiagnosisDoctor(nameEntity.getCheckUnitFixedUserName());
//         tbBusPatientEntity.setDiagnosisUnit("3c7b2d3d347552ed32a9727d99584eea");
////        if ((k % 2 == 0)){
////             tbBusPatientEntity.setDiagnosisUnit("3c7b2d3d347552ed32a9727d99584eea");
////         }else {
////            tbBusPatientEntity.setDiagnosisUnit("8ba4bd73c74c1d630bf2519e21f8aa61");
////        }
//        tbBusPatientEntity.setDiagnosisTime(nameEntity.getApplyByUnitDate());
//
//
//        StringBuilder str=new StringBuilder();//定义变长字符串
//        Random random=new Random();
//         for(int i=0;i<8;i++){
//            str.append(random.nextInt(10));
//        }
//         if ("1".equals(nameEntity.getRemark())){
//             tbBusPatientEntity.setDiagnosisDescribe(nameEntity.getName()+"膀胱恶性肿瘤");//
//             tbBusPatientEntity.setCtime(new Date());
//         }else {
//             tbBusPatientEntity.setCtime(this.getRandDate());
//         }
//         tbBusPatientEntity.setId(UUID.randomUUID().toString());
////         tbBusPatientEntity.setRadiationId(str.toString());
//
////         tbBusPatientEntity.setDiagnosisDescribe(nameEntity.getName());
////         tbBusPatientEntity.setCtime();
////         OnChainDataDto onChainDataDto = new OnChainDataDto();
////         onChainDataDto.setTbBusPatientEntity(tbBusPatientEntity);
////         List<String> base64es = new ArrayList<>();
////         onChainDataDto.setImagesIdList(base64es);
////         String FileHash = Api.encrypt(JSONObject.toJSONString(onChainDataDto),BaseConfig.ENCRYPTION_KEY);
////         String Txid = BlockChainApi.blockChainStorage(storageHost, FileHash, tbBusPatientEntity.getId(), tbBusPatientEntity.getRadiationId());
////         tbBusPatientEntity.setTxid(Txid);
//        this.save(tbBusPatientEntity);
     }
     OwnResult.result(true);
     }

    public  Date  getRandDate() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //指定开始日期
        long start = sdf.parse("2021-1-1 00:00:00").getTime();
        //指定结束日期
        long end = 0;
        try {
            end = sdf.parse("2021-07-11 00:00:00").getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //调用方法产生随机数
        long randomDate = nextLong(start, end);
        Date date = new Date();
        date.setTime(randomDate);
        return date;
//        //格式化输出日期
//        System.out.printf("随机日期为:%1$tF %1$tT", randomDate);


    }

    public  long nextLong(long start, long end) {
        Random random = new Random();
        return start + (long) (random.nextDouble() * (end - start + 1));
    }

    /**
     * auther： qwt
     * create： 2022-03-01 11:54:46
     * describe： 保存患者信息
     * @return
     */
    public void savePatientRecord1() throws Exception {
//        List<TbBusPatientEntity> tbBusPatientEntities = this.list(new LambdaQueryWrapper<TbBusPatientEntity>().eq(TbBusPatientEntity::getStatus, "1")
//                .orderByAsc(TbBusPatientEntity::getCtime));
        List<PatientRecordDto> tbBusPatientEntities = busPatientMapper.getPatientRecord1(new PatientRecordParam());
        for (TbBusPatientEntity  tbBusPatientEntity :tbBusPatientEntities){
            OnChainDataDto onChainDataDto = new OnChainDataDto();
            onChainDataDto.setTbBusPatientEntity(tbBusPatientEntity);
            String FileHash = Api.encrypt(JSONObject.toJSONString(onChainDataDto), BaseConfig.ENCRYPTION_KEY);
            String Txid = BlockChainApi.blockChainStorage(storageHost, FileHash, tbBusPatientEntity.getId(), tbBusPatientEntity.getRadiationId());
            tbBusPatientEntity.setTxid(Txid);
            this.updateById(tbBusPatientEntity);
        }
    }
}