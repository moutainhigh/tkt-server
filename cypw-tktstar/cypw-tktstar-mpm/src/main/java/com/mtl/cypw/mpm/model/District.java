package com.mtl.cypw.mpm.model;

import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年11月26日 下午07:24:37
*/
@Data
@Table(name = "tbldistrict")
public class District {
    @Id
    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "city_code")
    private String cityCode;

    @Column(name = "district_brief_name")
    private String districtBriefName;

    @Column(name = "district_name")
    private String districtName;
}