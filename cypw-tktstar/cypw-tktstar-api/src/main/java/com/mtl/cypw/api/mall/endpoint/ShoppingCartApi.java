package com.mtl.cypw.api.mall.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.request.IdRequest;
import com.juqitech.response.TMultiResult;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.mall.dto.ShoppingCartDTO;
import com.mtl.cypw.domain.mall.param.ShoppingCartOperateParam;
import com.mtl.cypw.domain.mall.param.ShoppingCartParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-02 21:27
 */
public interface ShoppingCartApi {

    String ShoppingCart_API = "ShoppingCartEndpoint";

    @PostMapping("/endpoint/v1/shopping/add_goods_to_shopping_cart")
    TSingleResult<ShoppingCartDTO> addGoodsToShoppingCart(@RequestBody GenericRequest<ShoppingCartParam> request);

    @PostMapping("/endpoint/v1/shopping/update_quantity_by_sku_id")
    TSingleResult<ShoppingCartDTO> updateQuantityBySkuId(@RequestBody GenericRequest<ShoppingCartParam> request);

    @PostMapping("/endpoint/v1/shopping/remove_goods_from_shopping_cart")
    TSingleResult<Integer> removeGoodsFromShoppingCart(@RequestBody GenericRequest<ShoppingCartOperateParam> request);

    @PostMapping("/endpoint/v1/shopping/empty_shopping_cart_by_userId")
    TSingleResult<Integer> emptyByUserId(@RequestBody IdRequest idRequest);

    @PostMapping("/endpoint/v1/shopping/find_shopping_cart_by_userId")
    TMultiResult<ShoppingCartDTO> findByUserId(@RequestBody IdRequest idRequest);


}
