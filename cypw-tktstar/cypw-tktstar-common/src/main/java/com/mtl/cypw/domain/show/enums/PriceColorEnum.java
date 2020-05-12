package com.mtl.cypw.domain.show.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Johnathon.Yuan
 * @date 2020-01-15 13:45
 */
public enum PriceColorEnum {
    VVIP("VVIP", "#ff9900"),
    VIP("VIP", "#ffcc33"),
    A("A", "#cc0000"),
    B("B", "#663399"),
    C("C", "#669900"),
    D("D", "#0099CC"),
    E("E", "#ea4c88"),
    F("F", "#996633"),
    G("G", "#77cc33"),
    H("H", "#0066cc"),
    I("I", "#660000"),
    J("J", "#999900"),
    K("K", "#333399"),
    L("L", "#cc3333"),
    M("M", "#336600"),
    N("N", "#666600"),
    O("O", "#ADFF2F"),
    P("P", "#AB82FF"),
    Q("Q", "#A52A2A"),
    R("R", "#A0522D"),
    S("S", "#A020F0"),
    T("T", "#98FB98"),
    U("U", "#7CFC00"),
    V("V", "#7A67EE"),
    W("W", "#1C1C1C"),
    X("X", "#00CD00"),
    Y("Y", "#006400"),
    Z("Z", "#0000FF");

    private String clazz;

    private String color;

    PriceColorEnum(String clazz, String color) {
        this.clazz = clazz;
        this.color = color;
    }

    public static PriceColorEnum getObject(String clazz) {
        for (PriceColorEnum colorEnum : values()) {
            if (StringUtils.equals(colorEnum.getClazz(), clazz)) {
                return colorEnum;
            }
        }
        return null;
    }


    public String getClazz() {
        return clazz;
    }

    public String getColor() {
        return color;
    }

}
