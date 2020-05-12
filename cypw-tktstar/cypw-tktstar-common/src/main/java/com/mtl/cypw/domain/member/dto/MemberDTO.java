package com.mtl.cypw.domain.member.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author tang.
 * @date 2019/11/22.
 */
@Data
public class MemberDTO {
    private Integer memberId;
    private String memberName;
    private String nickName;
    private String memberMobile;
    private String memberDesc;
    private String memberPass;
    private String tempPass;
    private Date addDate;
    private Date updateDate;
    private Integer addUser;
    private Integer updateUser;
    private Date firstOrderDate;
    private Integer memberLevelId;
    private Integer isBlacklist;
    private Integer keyaccountId;
    private String customerTagIds;
    private Integer memberMileage;
    private Integer memberDeposit;
    private String openId;
    private String memberToken;
    private Date tokenDate;
    private String tokenDesc;
    private Integer isDefault;
    private Integer parentId;
    private String companyName;
    private String contactName;
    private String contactDesc;
    private Integer enterpriseId;
    private String memberEmail;
    private String memberImage;
    private Integer defaultAddressId;
    private String thirdPartyUserId;
}
