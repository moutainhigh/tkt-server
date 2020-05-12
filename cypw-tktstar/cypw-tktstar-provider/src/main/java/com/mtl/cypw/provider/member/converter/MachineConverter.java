package com.mtl.cypw.provider.member.converter;

import com.mtl.cypw.domain.member.dto.MachineDTO;
import com.mtl.cypw.member.pojo.Machine;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/12.
 */
@Component
public class MachineConverter {

    public MachineDTO toDto(Machine pojo) {
        if (pojo == null) {
            return null;
        }
        MachineDTO dto = new MachineDTO();
        dto.setMachineId(pojo.getMachineId());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setMachineKey(pojo.getMachineKey());
        dto.setMacAddress(pojo.getMacAddress());
        dto.setIsEnable(pojo.getIsEnable());
        dto.setRemark(pojo.getRemark());
        dto.setCreateDate(pojo.getCreateDate());
        dto.setCreateUser(pojo.getCreateUser());
        dto.setUpdateDate(pojo.getUpdateDate());
        dto.setUpdateUser(pojo.getUpdateUser());
        return dto;
    }

    public List<MachineDTO> toDto(List<Machine> list) {
        if (list == null) {
            return null;
        }
        List<MachineDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }


}
