package com.mtl.cypw.show.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年12月02日 上午10:30:20
*/
@Data
@Table(name = "show_shortage_registration")
public class ShortageRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "register_phone")
    private String registerPhone;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "event_id")
    private Integer eventId;

    @Column(name = "event_price_id")
    private Integer eventPriceId;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "is_delete")
    private Boolean isDelete;
}