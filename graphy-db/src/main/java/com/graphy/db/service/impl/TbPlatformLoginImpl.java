package com.graphy.db.service.impl;

import com.graphy.db.entity.TbPlatformLoginEntity;
import com.graphy.db.mapper.TbPlatformLoginMapper;
import com.graphy.db.service.TbPlatformLoginService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther lsd
 * @create 2021-09-30 16:16:55
 * @describe 系统-登录信息服务实现类
 */
@Service("com.graphy.db.service.impl.tbplatformloginimpl")
public class TbPlatformLoginImpl extends ServiceImpl<TbPlatformLoginMapper, TbPlatformLoginEntity> implements TbPlatformLoginService {

}
