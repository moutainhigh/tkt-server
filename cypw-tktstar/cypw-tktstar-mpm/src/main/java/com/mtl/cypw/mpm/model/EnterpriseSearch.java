package com.mtl.cypw.mpm.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author tang.
 * @date 2020年01月19日 下午12:19:33
 */
@Data
@Table(name = "tblenterprisesearch")
public class EnterpriseSearch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "search_key")
    private String searchKey;

    @Column(name = "guess_key")
    private String guessKey;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "create_user")
    private Integer createUser;

    @Column(name = "update_user")
    private Integer updateUser;
}