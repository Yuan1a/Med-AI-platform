package com.graphy.db.service.impl;

import com.graphy.db.entity.TbPlatformUserEntity;
import com.graphy.db.mapper.TbPlatformUserMapper;
import com.graphy.db.service.TbPlatformUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther lsd
 * @create 2021-11-15 15:06:18
 * @describe 系统-账户信息服务实现类
 */
@Service("com.graphy.db.service.impl.tbplatformuserimpl")
public class TbPlatformUserImpl extends ServiceImpl<TbPlatformUserMapper, TbPlatformUserEntity> implements TbPlatformUserService {

}
