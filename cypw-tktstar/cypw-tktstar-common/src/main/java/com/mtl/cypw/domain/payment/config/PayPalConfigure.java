package com.mtl.cypw.domain.payment.config;

import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.base.rest.PayPalRESTException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author tang.
 * @date 2020/2/14.
 */
@Data
public class PayPalConfigure {
    /**
     * 支付货币：美元(USD)，人民币(CNY)
     */
    public String currencyCode = "USD";

    private String clientId;
    private String clientSecret;
    private String mode;

    public Boolean checkParam() {
        if (StringUtils.isEmpty(clientId) || StringUtils.isEmpty(clientSecret) || StringUtils.isEmpty(mode)) {
            return false;
        }
        return true;
    }

    public Map<String, String> payPalSdkConfig() {
        Map<String, String> sdkConfig = new HashMap<>();
        sdkConfig.put("mode", mode);
        return sdkConfig;
    }

    public OAuthTokenCredential authTokenCredential() {
        return new OAuthTokenCredential(clientId, clientSecret, payPalSdkConfig());
    }

    public APIContext apiContext() throws PayPalRESTException {
        APIContext apiContext = new APIContext(authTokenCredential().getAccessToken());
        apiContext.setConfigurationMap(payPalSdkConfig());
        return apiContext;
    }

}
