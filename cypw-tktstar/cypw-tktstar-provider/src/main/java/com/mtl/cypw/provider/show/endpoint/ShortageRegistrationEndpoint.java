package com.mtl.cypw.provider.show.endpoint;

import com.alibaba.fastjson.JSONObject;
import com.juqitech.request.GenericRequest;
import com.juqitech.response.ResultBuilder;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.api.show.endpoint.ShortageRegistrationApi;
import com.mtl.cypw.domain.show.param.ShortageRegistrationParam;
import com.mtl.cypw.member.pojo.Member;
import com.mtl.cypw.member.service.MemberService;
import com.mtl.cypw.show.pojo.Event;
import com.mtl.cypw.show.pojo.EventPrice;
import com.mtl.cypw.show.pojo.ShortageRegistration;
import com.mtl.cypw.show.service.EventPriceService;
import com.mtl.cypw.show.service.EventService;
import com.mtl.cypw.show.service.ShortageRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tang.
 * @date 2019/12/2.
 */
@RestController
@Slf4j
public class ShortageRegistrationEndpoint implements ShortageRegistrationApi {

    @Resource
    ShortageRegistrationService shortageRegistrationService;

    @Resource
    EventPriceService eventPriceService;

    @Resource
    EventService eventService;

    @Resource
    MemberService memberService;

    @Override
    public TSingleResult<Boolean> create(GenericRequest<ShortageRegistrationParam> param) {
        log.debug("新增缺货登记，request：{}", JSONObject.toJSONString(param));
        TSingleResult<Boolean> result = ResultBuilder.succTSingle(false);
        ShortageRegistrationParam registrationParam = param.getParam();
        if (registrationParam.getMemberId() != null && registrationParam.getEventPriceId() != null) {
            ShortageRegistration shortageRegistration = shortageRegistrationService.getShortageRegistration(registrationParam.getMemberId(), registrationParam.getEventPriceId());
            if (shortageRegistration == null) {
                Member member = memberService.getMemberById(registrationParam.getMemberId());
                EventPrice eventPrice = eventPriceService.getEventPriceById(registrationParam.getEventPriceId());
                if (eventPrice != null && member != null) {
                    Event event = eventService.findOneById(eventPrice.getEventId());
                    if (event != null) {
                        shortageRegistration = new ShortageRegistration();
                        shortageRegistration.setMemberId(registrationParam.getMemberId());
                        if (StringUtils.isNotEmpty(registrationParam.getRegisterPhone())) {
                            shortageRegistration.setRegisterPhone(registrationParam.getRegisterPhone());
                        } else {
                            shortageRegistration.setRegisterPhone(member.getMemberMobile());
                        }
                        shortageRegistration.setProgramId(event.getProgramId());
                        shortageRegistration.setEventId(event.getEventId());
                        shortageRegistration.setEventPriceId(eventPrice.getPriceId());
                        shortageRegistrationService.create(shortageRegistration);
                        result.setData(true);
                    }
                }
            } else {
                log.debug("该用户已经登记过该场次票价的演出了");
            }
        }
        return result;
    }
}
