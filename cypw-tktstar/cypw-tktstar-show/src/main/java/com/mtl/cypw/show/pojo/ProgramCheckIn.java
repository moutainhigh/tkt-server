package com.mtl.cypw.show.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author tang. 
 * @date 2019年11月27日 下午04:40:15
*/
@Data
@Table(name = "tblprogramcheckin")
public class ProgramCheckIn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "entrans_name")
    private String entranceName;

    @Column(name = "checkin_count")
    private String checkinCount;

    @Column(name = "checkin_account_ids")
    private String checkinAccountIds;

}