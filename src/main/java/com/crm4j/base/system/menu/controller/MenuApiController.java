package com.crm4j.base.system.menu.controller;

import com.crm4j.base.controller.ApiBaseController;
import com.crm4j.base.system.menu.service.MenuService;
import com.crm4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/8
 */
@RestController
@RequestMapping(value = "/menu/api")
public class MenuApiController extends ApiBaseController {
    private LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

    public MenuApiController() {
        this.menuId = "6";
    }

    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单分页数据
     * @param parentid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getmenus/{parentid}", "/getmenus"}, method = RequestMethod.POST)
    public PageDto getMenus(@PathVariable(required = false) String parentid) throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
            return new PageDto();

        logger.begin("获取菜单分页数据");
        DataMap dataMap = this.getDataMap();
        dataMap.put("PARENT_ID", parentid);
        Page page = new Page();
        page.setParams(dataMap);
        page.setPageNumber(Integer.valueOf(dataMap.getString("iDisplayStart")));
        page.setPageSize(Integer.valueOf(dataMap.getString("iDisplayLength")));
        List<DataMap> list = menuService.getMenus(page);

        page.setResults(list);
        logger.end();
        return PageConverter.toPageDto(page, Integer.valueOf(dataMap.getString("sEcho")));
    }

    /**
     * 新增菜单
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
            return this.interceptorJurisdiction();

        logger.begin("新增菜单");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("PARENT_ID"))) {
            dataMap.put("PARENT_ID", null);
            dataMap.put("URL", "#");
        }

        if(StringUtils.isBlank(dataMap.getString("ICON"))) {
            dataMap.put("ICON", "");
        }

        Integer maxId = 1;
        DataMap maxIdMenu = menuService.getMaxIdMenu();
        if(maxIdMenu != null)
            maxId = (Integer) maxIdMenu.get("MENU_ID");

        maxId++;
        dataMap.put("MENU_ID", maxId);
        menuService.addMenu(dataMap);
        logger.end();

        return InvokeResult.success();
    }

    /**
     * 批量删除菜单
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchremoves", method = RequestMethod.POST)
    public InvokeResult batchRemoves() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
            return this.interceptorJurisdiction();

        logger.begin("批量删除菜单");
        DataMap dataMap = this.getDataMap();
        menuService.batchRemoveMenus(dataMap.getString("batchremoves"));
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 编辑菜单
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public InvokeResult edit() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
            return this.interceptorJurisdiction();

        logger.begin("编辑菜单");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("ICON")))
            dataMap.put("ICON", "");
        if(StringUtils.isBlank(dataMap.getString("URL")))
            dataMap.put("URL", "#");
        menuService.editMenu(dataMap);
        logger.end();
        return InvokeResult.success();
    }
}
