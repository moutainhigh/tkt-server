package com.mtl.cypw.provider.admin.converter;

import com.mtl.cypw.admin.pojo.User;
import com.mtl.cypw.domain.admin.dto.UserDTO;
import com.mtl.cypw.domain.admin.param.UserParam;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/18.
 */
@Component
public class UserConverter {

    public UserDTO toDto(User pojo) {
        UserDTO dto = new UserDTO();
        dto.setUserId(pojo.getUserId());
        dto.setGroupId(pojo.getGroupId());
        dto.setLoginName(pojo.getLoginName());
        dto.setLoginPass(pojo.getLoginPass());
        dto.setPersonName(pojo.getPersonName());
        dto.setPersonMobile(pojo.getPersonMobile());
        dto.setUserDesc(pojo.getUserDesc());
        dto.setRoleId(pojo.getRoleId());
        dto.setAddDate(pojo.getAddDate());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setAddUser(pojo.getAddUser());
        dto.setUpdateUser(pojo.getUpdateUser());
        dto.setUpdateName(pojo.getUpdateName());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setIsDelete(pojo.getIsDelete());
        dto.setIsAdministrator(pojo.getIsAdministrator());
        dto.setTokenId(pojo.getTokenId());
        dto.setCompanyId(pojo.getCompanyId());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setIsPrint(pojo.getIsPrint());
        return dto;
    }

    public List<UserDTO> toDto(List<User> list) {
        if (list == null) {
            return null;
        }
        List<UserDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }

    public User toEntity(UserParam param) {
        User user = new User();
        user.setUserId(param.getUserId());
        user.setTokenId(param.getTokenId());
        return user;
    }
}
