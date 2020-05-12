package com.mtl.cypw.provider.mpm.converter;

import com.mtl.cypw.domain.mpm.dto.SmsMessageDTO;
import com.mtl.cypw.mpm.model.SmsMessage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Component
public class SmsMessageConverter {

    public SmsMessageDTO toDto(SmsMessage pojo) {
        if (pojo == null) {
            return null;
        }
        SmsMessageDTO dto = new SmsMessageDTO();
        dto.setId(pojo.getId());
        dto.setSmsId(pojo.getSmsId());
        dto.setProductChannelId(pojo.getProductChannelId());
        dto.setProduct(pojo.getProduct());
        dto.setChannel(pojo.getChannel());
        dto.setTemplateCode(pojo.getTemplateCode());
        dto.setBizId(pojo.getBizId());
        dto.setContent(pojo.getContent());
        dto.setCellphone(pojo.getCellphone());
        dto.setVariables(pojo.getVariables());
        dto.setSendState(pojo.getSendState());
        dto.setFailCount(pojo.getFailCount());
        dto.setRemarks(pojo.getRemarks());
        dto.setEnterpriseId(pojo.getEnterpriseId());
        dto.setCreateTime(pojo.getCreateTime());
        dto.setUpdateTime(pojo.getUpdateTime());
        dto.setDeleted(pojo.getDeleted());
        return dto;
    }

    public List<SmsMessageDTO> toDto(List<SmsMessage> list) {
        if (list == null) {
            return null;
        }
        List<SmsMessageDTO> dtoList = new ArrayList<>();
        list.forEach(n -> dtoList.add(toDto(n)));
        return dtoList;
    }
}
