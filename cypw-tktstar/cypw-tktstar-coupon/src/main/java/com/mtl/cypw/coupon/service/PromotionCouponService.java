package com.mtl.cypw.coupon.service;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.coupon.mapper.PromotionCouponMapper;
import com.mtl.cypw.coupon.pojo.PromotionCoupon;
import com.mtl.cypw.domain.coupon.enums.CouponStateEnum;
import com.mtl.cypw.domain.coupon.param.PromotionCouponQueryParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@Service
@Slf4j
public class PromotionCouponService {

    @Resource
    PromotionCouponMapper mapper;


    public PromotionCoupon findOneById(Integer couponId) {
        if (couponId == null) {
            return null;
        }
        return mapper.selectByPrimaryKey(couponId);
    }

    public List<PromotionCoupon> searchPromotionCouponList(PromotionCouponQueryParam param) {
        if (param == null) {
            return null;
        }
        Example example = new Example(PromotionCoupon.class);
        Example.Criteria cri = example.createCriteria();
        if (param.getMemberId() != null) {
            cri.andEqualTo("memberId", param.getMemberId());
        }
        if (param.getIsBind() != null) {
            cri.andEqualTo("isBinded", param.getIsBind());
        }
        if (param.getIsEnable() != null) {
            cri.andEqualTo("isEnable", param.getIsEnable());
        }
        if (param.getIsUsed() != null) {
            cri.andEqualTo("isUsed", param.getIsUsed());
        }
        if (param.getPromotionIds() != null && param.getPromotionIds().size() > 0) {
            cri.andIn("promotionId", param.getPromotionIds());
        }
        if (param.getCouponStateEnum() != null) {
            Date date = new Date();
            if (CouponStateEnum.NOT_USED.equals(param.getCouponStateEnum())) {
                cri.andEqualTo("isUsed", CouponStateEnum.NOT_USED.getCode());
                cri.andGreaterThanOrEqualTo("endDate", date);
            } else if (CouponStateEnum.ALREADY_USED.equals(param.getCouponStateEnum())) {
                cri.andEqualTo("isUsed", CouponStateEnum.ALREADY_USED.getCode());
            } else if (CouponStateEnum.EXPIRED.equals(param.getCouponStateEnum())) {
                cri.andEqualTo("isUsed", CouponStateEnum.NOT_USED.getCode());
                cri.andLessThanOrEqualTo("endDate", date);
            }
        }
        if (param.getEndEndDate() != null) {
            cri.andLessThanOrEqualTo("endDate", param.getEndEndDate());
        }
        return mapper.selectByExample(example);
    }

    public PromotionCoupon getPromotionCouponByCode(String couponCode) {
        if (couponCode == null) {
            return null;
        }
        Example example = new Example(PromotionCoupon.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("couponCode", couponCode);
        return mapper.selectOneByExample(example);
    }

    public PromotionCoupon getPromotionCoupon(Integer promotionId, Integer memberId) {
        if (promotionId == null || memberId == null) {
            return null;
        }
        Example example = new Example(PromotionCoupon.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("promotionId", promotionId);
        cri.andEqualTo("memberId", memberId);
        return mapper.selectOneByExample(example);
    }

    public List<Integer> searchPromotionIdListByMemberId(PromotionCouponQueryParam param) {
        List<PromotionCoupon> promotionCouponList = searchPromotionCouponList(param);
        if (promotionCouponList != null && promotionCouponList.size() > 0) {
            List<Integer> promotionIdList = new ArrayList<>();
            promotionCouponList.forEach(n -> promotionIdList.add(n.getPromotionId()));
            return promotionIdList;
        }
        return null;
    }

    public void addPromotionCoupon(PromotionCoupon promotionCoupon) {
        promotionCoupon.setAddDate(new Date());
        mapper.insertSelective(promotionCoupon);
    }

    public void update(PromotionCoupon promotionCoupon) {
        mapper.updateByPrimaryKeySelective(promotionCoupon);
    }

    /**
     * 将优惠券或兑换券置为已使用
     *
     * @param promotionCouponId
     * @param memberId
     * @return
     */
    public Boolean updateUsed(Integer promotionCouponId, Integer memberId, String orderNo) {
        PromotionCoupon promotionCoupon = findOneById(promotionCouponId);
        if (checkCouponState(promotionCoupon, memberId)) {
            promotionCoupon.setIsUsed(1);
            promotionCoupon.setUseDate(DateUtils.now());
            promotionCoupon.setOrderNo(orderNo);
            update(promotionCoupon);
            return true;
        }
        return false;
    }

    /**
     * 将优惠券或兑换券释放
     * @param promotionCouponId
     * @return
     */
    public Boolean unlock(Integer promotionCouponId, String orderNo) {
        PromotionCoupon promotionCoupon = findOneById(promotionCouponId);
        if (promotionCoupon == null) {
            log.error("coupon not found, couponId [{}]", promotionCouponId);
            return false;
        }
        if (promotionCoupon.isUnUsed()) {
            log.error("coupon not used, couponId [{}]", promotionCouponId);
            return true;
        }

        if (!StringUtils.equals(promotionCoupon.getOrderNo(), orderNo)) {
            log.error("user is illegal, couponId [{}], binding [{}], current [{}]", promotionCouponId,
                    promotionCoupon.getOrderNo(), orderNo);
            return false;
        }
        promotionCoupon.setIsUsed(BooleanUtils.toIntegerObject(false));
        promotionCoupon.setUseDate(null);
        promotionCoupon.setOrderNo(null);
        mapper.updateByPrimaryKey(promotionCoupon);
        return true;
    }

    private Boolean checkCouponState(PromotionCoupon promotionCoupon, Integer memberId) {
        if (promotionCoupon == null || memberId == null) {
            log.error("参数错误");
            return false;
        }
        if (promotionCoupon.getIsEnable() != 1) {
            log.error("优惠券或兑换券已停用");
            return false;
        }
        if (promotionCoupon.getIsUsed() == 1) {
            log.error("优惠券或兑换券已使用");
            return false;
        }
        if (!promotionCoupon.getMemberId().equals(memberId)) {
            log.error("只能操作自己优惠券或兑换券");
            return false;
        }
        return true;
    }
}
