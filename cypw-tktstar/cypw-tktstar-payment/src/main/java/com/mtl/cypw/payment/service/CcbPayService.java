package com.mtl.cypw.payment.service;

import CCBSign.RSASig;
import com.mtl.cypw.common.utils.MoneyUtils;
import com.mtl.cypw.domain.payment.callback.CcbCallbackParam;
import com.mtl.cypw.domain.payment.config.CcbConfigure;
import com.mtl.cypw.domain.payment.param.CcbPayRequestParam;
import com.mtl.cypw.payment.request.CcbPayRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tang.
 * @date 2020/1/7.
 */
@Service
@Slf4j
public class CcbPayService {

    public String combineH5PayData(CcbPayRequestParam param, CcbConfigure configure) {
        CcbPayRequest data = new CcbPayRequest();
        data.setMERCHANTID(configure.getMerchantId());
        data.setPOSID(configure.getPosId());
        data.setBRANCHID(configure.getBranchId());
        data.setORDERID(param.getOrderNo());
        data.setPAYMENT(MoneyUtils.getMoneyByCent(param.getOrderAmount()).toString());
        data.setREFERER(param.getReferer());
        String pubKey = configure.getPubKey();
        pubKey = pubKey.substring(pubKey.length() - 30, pubKey.length());
        data.setPUB(pubKey);
        data.setCLIENTIP(param.getClientIp());
        data.setINSTALLNUM(param.getInstallment());
        return CcbPayRequest.CCB_PAY_URL + data.toString();
    }

    public Boolean checkSign(CcbCallbackParam callback, CcbConfigure configure) {
        RSASig rsaSig = new RSASig();
        rsaSig.setPublicKey(configure.getPubKey());
        Boolean b = rsaSig.verifySigature(callback.getSign(), callback.toString());
        return b;
    }
}
