package com.mtl.cypw.domain.show.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/11/20.
 */
@Data
public class ProgramDTO {
    private Integer programId;

    private String programNo;

    private String programTitle;

    private String programTitleBrief;

    private Integer venueId;

    private Integer programTypeId;

    private String programTime;

    private String programPrice;

    private String programBrief;

    private Date saleDateBegin;

    private Date saleDateEnd;

    private Integer isPublish;

    private Integer saleStatus;

    private Integer isDelete;

    private Integer appSort;

    private Integer wechatSort;

    private Integer pcSort;

    private Integer appRecommend;

    private Integer wechatRecommend;

    private Integer pcRecommend;

    private Date addDate;

    private Integer addUser;

    private Date updateDate;

    private Integer updateUser;

    private String listImage;

    private String detailImage;

    private String programNotice;

    private Integer appShow;

    private Integer wechatShow;

    private Integer pcShow;

    private String deliveryRestrict;

    private String appPaymentRestrict;

    private String wechatPaymentRestrict;

    private String pcPaymentRestrict;

    private String memberRestrict;

    private Integer showTimeImage;

    private String showOtherImages;

    private Integer enterpriseId;

    private String programContent;

    private Integer supportSeatFlag;

    private Integer supportETicket;

    private Integer isOfficial;

    private List<FetchTicketWayDTO> fetchTicketWayList;

    private String sponserTitle;

    private String sponserIntroduce;

    private String locationName;

    private String locationAddress;

    private String serviceMobile;

    private String keyValue;

    private String tags;

    private List<ProgramCheckInDTO> programCheckInList;
}
