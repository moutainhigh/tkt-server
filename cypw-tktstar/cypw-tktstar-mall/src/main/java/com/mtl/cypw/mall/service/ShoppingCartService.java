package com.mtl.cypw.mall.service;

import com.juqitech.service.utils.DateUtils;
import com.mtl.cypw.common.enums.CommonStateEnum;
import com.mtl.cypw.common.enums.DeleteEnum;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.exception.ValidationException;
import com.mtl.cypw.domain.mall.param.ShoppingCartParam;
import com.mtl.cypw.mall.mapper.ShoppingCartMapper;
import com.mtl.cypw.mall.model.ShoppingCart;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 14:54
 */
@Slf4j
@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    public ShoppingCart save(ShoppingCartParam param) {
        if (param == null || param.getMemberId() == null || param.getSkuId() == null) {
            throw new ValidationException("购物车商品不对哦~", ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        ShoppingCart shoppingCart = this.findBySkuId(param.getMemberId(), param.getSkuId());
        if (shoppingCart == null) {
            shoppingCart = new ShoppingCart();
            shoppingCart.setSkuId(param.getSkuId());
            shoppingCart.setSkuType(param.getSkuType());
            shoppingCart.setMemberId(param.getMemberId());
            shoppingCart.setProductId(param.getProductId());
            shoppingCart.setEnterpriseId(param.getEnterpriseId());
            shoppingCart.setStatus(CommonStateEnum.VALID.getCode());
        }
        if (shoppingCart.brandNew()) {
            shoppingCart.setQuantity(param.getQuantity());
            shoppingCartMapper.insert(shoppingCart);
        } else if (shoppingCart.getId() > 0 && param.getHasCover()) {
            shoppingCart.setQuantity(param.getQuantity());
            shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
        } else {
            shoppingCart.setQuantity(shoppingCart.getQuantity() + param.getQuantity());
            shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);
        }
        return shoppingCart;
    }

    public void invalidate(Integer memberId, List<Integer> skuIds) {
        if (memberId == null || CollectionUtils.isEmpty(skuIds)) {
            return;
        }
        List<ShoppingCart> shoppingCarts = this.findByMemberIdAndSkuIds(memberId, skuIds);
        Date current = DateUtils.now();
        for (ShoppingCart cart : shoppingCarts) {
            cart.setStatus(CommonStateEnum.INVALID.getCode());
            cart.setUpdateTime(current);
            shoppingCartMapper.updateByPrimaryKeySelective(cart);
        }
    }

    public void remove(ShoppingCart shoppingCart) {
        if (shoppingCart == null || shoppingCart.brandNew()) {
            throw new ValidationException("购物车商品飞走了~", ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        shoppingCart.setDeleted(DeleteEnum.DELETED.getCode());
        shoppingCartMapper.updateByPrimaryKeySelective(shoppingCart);

    }

    public int batchRemove(Integer memberId, List<Integer> skuIds) {
        if (memberId == null || CollectionUtils.isEmpty(skuIds)) {
            throw new ValidationException("购物车商品飞走了~", ErrorCode.ERROR_COMMON_PARAMETER.getCode());
        }
        int count = 0;
        List<ShoppingCart> shoppingCarts = this.findByMemberIdAndSkuIds(memberId, skuIds);
        for (ShoppingCart cart : shoppingCarts) {
            this.remove(cart);
            count++;
        }
        return count;
    }

    public int empty(Integer memberId) {
        List<ShoppingCart> shoppingCarts = this.findByMemberId(memberId);
        if (CollectionUtils.isEmpty(shoppingCarts)) {
            return 0;
        }
        for (ShoppingCart cart : shoppingCarts) {
            this.remove(cart);
        }
        return shoppingCarts.size();
    }

    public ShoppingCart findBySkuId(Integer memberId, Integer skuId) {
        if (memberId == null || skuId == null) {
            return null;
        }
        Example example = new Example(ShoppingCart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", memberId);
        criteria.andEqualTo("skuId", skuId);
        criteria.andEqualTo("deleted", DeleteEnum.EXIST.getCode());
        return shoppingCartMapper.selectOneByExample(example);
    }

    public List<ShoppingCart> findByMemberId(Integer memberId) {
        return this.findByMemberIdAndSkuIds(memberId, null);
    }

    public List<ShoppingCart> findByMemberIdAndSkuIds(Integer memberId, List<Integer> skuIds) {
        if (memberId == null) {
            return Collections.emptyList();
        }
        Example example = new Example(ShoppingCart.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("memberId", memberId);
        if (CollectionUtils.isNotEmpty(skuIds)) {
            criteria.andIn("skuId", skuIds);
        }
        criteria.andEqualTo("deleted", DeleteEnum.EXIST.getCode());
        example.orderBy("id").desc();
        return shoppingCartMapper.selectByExample(example);
    }

}
