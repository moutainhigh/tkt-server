package com.mtl.cypw.order.service;

import com.mtl.cypw.order.model.CodeRepository;

import java.util.List;

/**
 * @author zhengyou.yuan
 * @date 2019-11-28 13:19
 */
public interface CodeRepositoryService {

    void codeGenerate();

    int batchImportThirdPartyCodes(List<CodeRepository> codeRepositories);

    List<CodeRepository> findListByStatusAndLimitNumber(CodeRepository codeRepository, int limit);

    CodeRepository findOneByCode(String code);

    int countByBitAndStatus(int bit, int status);

    int countByThirdPartyUsable(CodeRepository codeRepository, int status);

    void bulkUpdate(List<CodeRepository> codes);

}
