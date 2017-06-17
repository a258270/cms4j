package com.cms4j.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 自定义Logger工具类
 *
 * @author: zmj
 * @create: 2017/5/7
 */
public class LoggerUtil {
    private Logger logger;

    public LoggerUtil(Logger logger) {
        this.logger = logger;
    }

    public LoggerUtil(Class<?> clazz) {
        this.logger = LoggerFactory.getLogger(clazz);
    }

    public static LoggerUtil getLogger(Class<?> clazz) {
        return new LoggerUtil(clazz);
    }

    public void info(String message) {
        this.logger.info(message);
    }

    public void info(String message, Throwable e) {
        this.logger.info("-----异常开始-----");
        this.logger.info(message, e);
        this.logger.info("-----异常结束-----");
    }

    public void debug(String message) {
        this.logger.debug(message);
    }

    public void debug(String message, Throwable e) {
        this.logger.debug("-----异常开始-----");
        this.logger.debug(message, e);
        this.logger.debug("-----异常结束-----");
    }

    public void error(String message) {
        this.logger.error(message);
    }

    public void error(String message, Throwable e) {
        this.logger.error("-----异常开始-----");
        this.logger.error(message, e);
        this.logger.error("-----异常结束-----");
    }

    public void warn(String message) {
        this.logger.warn(message);
    }

    public void warn(String message, Throwable e) {
        this.logger.warn("-----异常开始-----");
        this.logger.warn(message, e);
        this.logger.warn("-----异常结束-----");
    }

    public void trace(String message) {
        this.logger.trace(message);
    }

    public void trace(String message, Throwable e) {
        this.logger.trace("-----异常开始-----");
        this.logger.trace(message, e);
        this.logger.trace("-----异常结束-----");
    }

    public void begin(String message) {
        this.logger.info("-----开始-----");
        this.logger.info(message);
    }

    public void end() {
        this.logger.info("-----结束-----");
    }
}
