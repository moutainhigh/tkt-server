package com.mtl.cypw.test;

import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.stock.param.SeatLockParam;
import com.mtl.cypw.stock.service.SeatLockService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-17 14:46
 */
public class SeatLockTest extends BaseTest {


    @Autowired
    private SeatLockService seatLockServiceImpl;

    @Test
    public void testSeatLock() {
        String param = "{\"enterpriseId\":7,\"eventId\":44,\"memberId\":80001062,\"orderId\":839,\"seatIds\":[16267,16237,16479],\"sellType\":\"SALE\"}";
        SeatLockParam lockParam = JsonUtils.toObject(param, SeatLockParam.class);
        seatLockServiceImpl.lockSeats(lockParam);
    }
}
