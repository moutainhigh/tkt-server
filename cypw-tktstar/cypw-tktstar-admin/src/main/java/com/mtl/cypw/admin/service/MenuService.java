package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.MenuMapper;
import com.mtl.cypw.admin.pojo.Menu;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class MenuService {

    @Resource
    MenuMapper menuMapper;

    public List<Menu> gerMenus(Integer moduleId) {
        if (moduleId == null) {
            return null;
        }
        Example example = new Example(Menu.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("moduleId", moduleId);
        cri.andEqualTo("isEnable", 1);
        example.orderBy("sortOrder").asc();
        return menuMapper.selectByExample(example);
    }
}
