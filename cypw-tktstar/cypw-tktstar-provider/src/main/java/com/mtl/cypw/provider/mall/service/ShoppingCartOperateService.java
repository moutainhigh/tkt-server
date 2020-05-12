package com.mtl.cypw.provider.mall.service;

import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.common.enums.ErrorCode;
import com.mtl.cypw.common.enums.SkuTypeEnum;
import com.mtl.cypw.common.exception.GeneralException;
import com.mtl.cypw.domain.mall.dto.ShoppingCartDTO;
import com.mtl.cypw.domain.mall.param.ShoppingCartOperateParam;
import com.mtl.cypw.domain.mall.param.ShoppingCartParam;
import com.mtl.cypw.mall.model.Merchandise;
import com.mtl.cypw.mall.model.ShoppingCart;
import com.mtl.cypw.mall.service.MerchandiseService;
import com.mtl.cypw.mall.service.ShoppingCartService;
import com.mtl.cypw.provider.mall.converter.ShoppingCartConverter;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.stock.model.Stock;
import com.mtl.cypw.stock.repository.StockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Johnathon.Yuan
 * @date 2020-03-04 21:47
 */
@Slf4j
@Service
public class ShoppingCartOperateService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private EventPriceService eventPriceService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private ShoppingCartConverter shoppingCartConverter;

    public TSingleResult<ShoppingCartDTO> mergeGoodsToShoppingCartBySkuId(ShoppingCartParam param) {
        if (param.getMemberId() == null || param.getProductId() == null || param.getSkuId() == null) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_PARAMETER.getCode(), ErrorCode.ERROR_COMMON_PARAMETER.getMsg());
        }
        Merchandise merchandise = merchandiseService.get(param.getProductId());
        if (merchandise == null || !merchandise.isSaleable()) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getCode(), "该商品已下架");
        }
        EventPrice price = eventPriceService.getEventPriceById(param.getSkuId());
        if (price == null || !price.isEnabled() || SkuTypeEnum.GOODS.getCode() != price.getProductType()) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getCode(), "该商品缺货中");
        }
        Stock stock = stockRepository.findOneBySkuIdAndType(SkuTypeEnum.GOODS, price.getPriceId());
        if (stock == null || stock.getActualInventory() < param.getQuantity()) {
            return ResultBuilder.failTSingle(ErrorCode.ERROR_COMMON_DATA_NOT_FOUND.getCode(), "该商品库存不足");
        }
        ShoppingCart shoppingCart = shoppingCartService.save(param);
        return ResultBuilder.succTSingle(shoppingCartConverter.convert(shoppingCart));

    }

    public TSingleResult<Integer> removeGoodsFromShoppingCart(ShoppingCartOperateParam param) {
        int count = 0;
        try {
            count = shoppingCartService.batchRemove(param.getMemberId(), param.getSkuIds());
        } catch (GeneralException e) {
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        }
        return ResultBuilder.succTSingle(count);
    }

    public TSingleResult<Integer> emptyByUserId(Integer memberId) {
        int count = 0;
        try {
            count = shoppingCartService.empty(memberId);
        } catch (GeneralException e) {
            return ResultBuilder.failTSingle(e.getCode(), e.getMessage());
        }
        return ResultBuilder.succTSingle(count);
    }
}
