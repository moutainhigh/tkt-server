package com.mtl.cypw.mpm.service;

import com.mtl.cypw.mpm.model.EnterpriseProgramType;

import java.util.List;

/**
 * @author tang.
 * @date 2019/12/3.
 */
public interface EnterpriseProgramTypeService {

    List<EnterpriseProgramType> getEnterpriseProgramType(Integer enterpriseId);
}
