package com.mtl.cypw.order.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mtl.cypw.domain.order.enums.CodeSourceEnum;
import com.mtl.cypw.domain.order.enums.CodeStatusEnum;
import com.mtl.cypw.order.mapper.CodeRepositoryMapper;
import com.mtl.cypw.order.model.CodeRepository;
import com.mtl.cypw.order.service.CodeRepositoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Johnathon.Yuan
 * @date 2019-11-28 13:30
 */
@Slf4j
@Service
public class CodeRepositoryServiceImpl implements CodeRepositoryService {

    private static final String CODE_CHARACTERS = "0123456789";

    private static final HashMap<String, Integer> codeBitMap;

    static {
        codeBitMap = Maps.newHashMap();
        codeBitMap.put("9", 500);
    }

    @Autowired
    private CodeRepositoryMapper codeRepositoryMapper;

    @Override
    public void codeGenerate() {
        for(Map.Entry<String,Integer> entry: codeBitMap.entrySet()){
            int bit = NumberUtils.toInt(entry.getKey());
            if(entry.getValue() > Math.pow(10, bit-1)) {
                continue;
            }
            int remainder = this.countByBitAndStatus(bit, CodeStatusEnum.NOT_USED.getCode());
            while (remainder < entry.getValue()) {
                int step = 0;
                int size = entry.getValue() - remainder;
                List<CodeRepository> repos = Lists.newArrayListWithCapacity(size);
                List<CodeRepository> localCodes = Lists.newArrayListWithCapacity(size);
                while (step < size) {
                    String randomStr = RandomStringUtils.random(bit, CODE_CHARACTERS);
                    CodeRepository exist = this.getRepeatCode(localCodes, randomStr);
                    while (exist != null) {
                        randomStr = RandomStringUtils.random(bit, CODE_CHARACTERS);
                        exist = this.getRepeatCode(localCodes, randomStr);
                    }
                    CodeRepository code = new CodeRepository();
                    code.setBitLength(bit);
                    code.setCode(randomStr);
                    code.setStatus(CodeStatusEnum.NOT_USED.getCode());
                    code.setCodeSource(CodeSourceEnum.SELF_CODE.getCode());
                    localCodes.add(code);
                    repos.add(code);
                    step++;
                }
                List<List<CodeRepository>> buckets = Lists.partition(repos, 500);
                buckets.stream().forEach(codes -> codeRepositoryMapper.insertList(codes));
                remainder = this.countByBitAndStatus(bit, CodeStatusEnum.NOT_USED.getCode());
            }
        }
    }

    private CodeRepository getRepeatCode(List<CodeRepository> localCodes, String randomStr) {
        Map<String, CodeRepository> codeRepositoryMap = localCodes.stream().collect(
                Collectors.toMap(CodeRepository :: getCode, e -> e));
        CodeRepository exist = codeRepositoryMap.get(randomStr);
        if (exist != null) {
            return exist;
        }
        exist = this.findOneByCode(randomStr);
        return exist;
    }

    @Override
    public int batchImportThirdPartyCodes(List<CodeRepository> codeRepositories) {
        if (CollectionUtils.isEmpty(codeRepositories)) {
            return 0;
        }
        List<String> repeated = Lists.newArrayListWithExpectedSize(0);
        List<CodeRepository> repos = Lists.newArrayListWithCapacity(codeRepositories.size());
        for (CodeRepository codeRepo : codeRepositories) {
            CodeRepository exist = this.getRepeatCode(repos, codeRepo.getCode());
            if (exist != null) {
                repeated.add(codeRepo.getCode());
                continue;
            }
            repos.add(codeRepo);
        }
        List<List<CodeRepository>> buckets = Lists.partition(repos, 500);
        buckets.stream().forEach(codes -> codeRepositoryMapper.insertList(codes));
        log.info("导入第三方券码成功, success[{}], repeated[{}], duplicate codes{}", repos.size(), repeated.size(), repeated);
        return repos.size();
    }

    @Override
    public List<CodeRepository> findListByStatusAndLimitNumber(CodeRepository codeRepository, int limit) {
        Example example = new Example(CodeRepository.class);
        Example.Criteria criteria = example.createCriteria();
        if (codeRepository.getBitLength() != null) {
            criteria.andEqualTo("bitLength", codeRepository.getBitLength());
        }
        if (codeRepository.getCodeSource() != null) {
            criteria.andEqualTo("codeSource", codeRepository.getCodeSource());
        }
        if (codeRepository.getEventId() != null) {
            criteria.andEqualTo("eventId", codeRepository.getEventId());
        }
        if (codeRepository.getPriceId() != null) {
            criteria.andEqualTo("priceId", codeRepository.getPriceId());
        }
        if (codeRepository.getStatus() != null) {
            criteria.andEqualTo("status", codeRepository.getStatus());
        }
        List<CodeRepository> codes = codeRepositoryMapper.selectByExampleAndRowBounds(example, new RowBounds(0, limit));
        if (CollectionUtils.isEmpty(codes)) {
            return Collections.emptyList();
        }
        return codes;
    }

    @Override
    public CodeRepository findOneByCode(String code) {
        Example example = new Example(CodeRepository.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(code)) {
            criteria.andEqualTo("code", code);
        }
        return codeRepositoryMapper.selectOneByExample(example);
    }

    @Override
    public int countByBitAndStatus(int bit, int status) {
        Example example = new Example(CodeRepository.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("bitLength", bit);
        criteria.andEqualTo("status", status);
        criteria.andEqualTo("codeSource", CodeSourceEnum.SELF_CODE.getCode());
        return codeRepositoryMapper.selectCountByExample(example);
    }

    @Override
    public int countByThirdPartyUsable(CodeRepository codeRepository, int status) {
        Example example = new Example(CodeRepository.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("eventId", codeRepository.getEventId());
        criteria.andEqualTo("priceId", codeRepository.getPriceId());
        criteria.andEqualTo("status", status);
        criteria.andEqualTo("codeSource", CodeSourceEnum.THIRD_PARTY_CODE.getCode());
        return codeRepositoryMapper.selectCountByExample(example);
    }

    @Override
    public void bulkUpdate(List<CodeRepository> codes) {
        codeRepositoryMapper.bulkUpdateByExampleSelective(codes);
    }
}
