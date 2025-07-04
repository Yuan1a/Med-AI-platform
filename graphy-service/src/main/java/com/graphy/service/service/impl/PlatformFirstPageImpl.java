package com.graphy.service.service.impl;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.graphy.service.mapper.PlatformFirstPageMapper;
import com.graphy.service.service.PlatformFirstPageService;
import lombok.extern.slf4j.Slf4j;

/**
* @auther lsd
* @create 2021-07-27 17:28:34
* @describe 主页
*/
@Service("com.graphy.service.service.impl.platformfirstpageimpl")
@RequiredArgsConstructor
@Slf4j
public class PlatformFirstPageImpl  implements PlatformFirstPageService {

    private final PlatformFirstPageMapper platformFirstPageMapper;

}