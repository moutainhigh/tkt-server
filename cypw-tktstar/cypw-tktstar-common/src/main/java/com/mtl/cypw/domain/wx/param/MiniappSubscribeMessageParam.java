package com.mtl.cypw.domain.wx.param;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/4.
 */
@Data
public class MiniappSubscribeMessageParam {
    private String toUser;
    private String templateId;
    private String page;
    private String miniprogramState;
    private List<Data> dataList;

    public MiniappSubscribeMessageParam addData(MiniappSubscribeMessageParam.Data date) {
        if(this.dataList == null) {
            this.dataList = new ArrayList();
        }
        this.dataList.add(date);
        return this;
    }

    @lombok.Data
    public static class Data {
        private String name;
        private String value;

        public Data(String name, String value) {
            this.name = name;
            this.value = value;
        }
    }
}
