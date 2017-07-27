package com.cms4j.base.system.user.online.controller;

import com.cms4j.base.controller.ApiBaseController;
import com.cms4j.base.system.user.online.service.SessionService;
import com.cms4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 * Created by zmj on 2017/6/16.
 */
@RestController
@RequestMapping(value = "/admin/user/online/api")
public class OnlineApiController extends ApiBaseController {
    public OnlineApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "8";
    }

    @Autowired
    private SessionService sessionService;

    /**
     * 获取在线用户分页信息
     * @return
     */
    @RequestMapping(value = "/getonlineusers", method = RequestMethod.POST)
    public PageDto getOnlineUsers() {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
            return new PageDto();
        logger.begin("获取在线用户分页信息");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("USERNAME")))
            dataMap.put("USERNAME", null);
        if(StringUtils.isBlank(dataMap.getString("NAME")))
            dataMap.put("NAME", null);
        if(StringUtils.isBlank(dataMap.getString("ROLE_ID")))
            dataMap.put("ROLE_ID", null);
        if(StringUtils.isBlank(dataMap.getString("STATUS")))
            dataMap.put("STATUS", null);

        Page page = new Page();
        page.setParams(dataMap);
        page.setPageNumber(Integer.valueOf(dataMap.getString("iDisplayStart")) / Integer.valueOf(dataMap.getString("iDisplayLength")));
        page.setPageSize(Integer.valueOf(dataMap.getString("iDisplayLength")));

        List<DataMap> users = sessionService.getOnlineUsers(page);
        page.setResults(users);
        logger.end();
        return PageConverter.toPageDto(page, Integer.valueOf(dataMap.getString("sEcho")));
    }

    /**
     * 踢出用户
     * @return
     */
    @RequestMapping(value = "/kick", method = RequestMethod.POST)
    public InvokeResult kick() {
        if (!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
            return this.interceptorJurisdiction();

        logger.begin("踢出用户");
        DataMap dataMap = this.getDataMap();
        sessionService.kickUser(dataMap.getString("USER_ID"));
        logger.end();
        return InvokeResult.success();
    }
}
