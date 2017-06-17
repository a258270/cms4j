package com.crm4j.base.system.createcode;

import com.crm4j.base.controller.ApiBaseController;
import com.crm4j.base.util.LoggerUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/6/17.
 */
@RestController
@RequestMapping(value = "/createcode/api")
public class CreatecodeApiController extends ApiBaseController {
    public CreatecodeApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "9";
    }
}
