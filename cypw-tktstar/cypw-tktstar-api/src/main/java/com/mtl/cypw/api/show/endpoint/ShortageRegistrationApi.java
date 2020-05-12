package com.mtl.cypw.api.show.endpoint;

import com.juqitech.request.GenericRequest;
import com.juqitech.response.TSingleResult;
import com.mtl.cypw.domain.show.param.ShortageRegistrationParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author tang.
 * @date 2019/12/2.
 */
public interface ShortageRegistrationApi {

    /**
     * 缺货登记
     * @param param
     * @return
     */
    @PostMapping("/endpoint/shortage_registration/create")
    TSingleResult<Boolean> create(@RequestBody GenericRequest<ShortageRegistrationParam> param);
}
