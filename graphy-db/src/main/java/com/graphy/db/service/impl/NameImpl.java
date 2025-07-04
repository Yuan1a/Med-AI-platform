package com.graphy.db.service.impl;

import com.graphy.db.entity.NameEntity;
import com.graphy.db.mapper.NameMapper;
import com.graphy.db.service.NameService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @auther qwt
 * @create 2023-06-16 09:26:34
 * @describe 服务实现类
 */
@Service("com.graphy.db.service.impl.nameimpl")
public class NameImpl extends ServiceImpl<NameMapper, NameEntity> implements NameService {

}
