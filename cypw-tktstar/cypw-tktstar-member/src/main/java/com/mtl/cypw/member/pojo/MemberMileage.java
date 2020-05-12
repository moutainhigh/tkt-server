package com.mtl.cypw.member.pojo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
* @author tang. 
* @date 2019年11月22日 下午07:41:32
*/
@Data
@Table(name = "tblmembermileage")
public class MemberMileage {
    @Id
    @Column(name = "mileage_id")
    private Integer mileageId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "mileage_value")
    private Integer mileageValue;

    @Column(name = "mileage_desc")
    private String mileageDesc;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "old_value")
    private Integer oldValue;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;
}