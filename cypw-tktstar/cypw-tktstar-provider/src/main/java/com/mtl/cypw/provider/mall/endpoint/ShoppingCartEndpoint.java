package com.mtl.cypw.provider.mall.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.mall.endpoint.ShoppingCartApi;
import com.mtl.cypw.domain.mall.dto.ShoppingCartDTO;
import com.mtl.cypw.domain.mall.param.ShoppingCartOperateParam;
import com.mtl.cypw.domain.mall.param.ShoppingCartParam;
import com.mtl.cypw.mall.model.ShoppingCart;
import com.mtl.cypw.mall.service.ShoppingCartService;
import com.mtl.cypw.provider.mall.converter.ShoppingCartConverter;
import com.mtl.cypw.provider.mall.service.ShoppingCartOperateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-04 15:55
 */
@RestController
@Slf4j
public class ShoppingCartEndpoint implements ShoppingCartApi {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartConverter shoppingCartConverter;

    @Autowired
    private ShoppingCartOperateService shoppingCartOperateService;

    @Override
    public TSingleResult<ShoppingCartDTO> addGoodsToShoppingCart(GenericRequest<ShoppingCartParam> request) {
        return shoppingCartOperateService.mergeGoodsToShoppingCartBySkuId(request.getParam());
    }

    @Override
    public TSingleResult<ShoppingCartDTO> updateQuantityBySkuId(GenericRequest<ShoppingCartParam> request) {
        return shoppingCartOperateService.mergeGoodsToShoppingCartBySkuId(request.getParam());
    }

    @Override
    public TSingleResult<Integer> removeGoodsFromShoppingCart(GenericRequest<ShoppingCartOperateParam> request) {
        return shoppingCartOperateService.removeGoodsFromShoppingCart(request.getParam());
    }

    @Override
    public TSingleResult<Integer> emptyByUserId(IdRequest idRequest) {
        return shoppingCartOperateService.emptyByUserId(Integer.valueOf(idRequest.getId()));
    }

    @Override
    public TMultiResult<ShoppingCartDTO> findByUserId(IdRequest idRequest) {
        Integer memberId = Integer.valueOf(idRequest.getId());
        List<ShoppingCart> shoppingCarts = shoppingCartService.findByMemberId(memberId);
        return ResultBuilder.succTMulti(shoppingCartConverter.batchConvert(shoppingCarts));
    }
}
