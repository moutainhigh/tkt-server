package com.mtl.cypw.admin.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * @author tang.
 * @date 2020年03月17日 上午11:17:41
 */
@Data
@Table(name = "tblmenusubmenu")
public class MenuSubmenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Integer menuId;

    @Column(name = "menu_title")
    private String menuTitle;

    @Column(name = "menu_url")
    private String menuUrl;

    @Column(name = "menu_icon")
    private String menuIcon;

    @Column(name = "menu_permission_key")
    private String menuPermissionKey;

    @Column(name = "sort_order")
    private Integer sortOrder;

    @Column(name = "parent_id")
    private Integer parentId;

    @Column(name = "is_enable")
    private Integer isEnable;

    @Column(name = "module_id")
    private Integer moduleId;
}