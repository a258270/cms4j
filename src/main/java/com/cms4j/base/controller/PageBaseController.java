package com.cms4j.base.controller;

import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.JurisdictionUtil;
import com.cms4j.base.util.LoggerUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/31
 */
@ControllerAdvice
public class PageBaseController extends BaseController {

    public PageBaseController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
    }

    @ExceptionHandler({Exception.class})
    protected ResponseEntity<Object> handleException(Exception e) {

        logger.error("捕捉异常", e);
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected void setJurisdictionInfo(ModelAndView modelAndView, String type) {
        DataMap dataMap = new DataMap();
        dataMap.put(JurisdictionUtil.ADD_QX, String.valueOf(JurisdictionUtil.hasJurisdiction(JurisdictionUtil.ADD_QX, menuId)));
        dataMap.put(JurisdictionUtil.DEL_QX, String.valueOf(JurisdictionUtil.hasJurisdiction(JurisdictionUtil.DEL_QX, menuId)));
        dataMap.put(JurisdictionUtil.EDIT_QX, String.valueOf(JurisdictionUtil.hasJurisdiction(JurisdictionUtil.EDIT_QX, menuId)));
        dataMap.put(JurisdictionUtil.QUERY_QX, String.valueOf(JurisdictionUtil.hasJurisdiction(JurisdictionUtil.QUERY_QX, menuId)));

        if(dataMap.get(type) == null)
            dataMap.put(type, "false");

        if(dataMap.getString(type).equals("false")){
            modelAndView.setViewName("/403");
        }
        modelAndView.addObject(JurisdictionUtil.QX, dataMap);
    }

    protected ModelAndView getModelAndView() {
        return new ModelAndView();
    }
}
