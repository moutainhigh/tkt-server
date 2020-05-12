package com.mtl.cypw.provider.coupon.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.coupon.endpoint.PromotionCouponApi;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.coupon.pojo.Promotion;
import com.mtl.cypw.coupon.pojo.PromotionCoupon;
import com.mtl.cypw.coupon.service.PromotionCouponService;
import com.mtl.cypw.coupon.service.PromotionService;
import com.mtl.cypw.domain.coupon.dto.PromotionCouponDTO;
import com.mtl.cypw.domain.coupon.param.MemberCouponParam;
import com.mtl.cypw.domain.coupon.param.PromotionCouponQueryParam;
import com.mtl.cypw.provider.coupon.converter.PromotionCouponConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@RestController
@Slf4j
public class PromotionCouponEndpoint implements PromotionCouponApi {

    @Resource
    PromotionCouponService promotionCouponService;

    @Resource
    PromotionService promotionService;

    @Resource
    PromotionCouponConverter converter;

    @Override
    public TMultiResult<PromotionCouponDTO> searchPromotionCouponList(GenericRequest<PromotionCouponQueryParam> request) {
        PromotionCouponQueryParam param = request.getParam();
        if (param == null || param.getMemberId() == null) {
            ResultBuilder.succTMulti(null);
        }
        List<PromotionCoupon> promotionCouponList = promotionCouponService.searchPromotionCouponList(param);
        return ResultBuilder.succTMulti(converter.toDto(promotionCouponList));
    }

    @Override
    public TMultiResult<Integer> searchPromotionIdListByMemberId(GenericRequest<PromotionCouponQueryParam> request) {
        PromotionCouponQueryParam param = request.getParam();
        if (param == null || param.getMemberId() == null) {
            return ResultBuilder.succTMulti(null);
        }
        List<Integer> promotionIdList = promotionCouponService.searchPromotionIdListByMemberId(param);
        return ResultBuilder.succTMulti(promotionIdList);
    }

    @Override
    public TSingleResult<Boolean> addPromotionCoupon(GenericRequest<MemberCouponParam> request) {
        MemberCouponParam param = request.getParam();
        if (param == null || param.getMemberId() == null || param.getPromotionId() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_PARAMETER.getCode(), ErrorCode.ERROR_COUPON_PARAMETER.getMsg());
        }
        Promotion promotion = promotionService.getPromotionById(param.getPromotionId());
        if (promotion == null) {
            log.error("没找到对应的优惠券");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_NOT_COUPON.getCode(), ErrorCode.ERROR_COUPON_NOT_COUPON.getMsg());
        }
        //修改优惠券库存
        ErrorCode errorCode = updateHaveExchangeQty(promotion);
        if (errorCode != null) {
            return ResultBuilder.failTSingle(errorCode.getCode(), errorCode.getMsg());
        }
        PromotionCoupon promotionCoupon = promotionCouponService.getPromotionCoupon(param.getPromotionId(), param.getMemberId());
        if (promotionCoupon == null) {
            promotionCoupon = new PromotionCoupon();
            if (promotion.getAvailableEndDate() != null && promotion.getAvailableBeginDate() != null && promotion.getAvailableDay() != null && promotion.getAvailableDay() > 0) {
                Date date = new Date();
                Date beginDate = date.compareTo(promotion.getAvailableBeginDate()) == 1 ? date : promotion.getAvailableBeginDate();
                Calendar c = Calendar.getInstance();
                c.setTime(beginDate);
                c.add(Calendar.DAY_OF_MONTH, promotion.getAvailableDay());
                Date endDate = c.getTime().compareTo(promotion.getAvailableEndDate()) == 1 ? promotion.getAvailableEndDate() : c.getTime();
                promotionCoupon.setEndDate(endDate);
            } else {
                promotionCoupon.setEndDate(promotion.getAvailableEndDate());
            }
            if (promotion.getAvailableBeginDate() != null) {
                Date date = new Date();
                Date beginDate = date.compareTo(promotion.getAvailableBeginDate()) == 1 ? date : promotion.getAvailableBeginDate();
                promotionCoupon.setBeginDate(beginDate);
            }
            promotionCoupon.setEnterpriseId(promotion.getEnterpriseId());
            promotionCoupon.setPromotionId(promotion.getPromotionId());
            promotionCoupon.setMemberId(param.getMemberId());
            promotionCoupon.setIsBinded(1);
            promotionCoupon.setBindDate(new Date());
            promotionCoupon.setCouponCode(addCouponCode(param.getMemberId()));
            promotionCouponService.addPromotionCoupon(promotionCoupon);
            return ResultBuilder.succTSingle(true);
        } else {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_ALREADY_RECEIVE.getCode(), ErrorCode.ERROR_COUPON_ALREADY_RECEIVE.getMsg());
        }
    }

    @Override
    public TSingleResult<Boolean> addExchangeCoupon(GenericRequest<MemberCouponParam> request) {
        MemberCouponParam param = request.getParam();
        if (param == null || param.getMemberId() == null || StringUtils.isEmpty(param.getCouponCode())) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_PARAMETER.getCode(), ErrorCode.ERROR_COUPON_PARAMETER.getMsg());
        }
        PromotionCoupon promotionCoupon = promotionCouponService.getPromotionCouponByCode(param.getCouponCode());
        if (promotionCoupon == null || promotionCoupon.getIsEnable() == 0) {
            log.error("错误的兑换码");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_EXCHANGE_CODE.getCode(), ErrorCode.ERROR_COUPON_EXCHANGE_CODE.getMsg());
        }
        if (promotionCoupon.getMemberId() != null || promotionCoupon.getIsBinded() == 1) {
            log.error("兑换码已使用");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_EXCHANGE_USED.getCode(), ErrorCode.ERROR_COUPON_EXCHANGE_USED.getMsg());
        }
        Promotion promotion = promotionService.getPromotionById(promotionCoupon.getPromotionId());
        if (promotion == null) {
            log.error("没找到对应的优惠券");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_NOT_COUPON.getCode(), ErrorCode.ERROR_COUPON_NOT_COUPON.getMsg());
        }
        if (!promotion.isEnabled() || 0 != promotion.getIsDelete()) {
            log.error("错误的兑换码");
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COUPON_EXCHANGE_CODE.getCode(), ErrorCode.ERROR_COUPON_EXCHANGE_CODE.getMsg());
        }
        //修改优惠券库存
        ErrorCode errorCode = updateHaveExchangeQty(promotion);
        if (errorCode != null) {
            return ResultBuilder.failTSingle(errorCode.getCode(), errorCode.getMsg());
        }
        if (promotion.getAvailableEndDate() != null && promotion.getAvailableBeginDate() != null && promotion.getAvailableDay() != null && promotion.getAvailableDay() > 0) {
            Date date = new Date();
            Date beginDate = date.compareTo(promotion.getAvailableBeginDate()) == 1 ? date : promotion.getAvailableBeginDate();
            Calendar c = Calendar.getInstance();
            c.setTime(beginDate);
            c.add(Calendar.DAY_OF_MONTH, promotion.getAvailableDay());
            Date endDate = c.getTime().compareTo(promotion.getAvailableEndDate()) == 1 ? promotion.getAvailableEndDate() : c.getTime();
            promotionCoupon.setEndDate(endDate);
        } else {
            promotionCoupon.setEndDate(promotion.getAvailableEndDate());
        }
        if (promotion.getAvailableBeginDate() != null) {
            Date date = new Date();
            Date beginDate = date.compareTo(promotion.getAvailableBeginDate()) == 1 ? date : promotion.getAvailableBeginDate();
            promotionCoupon.setBeginDate(beginDate);
        }
        promotionCoupon.setMemberId(param.getMemberId());
        promotionCoupon.setIsBinded(1);
        promotionCoupon.setBindDate(new Date());
        promotionCouponService.update(promotionCoupon);
        return ResultBuilder.succTSingle(true);
    }

    public ErrorCode updateHaveExchangeQty(Promotion promotion) {
        if (promotion.getAllowExchangeQty() != 0) {
            if (promotion.getAllowExchangeQty().equals(promotion.getHaveExchangeQty())) {
                log.error("优惠券领取已经达到上限");
                return ErrorCode.ERROR_COUPON_RECEIVE_FINISHED;
            } else {
                int count = promotion.getHaveExchangeQty() == null ? 0 : promotion.getHaveExchangeQty();
                count = 1 + count;
                promotion.setHaveExchangeQty(count);
                promotionService.update(promotion);
            }
        }
        return null;
    }

    private String addCouponCode(Integer memberId) {
        int prefix = 1000000 + memberId;
        int randomNum = (int) (Math.random() * 90 + 10);
        String suffix = System.currentTimeMillis() + "";
        suffix = suffix.substring(6);
        StringBuffer sb = new StringBuffer();
        sb.append("P").append(prefix).append(randomNum).append(suffix);
        return sb.toString();
    }

}
