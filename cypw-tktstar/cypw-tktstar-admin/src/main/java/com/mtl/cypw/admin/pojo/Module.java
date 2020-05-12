package com.mtl.cypw.admin.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tang.
 * @date 2020年03月17日 上午11:17:41
 */
@Data
@Table(name = "tblmodule")
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "module_id")
    private Integer moduleId;

    @Column(name = "module_name")
    private String moduleName;
}