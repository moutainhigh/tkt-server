//package com.mtl.cypw.test;
//
//import com.alibaba.fastjson.JSONObject;
//import com.google.gson.Gson;
//import com.mtl.cypw.common.enums.ErrorCode;
//import com.mtl.cypw.common.exception.BusinessException;
//import com.mtl.cypw.domain.payment.config.CmbConfigure;
//import com.mtl.cypw.domain.payment.enums.PayTypeEnum;
//import com.mtl.cypw.mpm.model.EnterprisePayType;
//import com.mtl.cypw.mpm.service.EnterprisePayTypeService;
//import com.mtl.cypw.payment.service.CmbPayService;
//import com.mtl.cypw.payment.service.WechatPayService;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.StringUtils;
//import org.junit.Test;
//
//import javax.annotation.Resource;
//
///**
// * @author tang.
// * @date 2020/1/3.
// */
//@Slf4j
//public class PayTest extends BaseTest {
//
//    @Resource
//    WechatPayService wechatPayService;
//
//    @Resource
//    EnterprisePayTypeService enterprisePayTypeServiceImpl;
//
//    String wxNotifyUrl = "https://yuyuandev.fhlun.com/wx/notify";
//
//    @Resource
//    CmbPayService cmbPayService;
//
//
//    @Test
//    public void pay() {
//        String str = "{\"sign\":\"VAfLw09HlICb+0yRTGE7/tbh5oyIFsylVnK7lB8rPdh6LAgaZxxBuHRC3l2v8ebswp5wvDX4AhtuVtzt57Dd+xlPQk3uJiTy+All8Cj2nh7yU6kwuAFqx4s6gm4Ps3wXG+QjqjqLbCwNu7p/Lz623c829QjzzDW+0zxRN+399Tc=\",\"noticeData\":{\"bankDate\":\"20200107\",\"dateTime\":\"20200107141119\",\"httpMethod\":\"POST\",\"noticeUrl\":\"https://yuyuandev.fhlun.com/api/pub/v1/notify/cmb\",\"bankSerialNo\":\"20210764200000000100\",\"date\":\"20200107\",\"cardType\":\"03\",\"discountAmount\":\"0.00\",\"amount\":\"0.01\",\"orderNo\":\"134660132546592768\",\"merchantPara\":\"\",\"noticeSerialNo\":\"20200107134660132546592768\",\"merchantNo\":\"000524\",\"discountFlag\":\"N\",\"branchNo\":\"0021\",\"noticeType\":\"BKPAYRTN\"},\"signType\":\"RSA\",\"charset\":\"UTF-8\",\"version\":\"1.0\"}";
//        try {
//            cmbPayService.checkSign(str, getConfigure(8));
//        } catch (Exception e) {
//        }
//    }
//
//
//    private CmbConfigure getConfigure(Integer enterpriseId) {
//        if (enterpriseId == null) {
//            throw new BusinessException(ErrorCode.ERROR_COMMON_PARAMETER.getMsg(), ErrorCode.ERROR_COMMON_PARAMETER.getCode());
//        }
//        EnterprisePayType enterprisePayType = enterprisePayTypeServiceImpl.getEnterprisePayType(enterpriseId, PayTypeEnum.CMB_PAY);
//        if (enterprisePayType != null && StringUtils.isNotEmpty(enterprisePayType.getConfigJson())) {
//            CmbConfigure configure = (new Gson()).fromJson(enterprisePayType.getConfigJson(), CmbConfigure.class);
//            return configure;
//        } else {
//            log.error("招行支付，商户配置错误,enterpriseId:{},config:{}", enterpriseId, JSONObject.toJSONString(enterprisePayType.getConfigJson()));
//            throw new BusinessException(ErrorCode.ERROR_PAY_CONFIG.getMsg(), ErrorCode.ERROR_PAY_CONFIG.getCode());
//        }
//    }
//}
