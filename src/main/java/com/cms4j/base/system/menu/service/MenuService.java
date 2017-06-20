package com.cms4j.base.system.menu.service;

import com.cms4j.base.dao.DaoSupport;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * description: 菜单Service
 *
 * @author: zmj
 * @create: 2017/6/8
 */
@Service
public class MenuService {
    @Autowired
    private DaoSupport daoSupport;

    /**
     * 获取所有menu，按照id升序
     * @return
     * @throws Exception
     */
    public List<DataMap> getAllMenusSortId() throws Exception {
        return (List<DataMap>) daoSupport.findForList("MenuMapper.getAllMenusSortId");
    }
    /**
     * 获取所有menu
     * @return
     * @throws Exception
     */
    public List<DataMap> getAllMenus() throws Exception {
        return (List<DataMap>) daoSupport.findForList("MenuMapper.getAllMenus");
    }

    /**
     * 获取所有父节点菜单
     * @return
     * @throws Exception
     */
    public List<DataMap> getAllFatherMenus() throws Exception {
        return (List<DataMap>) daoSupport.findForList("MenuMapper.getAllFatherMenus");
    }

    /**
     * 获取子节点菜单
     * @param dataMap 父节点id
     * @return
     * @throws Exception
     */
    public List<DataMap> getSonMenus(DataMap dataMap) throws Exception {
        return (List<DataMap>) daoSupport.findForList("MenuMapper.getSonMenus", dataMap);
    }

    /**
     * 分页查询Menu
     * @param page
     * @return
     * @throws Exception
     */
    public List<DataMap> getMenus(Page page) throws Exception {
        return (List<DataMap>) daoSupport.findForList("MenuMapper.getMenus", page);
    }

    /**
     * 新增menu
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMenu(DataMap dataMap) throws Exception {
        Integer maxId = this.getMaxId();
        if(maxId == null)
            maxId = 0;//若不存在，menuId 从1开始
        maxId++;
        dataMap.put("MENU_ID", maxId);
        daoSupport.save("MenuMapper.addMenu", dataMap);
    }

    /**
     * 获取主键为最大值的menu
     * @return
     * @throws Exception
     */
    public DataMap getMaxIdMenu() throws Exception {
        return (DataMap) daoSupport.findForObject("MenuMapper.getMaxIdMenu");
    }

    /**
     * 批量删除Menus
     * @param objs
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void batchRemoveMenus(String objs) throws Exception {
        String[] objArr = objs.split(",");
        List<String> objList = Arrays.asList(objArr);

        daoSupport.batchDelete("MenuMapper.batchRemoveMenus", objList);

        //删除子节点
        for (String obj : objList) {
            DataMap param = new DataMap();
            param.put("MENU_ID", obj);
            List<DataMap> sons = getMenusByFatherId(param);
            if(sons != null) {
                String sonIds = "";
                for(DataMap son : sons) {
                    sonIds += son.getString("MENU_ID") + ",";
                }

                if(StringUtils.isBlank(sonIds)){
                    continue;
                }

                sonIds = sonIds.substring(0, sonIds.length() - 1);
                //无限级menu时，递归删除
                batchRemoveMenus(sonIds);
            }
        }
    }

    /**
     * 根据PARENT_ID获取Menus
     * @param dataMap
     * @return
     * @throws Exception
     */
    public List<DataMap> getMenusByFatherId(DataMap dataMap) throws Exception {
        return (List<DataMap>) daoSupport.findForList("MenuMapper.getMenusByFatherId", dataMap);
    }

    /**
     * 根据MENU_ID获取Menu
     * @param dataMap
     * @return
     * @throws Exception
     */
    public DataMap getMenuById(DataMap dataMap) throws Exception {
        return (DataMap) daoSupport.findForObject("MenuMapper.getMenuById", dataMap);
    }

    /**
     * 编辑Menu
     * @param dataMap
     * @throws Exception
     */
    @Transactional(rollbackFor = Exception.class)
    public void editMenu(DataMap dataMap) throws Exception {
        daoSupport.update("MenuMapper.editMenu", dataMap);
    }

    /**
     * 获取MENU_ID最大值
     * @return
     * @throws Exception
     */
    public Integer getMaxId() throws Exception {
        return (Integer) daoSupport.findForObject("MenuMapper.getMaxId");
    }

}
