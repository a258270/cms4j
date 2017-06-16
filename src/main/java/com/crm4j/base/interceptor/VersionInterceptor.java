package com.crm4j.base.interceptor;

import com.crm4j.base.plugin.BaseSetting;
import com.crm4j.base.util.Const;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.AsyncWebRequestInterceptor;
import org.springframework.web.context.request.WebRequest;

/**
 * description: 添加contextPath等相关信息
 *
 * @author: zmj
 * @create: 2017/5/30
 */
public class VersionInterceptor implements AsyncWebRequestInterceptor {
    private BaseSetting baseSetting;
    private String active;
    private String version;

    public VersionInterceptor(BaseSetting baseSetting, String active, String version) {
        this.baseSetting = baseSetting;
        this.active = active;
        this.version = version;
    }

    @Override
    public void afterConcurrentHandlingStarted(WebRequest webRequest) {

    }

    @Override
    public void preHandle(WebRequest webRequest) throws Exception {

    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
        if (modelMap != null) {
            if(!StringUtils.isBlank(active) && Const.PROFILE_PRO.equals(active)){
                if(StringUtils.isBlank(version))
                    version = String.valueOf(System.currentTimeMillis());
            }
            else{
                version = String.valueOf(System.currentTimeMillis());
            }
            modelMap.put("v", version);
            modelMap.put("ctxPath","");
            String lang = (String) webRequest.getAttribute("_lang", 1);
            if (StringUtils.isNotBlank(lang))
                modelMap.put("lang", lang);
            else
                modelMap.put("lang", "zh_cn");

            modelMap.put("baseSetting", baseSetting);
        }
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {

    }
}
