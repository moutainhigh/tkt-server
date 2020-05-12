package com.mtl.cypw.member.pojo;

import com.mtl.cypw.common.enums.CommonStateEnum;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年02月12日 上午09:51:15
*/
@Data
@Table(name = "tblcheckinuser")
public class CheckInUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_pass")
    private String userPass;

    @Column(name = "person_name")
    private String personName;

    @Column(name = "begin_date")
    private Date beginDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "remark")
    private String remark;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_user")
    private Integer updateUser;

    public boolean isValid() {
        return CommonStateEnum.VALID.getCode() == this.getIsEnable();
    }
}