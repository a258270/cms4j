package com.cms4j.base.system.createcode.service;

import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.DateUtil;
import com.cms4j.base.util.Freemarker;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    @Value("server.basepackage")
    private String basePackage;

    public void createcode(DataMap dataMap) throws IOException, TemplateException {
        /**
         * 生成页面参数
         */
        String upperPackage = dataMap.getString("UPPERPACKAGE");//上级包名
        String compeletePackage = basePackage + "." + upperPackage;//完整报名
        String jsPath = dataMap.getString("JSPATH");//js文件存放路径
        String ftlPath = dataMap.getString("FTLPATH");//ftl文件存放路径
        String mapperPath = dataMap.getString("MAPPERPATH");//mapper文件存放路径
        String className = dataMap.getString("CLASSNAME");//类名
        String classNameLower = dataMap.getString("CLASSNAME").toLowerCase();//全小写类名
        String classNameUpper = dataMap.getString("CLASSNAME").toUpperCase();//全大写类名
        String menuName = dataMap.getString("MENUNAME");//菜单名称
        String showName = dataMap.getString("SHOWNAME");//显示名称
        String fatherMenuId = dataMap.getString("FATHERMENU");//上级菜单
        String fatherMenuName = dataMap.getString("FATHERMENUNAME");//上级菜单名
        String isTableFront = dataMap.getString("TABLEFRONT");//表前缀
        String curDate = DateUtil.date2Str(new Date(), "yyyy/MM/dd");//当前时间
        String menuId = dataMap.getString("menuId");//菜单id
        List<DataMap> datas = new ArrayList<DataMap>();
        Integer maxIsFrontIndex = -1;
        Integer maxIsNotFrontIndex = -1;
        Integer maxIsDicIndex = -1;
        Integer maxIsNotDicIndex = -1;

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
            if(params[i + 4] == "是")
                maxIsFrontIndex = j;
            else
                maxIsNotFrontIndex = j;

            if(params[i + 9] == "是")
                maxIsDicIndex = j;
            else
                maxIsNotDicIndex = j;
        }

        DataMap templateParam = new DataMap();
        templateParam.put("basePackage", basePackage);
        templateParam.put("upperPackage", upperPackage);
        templateParam.put("compeletePackage", compeletePackage);
        templateParam.put("jsPath", jsPath);
        templateParam.put("ftlPath", ftlPath);
        templateParam.put("mapperPath", mapperPath);
        templateParam.put("className", className);
        templateParam.put("classNameLower", classNameLower);
        templateParam.put("classNameUpper", classNameUpper);
        templateParam.put("menuName", menuName);
        templateParam.put("showName", showName);
        templateParam.put("fatherMenuId", fatherMenuId);
        templateParam.put("fatherMenuName", fatherMenuName);
        templateParam.put("isTableFront", isTableFront);
        templateParam.put("curDate", curDate);
        templateParam.put("menuId", menuId);
        templateParam.put("maxIsFrontIndex", maxIsFrontIndex);
        templateParam.put("maxIsNotFrontIndex", maxIsNotFrontIndex);
        templateParam.put("maxIsDicIndex", maxIsDicIndex);
        templateParam.put("maxIsNotDicIndex", maxIsNotDicIndex);
        templateParam.put("datas", datas);

        Freemarker.createFile(templateParam);
    }
}
