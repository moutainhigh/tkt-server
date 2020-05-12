package com.mtl.cypw.mpm.model;

import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年11月26日 下午07:24:37
*/
@Data
@Table(name = "tblcity")
public class City {
    @Id
    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "city_name")
    private String cityName;

    @Column(name = "city_ename")
    private String cityEname;

    @Column(name = "province_code")
    private String provinceCode;
}