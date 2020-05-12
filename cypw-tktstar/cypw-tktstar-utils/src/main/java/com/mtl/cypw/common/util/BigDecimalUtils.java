package com.mtl.cypw.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-09 10:32
 */
public class BigDecimalUtils {

    public static long rounded2Long(BigDecimal value) {
        if (value == null) {
            return 0;
        }
        return value.setScale(0, RoundingMode.HALF_UP).longValue();
    }

}
