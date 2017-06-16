package com.crm4j.base.plugin;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * description: 加载自定义配置
 *
 * @author: zmj
 * @create: 2017/5/30
 */
@Component
@ConfigurationProperties(prefix="base")
@PropertySource(value = "classpath:setting/base.properties", encoding = "utf-8")
public class BaseSetting {
    private String title;
    private Integer pageNumber;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }
}
