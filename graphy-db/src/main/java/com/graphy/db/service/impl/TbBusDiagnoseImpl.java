package com.graphy.db.service.impl;

import com.graphy.db.entity.TbBusDiagnoseEntity;
import com.graphy.db.mapper.TbBusDiagnoseMapper;
import com.graphy.db.service.TbBusDiagnoseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther lsd
 * @create 2022-03-01 11:20:04
 * @describe 业务-诊断信息服务实现类
 */
@Service("com.graphy.db.service.impl.tbbusdiagnoseimpl")
public class TbBusDiagnoseImpl extends ServiceImpl<TbBusDiagnoseMapper, TbBusDiagnoseEntity> implements TbBusDiagnoseService {

}
