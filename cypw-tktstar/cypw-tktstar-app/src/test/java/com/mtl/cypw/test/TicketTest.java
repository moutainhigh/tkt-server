package com.mtl.cypw.test;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.ticket.dto.CheckRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.FetchPaperResultDTO;
import com.mtl.cypw.domain.ticket.dto.FetchRecordQueryDTO;
import com.mtl.cypw.domain.ticket.dto.TicketPaperResultDTO;
import com.mtl.cypw.domain.ticket.enums.CheckChannelEnum;
import com.mtl.cypw.domain.ticket.enums.CheckMethodEnum;
import com.mtl.cypw.domain.ticket.enums.DeviceTypeEnum;
import com.mtl.cypw.domain.ticket.enums.FetchMethodEnum;
import com.mtl.cypw.domain.ticket.enums.HandleTypeEnum;
import com.mtl.cypw.domain.ticket.param.CheckInDeviceParam;
import com.mtl.cypw.domain.ticket.param.CheckInQueryParam;
import com.mtl.cypw.domain.ticket.param.CheckInTicketParam;
import com.mtl.cypw.domain.ticket.param.FetchCommandParam;
import com.mtl.cypw.domain.ticket.param.FetchQueryParam;
import com.mtl.cypw.provider.ticket.service.CheckService;
import com.mtl.cypw.provider.ticket.service.FetchService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Johnathon.Yuan
 * @date 2020-02-19 17:30
 */
@Slf4j
public class TicketTest extends BaseTest {

    @Autowired
    private CheckService checkServiceImpl;

    @Autowired
    private FetchService fetchServiceImpl;

    @Test
    public void testCode() {
        CheckInQueryParam param = new CheckInQueryParam();
        param.setCheckCode("1217314045");
        param.setCheckUserId(7);
        param.setEnterpriseId(1);
        TicketPaperResultDTO dto = checkServiceImpl.findTicketByCheckCode(param);
        log.info(JsonUtils.toJson(dto));
    }

    @Test
    public void testMobileNo() {
        CheckInQueryParam param = new CheckInQueryParam();
        param.setMobileNo("13817372771");
        param.setCheckUserId(2);
        param.setEnterpriseId(1);
        TicketPaperResultDTO dto = checkServiceImpl.findTicketByMobileNo(param);
        log.info(JsonUtils.toJson(dto));
    }

    @Test
    public void testConsume() {
        CheckInTicketParam param = new CheckInTicketParam();
        param.setCheckCode("1050033623");
        param.setCheckChannel(CheckChannelEnum.APPLET.getCode());
        param.setCheckMethod(CheckMethodEnum.QR_CODE.getCode());
        CheckInDeviceParam checkInDeviceParam = new CheckInDeviceParam();
        checkInDeviceParam.setDeviceType(DeviceTypeEnum.TELEPHONE.getCode());
        checkInDeviceParam.setDeviceVersion("v1.0.0");
        checkInDeviceParam.setDeviceUniqueCode("ACSA44158775621");
        checkInDeviceParam.setIdCard("522131198901014512");
        checkInDeviceParam.setIdCardName("正方形");
        checkInDeviceParam.setBindingIdCard("522131198901014514");
        checkInDeviceParam.setBindingIdCardName("圆形");
        param.setCheckInDeviceParam(checkInDeviceParam);
        param.setCheckUserId(2);
        param.setEnterpriseId(1);
        TicketPaperResultDTO dto = checkServiceImpl.consume(param);
        log.info(JsonUtils.toJson(dto));
    }

    @Test
    public void testQuery() {
        CheckInQueryParam param = new CheckInQueryParam();
        param.setCheckDate(DateUtils.now());
        param.setCheckUserId(2);
        param.setEnterpriseId(1);
        CheckRecordQueryDTO dto = checkServiceImpl.findCheckRecordByQuery(param, null);
        log.info(JsonUtils.toJson(dto));
    }

    @Test
    public void testFetchCode() {
        FetchQueryParam param = new FetchQueryParam();
        param.setFetchCode("2350181580");
        param.setFetchMethod(FetchMethodEnum.APPLET.getCode());
        param.setFetchUserId(4);
        param.setEnterpriseId(7);
        FetchPaperResultDTO dto = fetchServiceImpl.findOrderByFetchCode(param);
        log.info(JsonUtils.toJson(dto));
    }

    @Test
    public void testFetchAck() {
        FetchCommandParam param = new FetchCommandParam();
        param.setHandleType(HandleTypeEnum.NORMAL.getCode());
        param.setFetchCode("2350181580");
        param.setFetchMethod(FetchMethodEnum.APPLET.getCode());
        param.setFetchUserId(4);
        param.setEnterpriseId(7);

        FetchPaperResultDTO dto = fetchServiceImpl.ackByGoods(param);
        log.info(JsonUtils.toJson(dto));
    }


    @Test
    public void testQueryVoucher() {
        FetchQueryParam param = new FetchQueryParam();
        param.setFetchUserId(4);
        param.setFetchDate(DateUtils.now());
        param.setEnterpriseId(7);

        FetchRecordQueryDTO dto = fetchServiceImpl.findFetchRecordByQuery(param, null);
        log.info(JsonUtils.toJson(dto));
    }
}
