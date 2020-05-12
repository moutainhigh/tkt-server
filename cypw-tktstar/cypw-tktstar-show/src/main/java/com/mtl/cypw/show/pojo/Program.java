package com.mtl.cypw.show.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Mybatis Generator on 2019年11月20日 下午02:25:48
 */
@Data
@Table(name = "tblprogram")
public class Program {
    @Id
    @Column(name = "program_id")
    private Integer programId;

    @Column(name = "program_no")
    private String programNo;

    @Column(name = "program_title")
    private String programTitle;

    @Column(name = "program_title_brief")
    private String programTitleBrief;

    @Column(name = "venue_id")
    private Integer venueId;

    @Column(name = "program_type_id")
    private Integer programTypeId;

    @Column(name = "program_time")
    private String programTime;

    @Column(name = "program_price")
    private String programPrice;

    @Column(name = "program_brief")
    private String programBrief;

    @Column(name = "sale_date_begin")
    private Date saleDateBegin;

    @Column(name = "sale_date_end")
    private Date saleDateEnd;

    @Column(name = "is_publish")
    private Integer isPublish;

    @Column(name = "sale_status")
    private Integer saleStatus;

    @Column(name = "is_delete")
    private Integer isDelete;

    @Column(name = "app_sort")
    private Integer appSort;

    @Column(name = "wechat_sort")
    private Integer wechatSort;

    @Column(name = "pc_sort")
    private Integer pcSort;

    @Column(name = "app_recommend")
    private Integer appRecommend;

    @Column(name = "wechat_recommend")
    private Integer wechatRecommend;

    @Column(name = "pc_recommend")
    private Integer pcRecommend;

    @Column(name = "add_date")
    private Date addDate;

    @Column(name = "add_user")
    private Integer addUser;

    @Column(name = "update_date")
    private Date updateDate;

    @Column(name = "update_user")
    private Integer updateUser;

    @Column(name = "list_image")
    private String listImage;

    @Column(name = "detail_image")
    private String detailImage;

    @Column(name = "program_notice")
    private String programNotice;

    @Column(name = "app_show")
    private Integer appShow;

    @Column(name = "wechat_show")
    private Integer wechatShow;

    @Column(name = "pc_show")
    private Integer pcShow;

    @Column(name = "delivery_restrict")
    private String deliveryRestrict;

    @Column(name = "app_payment_restrict")
    private String appPaymentRestrict;

    @Column(name = "wechat_payment_restrict")
    private String wechatPaymentRestrict;

    @Column(name = "pc_payment_restrict")
    private String pcPaymentRestrict;

    @Column(name = "member_restrict")
    private String memberRestrict;

    @Column(name = "show_time_image")
    private Integer showTimeImage;

    @Column(name = "show_other_images")
    private String showOtherImages;

    @Column(name = "enterprise_id")
    private Integer enterpriseId;

    @Column(name = "program_content")
    private String programContent;

    @Column(name = "support_seat_flag")
    private Integer supportSeatFlag;

    @Column(name = "support_eticket")
    private Integer supportETicket;

    @Column(name = "is_official")
    private Integer isOfficial;

    @Column(name = "support_print_ticket")
    private Integer supportPrintTicket;

    @Column(name = "sponser_title")
    private String sponserTitle;

    @Column(name = "sponser_introduce")
    private String sponserIntroduce;

    @Column(name = "location_name")
    private String locationName;

    @Column(name = "location_address")
    private String locationAddress;

    @Column(name = "service_mobile")
    private String serviceMobile;

    @Column(name = "key_value")
    private String keyValue;

    @Column(name = "tags")
    private String tags;

    @Column(name = "checkin_time_type")
    private Integer checkinTimeType;

    @Column(name = "checkin_count_type")
    private Integer checkinCountType;

    @Column(name = "checkin_count")
    private Integer checkinCount;

}