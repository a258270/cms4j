package com.cms4j.base.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.util.ClassUtils;

import java.io.*;
import java.util.Locale;

/**
 * description:
 *
 * @author: zmj
 * @create: 2017/6/19
 */
public class Freemarker {

    public static void createFile(DataMap dataMap) throws IOException, TemplateException {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String createPath = path + "/code";
        String templatePath = path + "/createcode";
        File file = new File(createPath + "/" + dataMap.getString("className") + "Controller.java");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        Configuration cfg = new Configuration();  												//通过Freemaker的Configuration读取相应的ftl
        cfg.setEncoding(Locale.CHINA, "utf-8");
        cfg.setDirectoryForTemplateLoading(new File(templatePath));		//设定去哪里读取相应的ftl模板文件
        Template template = cfg.getTemplate("controllerTemplate.ftl");
        template.process(dataMap, out);
    }
}
