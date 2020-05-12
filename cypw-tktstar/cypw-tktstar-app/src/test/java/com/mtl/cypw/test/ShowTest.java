package com.mtl.cypw.test;

import com.juqitech.response.paging.Pagination;
import com.mtl.cypw.domain.show.query.ProgramQuery;
import com.mtl.cypw.member.service.MemberAddressService;
import com.mtl.cypw.show.pojo.Program;
import com.mtl.cypw.show.service.ProgramService;
import org.junit.Test;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author tang.
 * @date 2019/11/28.
 */
public class ShowTest extends BaseTest {

    @Resource
    MemberAddressService memberAddressService;

    @Resource
    ProgramService programService;

    @Test
    public void test() {
//        memberAddressService.updateDefaultAddress(10000001,2);
    }

    @Test
    public void test1() {
        ProgramQuery query = new ProgramQuery();
        query.setLikeName("上海");
        query.setProgramTypeList(Arrays.asList(80, 83));
        programService.searchProgramList(query, new Pagination());
    }

    @Test
    public void testProgramDetail() {
        Program program = programService.getProgramById(21);
        Assert.isTrue(program != null, "项目为空");
    }
}
