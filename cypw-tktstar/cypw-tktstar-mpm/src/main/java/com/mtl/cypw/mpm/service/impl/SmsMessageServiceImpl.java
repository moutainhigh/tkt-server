package com.mtl.cypw.mpm.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.BusinessException;
import com.mtl.cypw.domain.Sms.enums.SmsStatusEnum;
import com.mtl.cypw.domain.mpm.param.SmsMessageQueryParam;
import com.mtl.cypw.mpm.mapper.SmsMessageMapper;
import com.mtl.cypw.mpm.model.SmsMessage;
import com.mtl.cypw.mpm.service.SmsMessageService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/30.
 */
@Service
@Slf4j
public class SmsMessageServiceImpl implements SmsMessageService {

    @Resource
    SmsMessageMapper mapper;

    @Override
    public void addSmsMessage(SmsMessage smsMessage) {
        if (smsMessage == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        smsMessage.setCreateTime(new Date());
        smsMessage.setDeleted(0);
        mapper.insert(smsMessage);
    }

    @Override
    public void updateFailCount(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        SmsMessage message = getSmsMessage(id);
        if (message == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getMsg(), ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getCode());
        }
        message.setFailCount(message.getFailCount() + 1);
        message.setUpdateTime(new Date());
        mapper.updateByPrimaryKey(message);
    }

    @Override
    public SmsMessage getSmsMessage(Integer id) {
        if (id == null) {
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SmsMessage> searchSmsMessage(SmsMessageQueryParam queryParam) {
        Example example = new Example(SmsMessage.class);
        Example.Criteria cri = example.createCriteria();
        if (queryParam != null) {
            if (queryParam.getEnterpriseId() != null) {
                cri.andEqualTo("enterpriseId", queryParam.getEnterpriseId());
            }
            if (StringUtils.isNotEmpty(queryParam.getProduct())) {
                cri.andEqualTo("product", queryParam.getProduct());
            }
            if (StringUtils.isNotEmpty(queryParam.getCellphone())) {
                cri.andEqualTo("cellphone", queryParam.getCellphone());
            }
            if (queryParam.getSendState() != null) {
                cri.andEqualTo("sendState", queryParam.getSendState());
            }
            if (queryParam.getLessFailCount() != null) {
                cri.andLessThan("failCount", queryParam.getLessFailCount());
            }
            if (queryParam.getGreaterFailCount() != null) {
                cri.andGreaterThan("failCount", queryParam.getGreaterFailCount());
            }

        }
        cri.andEqualTo("deleted", 0);
        example.orderBy("createTime").desc();
        return mapper.selectByExample(example);
    }

    @Override
    public List<SmsMessage> searchSmsMessagePage(SmsMessageQueryParam queryParam, Pagination pagination) {
        if (pagination == null) {
            log.error("分页参数不能为空");
            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        Page page = PageHelper.startPage(pagination.getOffset(), pagination.getLength());
        List<SmsMessage> list = searchSmsMessage(queryParam);
        pagination.setCount(page.getTotal());
        return list;
    }

    @Override
    public int updateSendStateAndFailCount(SmsMessage sendSmsParam) {
        Example example = new Example(SmsMessage.class);
        Example.Criteria exampleCriteria = example.createCriteria();
        exampleCriteria.andEqualTo("id", sendSmsParam.getId());
        exampleCriteria.andEqualTo("sendState", SmsStatusEnum.SMS_UNSEND.getCode());
        exampleCriteria.andEqualTo("bizType", sendSmsParam.getBizType());
        SmsMessage smsMessage = new SmsMessage();
        smsMessage.setSendState(sendSmsParam.getSendState());
        smsMessage.setFailCount(sendSmsParam.getFailCount());
        return mapper.updateByExampleSelective(smsMessage, example);
    }

    @Override
    public List<SmsMessage> findNoSendSmsByBizTypes(SmsMessageQueryParam queryParam) {
        Example example = new Example(SmsMessage.class);
        Example.Criteria cri = example.createCriteria();
        if (queryParam != null) {
            if (!CollectionUtils.isEmpty(queryParam.getBizTypes())) {
                cri.andIn("bizType", queryParam.getBizTypes());
            }
            if (queryParam.getSendState() != null) {
                cri.andEqualTo("sendState", queryParam.getSendState());
            }
        }
        cri.andEqualTo("deleted", 0);
        return mapper.selectByExample(example);
    }
}
