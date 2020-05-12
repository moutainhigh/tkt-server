package com.mtl.cypw.stock.service;

import com.mtl.cypw.domain.stock.param.SeatLockParam;
import com.mtl.cypw.domain.stock.param.SeatReleaseParam;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-03 20:06
 */
public interface SeatLockService {

    boolean lockSeats(SeatLockParam request);

    boolean unlockSeats(SeatReleaseParam request);
}
