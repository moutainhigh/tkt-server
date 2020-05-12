package com.mtl.cypw.auth.cmb;

import cmb.netpayment.Security;
import com.mtl.cypw.common.utils.DateUtil;
import com.mtl.cypw.common.utils.MD5Util;
import com.mtl.cypw.common.utils.PropertyRepository;
import com.mtl.cypw.common.utils.UuidUtil;
import com.mtl.cypw.domain.auth.response.CmbAuthResponse;
import com.mtl.cypw.domain.auth.response.data.CmbAuthInfo;
import com.mtl.cypw.domain.payment.config.CmbConfigure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tang.
 * @date 2020/1/14.
 */
@Service
public class CmbService {

    public CmbAuthResponse auth(CmbConfigure config) {
        CmbAuthResponse dto = new CmbAuthResponse();
        dto.setMerchantNo(config.getCorpNo());
        dto.setReAuth(false);
        CmbAuthInfo authInfo = new CmbAuthInfo();
        authInfo.setTimestamp(DateUtil.parseDateToStr(new Date(), DateUtil.DATE_TIME_FORMAT_YYYYMMDDHHMISS));
        authInfo.setNonceStr(UuidUtil.getUuid());
        String signStr = config.getCorpNo() + authInfo.getTimestamp() + authInfo.getNonceStr() + config.getSecretKey();
        authInfo.setSign(MD5Util.md5(signStr));
        dto.setAuthInfo(authInfo);
        return dto;
    }

    public boolean verifySignature(String signData) throws Exception {
        Security verifier = CmbSecurity.getSecurity();
        return verifier.checkInfoFromBank(signData.getBytes("UTF-8"));
    }

}

@Slf4j
class CmbSecurity {

    private static Security security;

    static {
        try {
            security = new Security(PropertyRepository.CMB_PUBLIC_KEY_PATH);
        } catch (Exception e) {
            log.info("获取招行登录解密公钥错误,公钥地址：{}", PropertyRepository.CMB_PUBLIC_KEY_PATH);
        }
    }

    public static Security getSecurity() {

        return security;
    }
}

