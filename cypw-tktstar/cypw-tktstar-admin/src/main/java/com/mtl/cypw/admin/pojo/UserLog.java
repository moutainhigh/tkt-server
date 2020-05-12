package com.mtl.cypw.admin.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2020年03月17日 上午11:17:41
*/
@Data
@Table(name = "tbluserlog")
public class UserLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Integer logId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "operation_name")
    private String operationName;

    @Column(name = "operation_content")
    private String operationContent;

    @Column(name = "operation_desc")
    private String operationDesc;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private String addUser;
}