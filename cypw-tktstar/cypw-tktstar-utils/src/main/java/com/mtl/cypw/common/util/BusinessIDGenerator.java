package com.mtl.cypw.common.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.RandomUtils;

import java.util.Set;

/**
 * 用于各业务 ID 生成（15位、目前不保证唯一，适用于并发低的业务）
 * @author Johnathon.Yuan
 * @date 2020-03-18 17:50
 */
public class BusinessIDGenerator {

    public static String genID(GeneratorModule module){
        if (module == null) {
            module = GeneratorModule.BIZ_OTHER;
        }
        StringBuilder builder = new StringBuilder(16);
        builder.append(module.getCode());
        builder.append(String.valueOf(System.currentTimeMillis()).substring(5));
        builder.append(String.valueOf(System.nanoTime()).substring( 12));
        builder.append(RandomUtils.nextInt(10, 99));
        return builder.toString();
    }

}
