package com.mtl.cypw.show.pojo;

import java.util.Date;
import javax.persistence.*;
import lombok.Data;

/**
 * @author tang. 
 * @date 2019年11月27日 下午04:40:15
*/
@Data
@Table(name = "show_actor")
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "actor_name")
    private String actorName;

    @Column(name = "actor_image")
    private String actorImage;

    @Column(name = "sort_order")
    private Byte sortOrder;

    @Column(name = "is_delete")
    private Byte isDelete;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;
}