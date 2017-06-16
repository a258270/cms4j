package com.crm4j.base.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Description:
 * Created by zmj on 2017/6/14.
 */
public class ZTree {
    private Integer id;
    private Integer pId;
    private String name;
    private boolean checked;
    private boolean open;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public static ZTree getzTree(DataMap menu, String jurisdictionStr) throws Exception {
        if(jurisdictionStr == null)
            throw new Exception("jurisdictionStr不能为空");
        ZTree zTree = new ZTree();
        zTree.setId(Integer.valueOf(menu.getString("MENU_ID")));
        if(StringUtils.isBlank(menu.getString("PARENT_ID")))
            zTree.setpId(0);
        else
            zTree.setpId(Integer.valueOf(menu.getString("PARENT_ID")));

        if(JurisdictionUtil.queryJurisdiction(jurisdictionStr, menu.getString("MENU_ID"))){
            zTree.setChecked(true);
        }
        else
            zTree.setChecked(false);

        zTree.setOpen(true);
        zTree.setName(menu.getString("NAME"));
        return zTree;
    }
}
