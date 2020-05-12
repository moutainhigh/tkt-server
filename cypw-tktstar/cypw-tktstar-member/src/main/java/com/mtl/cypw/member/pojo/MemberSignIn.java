package com.mtl.cypw.member.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年03月03日 下午03:27:48
*/
@Data
@Table(name = "cy_member_sign_in")
public class MemberSignIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "order_id")
    private Integer orderId;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "theatre_id")
    private Integer theatreId;

    @Column(name = "sign_in_address")
    private String signInAddress;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "type")
    private Integer type;

    @Column(name = "remark")
    private String remark;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "deleted")
    private Integer deleted;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}