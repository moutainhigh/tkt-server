package com.mtl.cypw.test;

import com.google.common.collect.Lists;
import com.mtl.cypw.domain.order.enums.CodeSourceEnum;
import com.mtl.cypw.domain.order.enums.CodeStatusEnum;
import com.mtl.cypw.order.model.CodeRepository;
import com.mtl.cypw.order.service.CodeRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author Johnathon.Yuan
 * @date 2019-12-13 21:13
 */
@Slf4j
public class CodeTest extends BaseTest {

    @Autowired
    private CodeRepositoryService codeRepositoryServiceImpl;

    @Test
    public void testCode() {
        long star = System.currentTimeMillis();
        codeRepositoryServiceImpl.codeGenerate();
        long end = System.currentTimeMillis();
        log.info("耗费时长：" + (end - star)/1000 + " s" );
    }

    @Test
    public void testTPCode() {
        long star = System.currentTimeMillis();
        List<CodeRepository> codes = Lists.newArrayList();
        for (int i = 0; i < 100; i++) {
            CodeRepository codeRepository = new CodeRepository();
            codeRepository.setCodeSource(CodeSourceEnum.THIRD_PARTY_CODE.getCode());
            codeRepository.setCode(RandomStringUtils.random(11, "0123456789"));
            codeRepository.setBitLength(11);
            codeRepository.setPriceId(9999);
            codeRepository.setEventId(9999);
            codeRepository.setStatus(CodeStatusEnum.NOT_USED.getCode());
            codes.add(codeRepository);
        }
        int count = codeRepositoryServiceImpl.batchImportThirdPartyCodes(codes);
        long end = System.currentTimeMillis();
        log.info("总计数量"+ count + ", 耗费时长：" + (end - star)/1000 + " s" );
    }
}
