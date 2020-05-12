package com.mtl.cypw.provider.mpm.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mpm.endpoint.EnterpriseApi;
import com.mtl.cypw.domain.mpm.dto.EnterpriseDTO;
import com.mtl.cypw.domain.mpm.param.EnterpriseQueryParam;
import com.mtl.cypw.mpm.model.Enterprise;
import com.mtl.cypw.mpm.model.EnterpriseDialog;
import com.mtl.cypw.mpm.model.EnterprisePayType;
import com.mtl.cypw.mpm.model.EnterpriseProgramType;
import com.mtl.cypw.mpm.model.EnterpriseSearch;
import com.mtl.cypw.mpm.model.EnterpriseTemplate;
import com.mtl.cypw.mpm.service.EnterpriseDialogService;
import com.mtl.cypw.mpm.service.EnterprisePayTypeService;
import com.mtl.cypw.mpm.service.EnterpriseProgramTypeService;
import com.mtl.cypw.mpm.service.EnterpriseSearchService;
import com.mtl.cypw.mpm.service.EnterpriseService;
import com.mtl.cypw.mpm.service.EnterpriseTemplateService;
import com.mtl.cypw.provider.mpm.converter.EnterpriseDeliveryConverter;
import com.mtl.cypw.provider.mpm.converter.EnterpriseDialogConverter;
import com.mtl.cypw.provider.mpm.converter.EnterprisePayTypeConverter;
import com.mtl.cypw.provider.mpm.converter.EnterpriseProgramTypeConverter;
import com.mtl.cypw.provider.mpm.converter.EnterpriseSearchConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@RestController
@Slf4j
public class EnterpriseEndpoint implements EnterpriseApi {

    @Autowired
    EnterpriseService enterpriseServiceImpl;

    @Autowired
    EnterpriseTemplateService enterpriseTemplateServiceImpl;

    @Autowired
    EnterprisePayTypeService enterprisePayTypeServiceImpl;

    @Autowired
    EnterpriseDialogService enterpriseDialogServiceImpl;

    @Autowired
    EnterpriseProgramTypeService enterpriseProgramTypeServiceImpl;

    @Autowired
    EnterpriseSearchService enterpriseSearchServiceImpl;

    @Resource
    EnterprisePayTypeConverter enterprisePayTypeConverter;

    @Resource
    EnterpriseDialogConverter enterpriseDialogConverter;

    @Resource
    EnterpriseProgramTypeConverter enterpriseProgramTypeConverter;

    @Resource
    EnterpriseSearchConverter enterpriseSearchConverter;

    @Resource
    EnterpriseDeliveryConverter enterpriseDeliveryConverter;

    @Override
    public TSingleResult<EnterpriseDTO> getEnterprise(QueryRequest<EnterpriseQueryParam> request) {
        EnterpriseQueryParam queryParam = request.getParam();
        Enterprise enterprise = enterpriseServiceImpl.searchEnterprise(queryParam);
        EnterpriseDTO dto = null;
        if (enterprise != null) {
            dto = new EnterpriseDTO();
            EnterpriseTemplate enterpriseTemplate = enterpriseTemplateServiceImpl.getEnterpriseTemplateByEnterpriseId(enterprise.getEnterpriseId());
            List<EnterprisePayType> enterprisePayTypeList = enterprisePayTypeServiceImpl.getEnterprisePayType(enterprise.getEnterpriseId());
            List<EnterpriseProgramType> enterpriseProgramTypeList = enterpriseProgramTypeServiceImpl.getEnterpriseProgramType(enterprise.getEnterpriseId());
            EnterpriseSearch enterpriseSearch = enterpriseSearchServiceImpl.getEnterpriseSearch(enterprise.getEnterpriseId());
            EnterpriseDialog enterpriseDialog = enterpriseDialogServiceImpl.getEnterpriseDialog(enterprise.getEnterpriseId(), 1);
            dto.setEnterpriseId(enterprise.getEnterpriseId());
            dto.setEnterpriseName(enterprise.getEnterpriseName());
            dto.setDomainName(enterprise.getDomainName());
            dto.setServiceMobile(enterprise.getServiceMobile());
            dto.setHomeTitle(enterprise.getHomeTitle());
            dto.setTenantId(enterprise.getFhlId());
            dto.setLogoImage(enterprise.getLogoImage());
            dto.setBackendType(enterprise.getBackendType());
            dto.setSupportNormalLogin(enterprise.getSupportNormalLogin());
            dto.setExtendedLogin(enterprise.getExtendedLogin());
            dto.setMemberTags(enterprise.getMemberTags());
            dto.setAdminMobile(enterprise.getAdminMobile());
            dto.setChannel(enterprise.getChannel());
            dto.setAppId(enterprise.getAppId());
            dto.setAboutUs(enterprise.getAboutUs());
            if (enterpriseTemplate != null) {
                dto.setIndexTemplate(enterpriseTemplate.getIndexTemplate());
                dto.setIndexBackgroundColor(enterpriseTemplate.getIndexBackgroundColor());
                dto.setDelivers(enterpriseDeliveryConverter.toDto(enterpriseTemplate));
            }
            if (CollectionUtils.isNotEmpty(enterprisePayTypeList)) {
                dto.setEnterprisePayTypes(enterprisePayTypeConverter.toDto(enterprisePayTypeList));
            }
            if (CollectionUtils.isNotEmpty(enterpriseProgramTypeList)) {
                dto.setEnterpriseProgramTypes(enterpriseProgramTypeConverter.toDto(enterpriseProgramTypeList));
            }
            if (enterpriseSearch != null) {
                dto.setEnterpriseSearchDTO(enterpriseSearchConverter.toDto(enterpriseSearch));
            }
            if (enterpriseDialog != null) {
                dto.setEnterpriseDialogDTO(enterpriseDialogConverter.toDto(enterpriseDialog));
            }

        }
        return ResultBuilder.succTSingle(dto);
    }

}
