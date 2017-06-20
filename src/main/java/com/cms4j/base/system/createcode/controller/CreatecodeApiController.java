package com.cms4j.base.system.createcode.controller;

import com.cms4j.base.controller.ApiBaseController;
import com.cms4j.base.system.createcode.service.CreatecodeService;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.InvokeResult;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description:
 * @author: zmj
 * @create: 2017/6/17
 */
@RestController
@RequestMapping(value = "/createcode/api")
public class CreatecodeApiController extends ApiBaseController {
    public CreatecodeApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "9";
    }

    @Autowired
    private CreatecodeService createcodeService;

    @RequestMapping(value = "/create")
    public InvokeResult create() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
            return this.interceptorJurisdiction();

        logger.begin("代码生成");
        DataMap dataMap = this.getDataMap();
        createcodeService.createcode(dataMap);
        logger.end();
        return InvokeResult.success();
    }
}
