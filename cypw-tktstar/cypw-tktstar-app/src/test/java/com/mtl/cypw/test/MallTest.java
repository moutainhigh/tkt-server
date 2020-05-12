package com.mtl.cypw.test;

import com.google.common.collect.Lists;
import com.juqitech.response.TSingleResult;
import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.utils.JsonUtils;
import com.mtl.cypw.domain.mall.dto.MerchandiseDTO;
import com.mtl.cypw.domain.mall.dto.MerchandiseSpecDTO;
import com.mtl.cypw.domain.mall.dto.ShoppingCartDTO;
import com.mtl.cypw.domain.mall.param.MerchandiseQueryParam;
import com.mtl.cypw.domain.mall.param.ShoppingCartOperateParam;
import com.mtl.cypw.domain.mall.param.ShoppingCartParam;
import com.mtl.cypw.mall.model.Merchandise;
import com.mtl.cypw.mall.model.ShoppingCart;
import com.mtl.cypw.mall.service.MerchandiseService;
import com.mtl.cypw.mall.service.ShoppingCartService;
import com.mtl.cypw.provider.mall.converter.MerchandiseConverter;
import com.mtl.cypw.provider.mall.converter.MerchandiseSpecConverter;
import com.mtl.cypw.provider.mall.converter.ShoppingCartConverter;
import com.mtl.cypw.provider.mall.service.ShoppingCartOperateService;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-03-04 19:18
 */
@Slf4j
public class MallTest extends BaseTest {

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private MerchandiseConverter merchandiseConverter;

    @Autowired
    private MerchandiseSpecConverter merchandiseSpecConverter;

    @Autowired
    private ShoppingCartOperateService shoppingCartOperateService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartConverter shoppingCartConverter;

    @Test
    public void listTest() {
        MerchandiseQueryParam param = new MerchandiseQueryParam();
        param.setEnterpriseId(7);
        Pagination pagination = new Pagination(0, 5, 0);
        List<Merchandise> merchandises = merchandiseService.pageQuery(param, pagination);
        List<MerchandiseDTO> dtoList = merchandiseConverter.batchConvert(merchandises);
        log.info(JsonUtils.toJson(dtoList));
    }

    @Test
    public void detailTest() {
        MerchandiseQueryParam param = new MerchandiseQueryParam();
        param.setEnterpriseId(1);
        param.setMerchandiseId(1079);
        Merchandise merchandise = merchandiseService.getDetail(param.getMerchandiseId(), param.getEnterpriseId());
        MerchandiseDTO dto = merchandiseConverter.convert(merchandise);
        log.info(JsonUtils.toJson(dto));
    }

    @Test
    public void pricesTest() {
        MerchandiseQueryParam param = new MerchandiseQueryParam();
        param.setEnterpriseId(1);
        param.setMerchandiseId(1079);
        List<EventPrice> prices = eventPriceService.findPricesByProductIdWithMall(param.getMerchandiseId());
        List<MerchandiseSpecDTO> dtoList = merchandiseSpecConverter.batchConvert(prices);
        log.info(JsonUtils.toJson(dtoList));
    }

    @Test
    public void mergeCartTest() {
        ShoppingCartParam param = new ShoppingCartParam();
        param.setEnterpriseId(1);
        param.setMemberId(80001036);
        param.setSkuId(126);
        param.setProductId(1079);
        param.setSkuType(SkuTypeEnum.GOODS.getCode());
        param.setQuantity(6);
        TSingleResult<ShoppingCartDTO> result = shoppingCartOperateService.mergeGoodsToShoppingCartBySkuId(param);
        log.info(JsonUtils.toJson(result));
    }

    @Test
    public void queryCartTest() {
        List<ShoppingCart> shoppingCarts = shoppingCartService.findByMemberId(80001036);
        List<ShoppingCartDTO> dtoList = shoppingCartConverter.batchConvert(shoppingCarts);
        log.info(JsonUtils.toJson(dtoList));
    }


    @Test
    public void removeCartTest() {
        ShoppingCartOperateParam param = new ShoppingCartOperateParam();
        param.setMemberId(80001036);
        param.setSkuIds(Lists.newArrayList(127));
        param.setSkuType(SkuTypeEnum.GOODS.getCode());
        TSingleResult<Integer> result = shoppingCartOperateService.removeGoodsFromShoppingCart(param);
        log.info(JsonUtils.toJson(result));
    }

    @Test
    public void emptyCartTest() {
        TSingleResult<Integer> result = shoppingCartOperateService.emptyByUserId(80001036);
        log.info(JsonUtils.toJson(result));
    }
}
