package com.cms4j.base.system.createcode.service;

import com.cms4j.base.util.DataMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/18
 */
@Service
public class CreatecodeService {

    public static Integer numberOfProperty = 11;//生成器的新增属性页面中的属性值

    public void createcode(DataMap dataMap) {
        /**
         * 生成页面参数
         */
        String completePackName = dataMap.getString("COMPLETEPACK");//完整包名
        String className = dataMap.getString("CLASSNAME");//类名
        String classNameLower = dataMap.getString("CLASSNAME").toLowerCase();//全小写类名
        String classNameUpper = dataMap.getString("CLASSNAME").toUpperCase();//全大写类名
        String showName = dataMap.getString("SHOWNAME");//显示名称
        String fatherMenuId = dataMap.getString("FATHERMENU");//上级菜单
        String isTalbeFront = dataMap.getString("TABLEFRONT");//表前缀
        List<DataMap> datas = new ArrayList<DataMap>();

        /**
         * 属性参数
         */
        String[] params = dataMap.getString("PARAMS").split(",");
        for(int i = 0, len = params.length, j = 0; i < len; j++, i = j * numberOfProperty) {
            DataMap data = new DataMap();
            data.put("propertyName", params[i]);//属性名称
            data.put("propertyNameUpper", params[i].toUpperCase());//属性名称（大写）
            data.put("remark", params[i + 1]);//备注
            data.put("dataType", params[i + 2]);//数据类型
            data.put("isRequired", params[i + 3]);//是否必填
            data.put("isFront", params[i + 4]);//是否前台录入
            data.put("default", params[i + 5]);//默认值
            data.put("isList", params[i + 6]);//是否列表可见
            data.put("isSearch", params[i + 7]);//是否为搜索项
            data.put("searchCondition", params[i + 8]);//检索条件
            data.put("isDic", params[i + 9]);//是否为数据字典
            data.put("dicCode", params[i + 10]);//字典内码
            datas.add(data);
        }
    }
}
