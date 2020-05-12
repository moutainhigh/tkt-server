package com.mtl.cypw.domain.mpm.dto;

import lombok.Data;

import java.util.List;

/**
 * @author tang.
 * @date 2019/11/26.
 */
@Data
public class EnterpriseDTO {
    private Integer enterpriseId;

    private String enterpriseName;

    private String tenantId;

    private String indexTemplate;

    private String indexBackgroundColor;

    private String domainName;

    private String serviceMobile;

    private String homeTitle;

    private String logoImage;

    private Integer backendType;

    private Integer supportNormalLogin;

    private String extendedLogin;

    private String memberTags;

    private String adminMobile;

    private String channel;

    private String appId;

    private String aboutUs;

    private List<EnterprisePayTypeDTO> enterprisePayTypes;

    private List<EnterpriseProgramTypeDTO> enterpriseProgramTypes;

    private EnterpriseSearchDTO enterpriseSearchDTO;

    private EnterpriseDialogDTO enterpriseDialogDTO;

    private List<EnterpriseDeliveryDTO> delivers;

}
