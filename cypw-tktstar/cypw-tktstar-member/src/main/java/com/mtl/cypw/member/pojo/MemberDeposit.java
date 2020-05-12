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
@Table(name = "tblmemberdeposit")
public class MemberDeposit {
    @Id
    @Column(name = "deposit_id")
    private Integer depositId;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "deposit_value")
    private Integer depositValue;

    @Column(name = "deposit_desc")
    private String depositDesc;

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