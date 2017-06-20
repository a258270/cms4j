package server.basepackage.basepackage.controller;

import com.cms4j.base.controller.ApiBaseController;
import server.basepackage.basepackage.service.TTestService;
import com.cms4j.base.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
* Description: TTestApiController
* Created by zmj on 2017/06/20.
*/
@RestController
@RequestMapping(value = "/ttest/api")
public class TTestApiController extends ApiBaseController {
    public TTestApiController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
        this.menuId = "17";
    }

    @Autowired
    private TTestService ttestService;

    /**
    * 获取测试名称分页数据
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/getttests", method = RequestMethod.POST)
    public PageDto getUsers() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.QUERY_QX))
        return new PageDto();
        logger.begin("获取测试名称分页数据");
        DataMap dataMap = this.getDataMap();
        if(StringUtils.isBlank(dataMap.getString("NAME")))
            dataMap.put("NAME", null);
        Page page = new Page();
        page.setParams(dataMap);
        page.setPageNumber(Integer.valueOf(dataMap.getString("iDisplayStart")));
        page.setPageSize(Integer.valueOf(dataMap.getString("iDisplayLength")));
        List<DataMap> ttests = ttestService.getTTests(page);
        page.setResults(ttests);
        logger.end();

        return PageConverter.toPageDto(page, Integer.valueOf(dataMap.getString("sEcho")));
    }

    /**
    * 批量删除测试名称
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/batchremoves", method = RequestMethod.POST)
    public InvokeResult batchRemoves() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.DEL_QX))
        return this.interceptorJurisdiction();

        logger.begin("批量删除测试名称");
        DataMap dataMap = this.getDataMap();
        ttestService.batchRemoves(dataMap.getString("batchremoves"));
        logger.end();
        return InvokeResult.success();
    }

    /**
    * 新增测试名称
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult add() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.ADD_QX))
        return this.interceptorJurisdiction();

        logger.begin("新增测试名称");
        DataMap dataMap = this.getDataMap();
        ttestService.addTTest(dataMap);
        logger.end();
        return InvokeResult.success();
    }

    /**
    * 编辑测试名称
    * @return
    * @throws Exception
    */
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public InvokeResult edit() throws Exception {
        if(!this.validJurisdiction(JurisdictionUtil.EDIT_QX))
        return this.interceptorJurisdiction();

        logger.begin("编辑测试名称");
        DataMap dataMap = this.getDataMap();

        ttestService.editTTest(dataMap);
        logger.end();
        return InvokeResult.success();
    }
}