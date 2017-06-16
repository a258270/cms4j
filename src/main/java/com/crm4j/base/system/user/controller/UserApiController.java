package com.crm4j.base.system.user.controller;

import com.crm4j.base.controller.ApiBaseController;
import com.crm4j.base.system.user.service.UserService;
import com.crm4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/15.
 */
@RestController
@RequestMapping(value = "/user/api")
public class UserApiController extends ApiBaseController {
    private LoggerUtil logger = LoggerUtil.getLogger(this.getClass());

    public UserApiController() {
        this.menuId = "4";
    }

    @Autowired
    private UserService userService;

    /**
     * 获取用户分页数据
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getusers", method = RequestMethod.POST)
    public PageDto getUsers() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
            return new PageDto();
        logger.begin("获取用户分页数据");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("STATUS")))
            dataMap.put("STATUS", null);
        if(StringUtils.isBlank(dataMap.getString("ROLE_ID")))
            dataMap.put("ROLE_ID", null);
        Page page = new Page();
        page.setParams(dataMap);
        page.setPageNumber(Integer.valueOf(dataMap.getString("iDisplayStart")));
        page.setPageSize(Integer.valueOf(dataMap.getString("iDisplayLength")));
        List<DataMap> users = userService.getUsers(page);
        page.setResults(users);
        logger.end();

        return PageConverter.toPageDto(page, Integer.valueOf(dataMap.getString("sEcho")));
    }

    /**
     * 批量冻结用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/batchremoves", method = RequestMethod.POST)
    public InvokeResult batchRemoves() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
            return this.interceptorJurisdiction();

        logger.begin("批量冻结用户");
        DataMap dataMap = this.getDataMap();
        userService.batchRemoves(dataMap.getString("batchremoves"));
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 解冻用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/unremove", method = RequestMethod.POST)
    public InvokeResult unRemove() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
            return this.interceptorJurisdiction();

        logger.begin("解冻用户");
        DataMap dataMap = this.getDataMap();
        userService.unRemove(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 新增用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
            return this.interceptorJurisdiction();

        logger.begin("新增用户");
        DataMap dataMap = this.getDataMap();
        DataMap user = userService.getUserByUsername(dataMap);
        if(user != null) {
            return InvokeResult.failure("该用户名已经存在");
        }
        if(!dataMap.getString("PASSWORD").equals(dataMap.getString("REPASSWORD"))){
            return InvokeResult.failure("两次密码输入不一致");
        }
        dataMap.put("USER_ID", ShortUUID.randomUUID());
        dataMap.put("PASSWORD", MD5Util.getMD5(dataMap.getString("PASSWORD")));
        userService.addUser(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
     * 编辑用户
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public InvokeResult edit() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
            return this.interceptorJurisdiction();

        logger.begin("编辑用户");
        DataMap dataMap = this.getDataMap();
        if(!StringUtils.isBlank(dataMap.getString("PASSWORD")) || !StringUtils.isBlank(dataMap.getString("REPASSWORD"))){
            if(!dataMap.getString("PASSWORD").equals(dataMap.getString("REPASSWORD"))) {
                return InvokeResult.failure("两次密码输入不一致");
            }

            dataMap.put("PASSWORD", MD5Util.getMD5(dataMap.getString("PASSWORD")));
        }
        else {
            dataMap.put("PASSWORD", null);
        }
        if(StringUtils.isBlank(dataMap.getString("PHONE")))
            dataMap.put("PHONE", null);

        userService.editUser(dataMap);
        logger.end();
        return InvokeResult.success();
    }
}