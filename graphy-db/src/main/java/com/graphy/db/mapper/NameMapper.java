package com.graphy.db.mapper;

import com.graphy.db.entity.NameEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
/**
 * @auther qwt
 * @create 2023-06-16 09:26:34
 * @describe mapper类
 */
@Repository("com.graphy.db.mapper.namemapper")
public interface NameMapper extends BaseMapper<NameEntity> {

}
