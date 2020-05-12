package com.mtl.cypw.mpm.model;

import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年11月26日 下午07:24:37
*/
@Data
@Table(name = "tblprovince")
public class Province {
    @Id
    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "province_ename")
    private String provinceEname;

    @Column(name = "nationcode")
    private String nationcode;

    @Column(name = "nationname")
    private String nationname;

    @Column(name = "nationename")
    private String nationename;

    @Column(name = "continentcode")
    private String continentcode;

    @Column(name = "continentname")
    private String continentname;

    @Column(name = "continentename")
    private String continentename;

    @Column(name = "regionlevel")
    private String regionlevel;
}