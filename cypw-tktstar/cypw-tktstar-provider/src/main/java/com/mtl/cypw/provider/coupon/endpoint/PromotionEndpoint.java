package com.mtl.cypw.provider.coupon.endpoint;

import com.juqitech.request.QueryRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TMultiResult;
import com.mtl.cypw.api.coupon.endpoint.PromotionApi;
import com.mtl.cypw.coupon.pojo.PromotionProgram;
import com.mtl.cypw.coupon.service.PromotionProgramService;
import com.mtl.cypw.coupon.service.PromotionService;
import com.mtl.cypw.domain.coupon.dto.PromotionDTO;
import com.mtl.cypw.domain.coupon.enums.PromotionPointTypeEnum;
import com.mtl.cypw.domain.coupon.param.PromotionQueryParam;
import com.mtl.cypw.provider.coupon.converter.PromotionConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tang.
 * @date 2019/12/4.
 */
@RestController
@Slf4j
public class PromotionEndpoint implements PromotionApi {

    @Resource
    PromotionService promotionService;

    @Resource
    PromotionConverter promotionConverter;

    @Resource
    PromotionProgramService promotionProgramService;

    @Override
    public TMultiResult<PromotionDTO> searchPromotionList(QueryRequest<PromotionQueryParam> request) {
        log.debug("优惠券查询");
        PromotionQueryParam param = request.getParam();
        if (param != null && param.getProgramId() != null) {
            List<PromotionProgram> promotionPrograms = promotionProgramService.searchPromotionProgramList(param.getProgramId());
            if (promotionPrograms != null && promotionPrograms.size() > 0) {
                List<Integer> promotionIds = new ArrayList<>();
                promotionPrograms.forEach(n -> promotionIds.add(n.getPromotionId()));
                param.setProgramPromotionIds(promotionIds);
            }
        }
        return ResultBuilder.succTMulti(promotionConverter.toDto(promotionService.searchPromotionList(param)));
    }
}
