package com.graphy.db.service.impl;

import com.graphy.db.entity.TbBusPatientEntity;
import com.graphy.db.mapper.TbBusPatientMapper;
import com.graphy.db.service.TbBusPatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther lsd
 * @create 2022-03-01 11:20:04
 * @describe 服务实现类
 */
@Service("com.graphy.db.service.impl.tbbuspatientimpl")
public class TbBusPatientImpl extends ServiceImpl<TbBusPatientMapper, TbBusPatientEntity> implements TbBusPatientService {

}
