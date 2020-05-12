package com.mtl.cypw.member.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2019年11月22日 下午07:45:26
 */
@Data
@Table(name = "tblmember")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "member_name")
    private String memberName;

    @Column(name = "nick_name")
    private String nickName;

    @Column(name = "member_mobile")
    private String memberMobile;

    @Column(name = "member_desc")
    private String memberDesc;

    @Column(name = "member_pass")
    private String memberPass;

    @Column(name = "temp_pass")
    private String tempPass;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "first_order_date")
    private Date firstOrderDate;

    @Column(name = "member_level_id")
    private Integer memberLevelId;

    @Column(name = "is_blacklist")
    private Integer isBlacklist;

    @Column(name = "keyAccount_id")
    private Integer keyaccountId;

    @Column(name = "customer_tag_ids")
    private String customerTagIds;

    @Column(name = "member_mileage")
    private Integer memberMileage;

    @Column(name = "member_deposit")
    private Integer memberDeposit;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "member_token")
    private String memberToken;

    @Column(name = "token_date")
    private Date tokenDate;

    @Column(name = "token_desc")
    private String tokenDesc;

    @Column(name = "is_default")
    private Integer isDefault;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_desc")
    private String contactDesc;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "member_email")
    private String memberEmail;

    @Column(name = "member_image")
    private String memberImage;

    @Column(name = "default_address_id")
    private Integer defaultAddressId;

    @Column(name = "third_party_user_id")
    private String thirdPartyUserId;
}