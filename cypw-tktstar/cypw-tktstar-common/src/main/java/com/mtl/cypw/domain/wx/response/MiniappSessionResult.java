package com.mtl.cypw.domain.wx.response;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tang.
 * @date 2020/3/10.
 */
@Data
public class MiniappSessionResult implements Serializable {
    @SerializedName("session_key")
    private String sessionKey;
    @SerializedName("openid")
    private String openid;
    @SerializedName("unionid")
    private String unionId;
}
