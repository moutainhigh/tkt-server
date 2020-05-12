package com.mtl.cypw.admin.service;

import com.mtl.cypw.admin.mapper.MenuSubmenuMapper;
import com.mtl.cypw.admin.pojo.MenuSubmenu;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author tang.
 * @date 2020/3/17.
 */
@Service
public class MenuSubmenuService {

    @Resource
    MenuSubmenuMapper menuSubmenuMapper;

    public List<MenuSubmenu> gerSubMenus(Integer parentId) {
        if (parentId == null) {
            return null;
        }
        Example example = new Example(MenuSubmenu.class);
        Example.Criteria cri = example.createCriteria();
        cri.andEqualTo("parentId", parentId);
        cri.andEqualTo("isEnable", 1);
        example.orderBy("sortOrder").asc();
        return menuSubmenuMapper.selectByExample(example);
    }

    public List<MenuSubmenu> gerSubMenus(List<Integer> parentIdList) {
        if (CollectionUtils.isEmpty(parentIdList)) {
            return null;
        }
        Example example = new Example(MenuSubmenu.class);
        Example.Criteria cri = example.createCriteria();
        cri.andIn("parentId", parentIdList);
        cri.andEqualTo("isEnable", 1);
        example.orderBy("sortOrder").asc();
        return menuSubmenuMapper.selectByExample(example);
    }
}
