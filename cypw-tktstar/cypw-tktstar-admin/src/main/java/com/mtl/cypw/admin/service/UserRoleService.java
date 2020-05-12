package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class UserRoleService {

    @Resource
    UserRoleMapper userRoleMapper;
}
