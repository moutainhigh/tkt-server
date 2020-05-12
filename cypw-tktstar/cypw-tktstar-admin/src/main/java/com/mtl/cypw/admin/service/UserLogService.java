package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.UserLogMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class UserLogService {

    @Resource
    UserLogMapper userLogMapper;
}
