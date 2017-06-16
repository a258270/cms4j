package com.crm4j.base.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Iterator;
import java.util.Map;

/**
 * description: 统一的返回类型
 *
 * @author: zmj
 * @create: 2017/5/31
 */
public class InvokeResult extends JSONObject {

    public static InvokeResult success() {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.put("errorCode", "0");
        invokeResult.put("hasErrors", Boolean.valueOf(false));
        invokeResult.put("errorMessage", "");

        return invokeResult;
    }

    public static InvokeResult success(Object data) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.put("data", data);
        invokeResult.put("errorCode", "0");
        invokeResult.put("hasErrors", Boolean.valueOf(false));
        invokeResult.put("errorMessage", "");

        return invokeResult;
    }

    public static InvokeResult success(String key, Object value) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.put(key, value);
        invokeResult.put("errorCode", "0");
        invokeResult.put("hasErrors", Boolean.valueOf(false));
        invokeResult.put("errorMessage", "");

        return invokeResult;
    }

    public static InvokeResult success(Map map) {
        InvokeResult invokeResult = new InvokeResult();
        Iterator<Entry<String, Integer>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Integer> entry = it.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            invokeResult.put(key, value);
        }

        invokeResult.put("errorCode", "0");
        invokeResult.put("hasErrors", Boolean.valueOf(false));
        invokeResult.put("errorMessage", "");

        return invokeResult;
    }

    public static InvokeResult failure(String message) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.put("errorCode", "500");
        invokeResult.put("hasErrors", Boolean.valueOf(true));
        invokeResult.put("errorMessage", message);
        return invokeResult;
    }

    public static InvokeResult failure(String code, String message) {
        InvokeResult invokeResult = new InvokeResult();
        invokeResult.put("errorCode", code);
        invokeResult.put("hasErrors", Boolean.valueOf(true));
        invokeResult.put("errorMessage", message);
        return invokeResult;
    }

    public boolean isSuccess() {
        return this.containsKey("errorCode") && "0".equalsIgnoreCase(this.getString("errorCode"));
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
