package com.crm4j.base.util;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/5/31
 */
public class DataMap extends HashMap implements Serializable {
    private HttpServletRequest request;

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public DataMap() {
    }

    public DataMap(HttpServletRequest request) {
        this.request = request;

        Map properties = request.getParameterMap();
        if(properties != null) {
            Iterator it = properties.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                String key = (String) entry.getKey();
                String value = "";
                Object valueObj = entry.getValue();
                if(valueObj == null)
                    value= "";
                else if(valueObj instanceof String[]){
                    String[] values = (String[])valueObj;
                    for(int i=0;i<values.length;i++){
                        value += values[i] + ",";
                    }
                    value = value.substring(0, value.length()-1);
                }
                else{
                    value = valueObj.toString();
                }

                this.put(key, value);
            }
        }
    }

    public String getString(Object key) {
        return this.get(key) == null ? null : String.valueOf(this.get(key));
    }
}
