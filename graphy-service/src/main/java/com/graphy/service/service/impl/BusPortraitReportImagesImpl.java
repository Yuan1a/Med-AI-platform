package com.graphy.service.service.impl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.BusPortraitReportImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.graphy.db.mapper.TbBusPortraitReportImagesMapper;
import com.graphy.db.entity.TbBusPortraitReportImagesEntity;
import com.graphy.service.service.BusPortraitReportImagesService;
import lombok.extern.slf4j.Slf4j;

/**
* @auther qwt
* @create 2022-12-05 10:22:14
* @describe 影像报告图片管理
*/
@Service("com.graphy.service.service.impl.busportraitreportimagesimpl")
@RequiredArgsConstructor
@Slf4j
public class BusPortraitReportImagesImpl extends ServiceImpl<TbBusPortraitReportImagesMapper,TbBusPortraitReportImagesEntity> implements BusPortraitReportImagesService {

    private final BusPortraitReportImagesMapper busPortraitReportImagesMapper;

}