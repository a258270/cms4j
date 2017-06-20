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

    /**
     * 代码生成
     * @param dataMap 数据信息
     * @throws IOException
     * @throws TemplateException
     */
    public static void createCode(DataMap dataMap) throws Exception {
        String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String codePath = path + "code";
        String createPath = path + "code/code";
        String templatePath = path + "createcode";
        String basePackage = createPath + "/" + dataMap.getString("basePackage").replace(".", "/");
        String javaFilePath = basePackage + "/" + dataMap.getString("upperPackage");
        String jsFilePath = createPath + "/static/" + dataMap.getString("jsPath") + "/" + dataMap.getString("upperPackage");
        String ftlFilePath = createPath + "/templates/" + dataMap.getString("ftlPath") + "/" + dataMap.getString("upperPackage");
        String mapperFilePath = createPath + "/mybatis/mapper/" + dataMap.getString("mapperPath") + "/" + dataMap.getString("upperPackage");

        FileUtil.delAllFile(codePath);//清除之前生成的文件

        Freemarker.createControllerFile(dataMap, javaFilePath, templatePath);
        Freemarker.createServiceFile(dataMap, javaFilePath, templatePath);
        Freemarker.createMapperFile(dataMap, mapperFilePath, templatePath);
        Freemarker.createSqlFile(dataMap, createPath, templatePath);
        Freemarker.createFtlFile(dataMap, ftlFilePath, templatePath);
        Freemarker.createJsFile(dataMap, jsFilePath, templatePath);

        FileUtil.zip(createPath, codePath + "/code.zip");
    }

    /**
     * 创建ftl文件
     * @param dataMap 数据信息
     * @param ftlFilePath 文件全路径（不包含文件名）
     * @param templatePath 模板全路径（不包含文件名）
     * @throws IOException
     * @throws TemplateException
     */
    public static void createFtlFile(DataMap dataMap, String ftlFilePath, String templatePath) throws IOException, TemplateException {
        String ftlPath = ftlFilePath;

        Freemarker.createFile(dataMap, ftlPath, "index.ftl", templatePath, "indexTemplate.ftl");
        Freemarker.createFile(dataMap, ftlPath, "add.ftl", templatePath, "addTemplate.ftl");
        Freemarker.createFile(dataMap, ftlPath, "edit.ftl", templatePath, "editTemplate.ftl");
    }

    /**
     * 创建js文件
     * @param dataMap 数据信息
     * @param jsFilePath 文件全路径（不包含文件名）
     * @param templatePath 模板全路径（不包含文件名）
     * @throws IOException
     * @throws TemplateException
     */
    public static void createJsFile(DataMap dataMap, String jsFilePath, String templatePath) throws IOException, TemplateException {
        String jsPath = jsFilePath;

        Freemarker.createFile(dataMap, jsPath, "index.js", templatePath, "indexjsTemplate.ftl");
        Freemarker.createFile(dataMap, jsPath, "add.js", templatePath, "addjsTemplate.ftl");
        Freemarker.createFile(dataMap, jsPath, "edit.js", templatePath, "editjsTemplate.ftl");
    }

    /**
     * 创建controller文件
     * @param dataMap 数据信息
     * @param javaFilePath 文件全路径（不包含文件名）
     * @param templatePath 模板全路径（不包含文件名）
     * @throws IOException
     * @throws TemplateException
     */
    public static void createControllerFile(DataMap dataMap, String javaFilePath, String templatePath) throws IOException, TemplateException {
        String controllerPath = javaFilePath + "/controller";

        Freemarker.createFile(dataMap, controllerPath, dataMap.getString("className") + "Controller.java", templatePath, "controllerTemplate.ftl");
        Freemarker.createFile(dataMap, controllerPath, dataMap.getString("className") + "ApiController.java", templatePath, "controllerApiTemplate.ftl");
    }

    /**
     * 创建service文件
     * @param dataMap 数据信息
     * @param javaFilePath 文件全路径（不包含文件名）
     * @param templatePath 模板全路径（不包含文件名）
     * @throws IOException
     * @throws TemplateException
     */
    public static void createServiceFile(DataMap dataMap, String javaFilePath, String templatePath) throws IOException, TemplateException {
        String servicePath = javaFilePath + "/service";

        Freemarker.createFile(dataMap, servicePath, dataMap.getString("className") + "Service.java", templatePath, "serviceTemplate.ftl");
    }

    /**
     * 创建mapper文件
     * @param dataMap 数据信息
     * @param mapperFilePath 文件全路径（不包含文件名）
     * @param templatePath 模板全路径（不包含文件名）
     * @throws IOException
     * @throws TemplateException
     */
    public static void createMapperFile(DataMap dataMap, String mapperFilePath, String templatePath) throws IOException, TemplateException {
        String mapperPath = mapperFilePath;

        Freemarker.createFile(dataMap, mapperPath, dataMap.getString("className") + "Mapper.xml", templatePath, "mapperTemplate.ftl");
    }

    /**
     * 创建sql文件
     * @param dataMap 数据信息
     * @param sqlFilePath 文件全路径（不包含文件名）
     * @param templatePath 模板全路径（不包含文件名）
     * @throws IOException
     * @throws TemplateException
     */
    public static void createSqlFile(DataMap dataMap, String sqlFilePath, String templatePath) throws IOException, TemplateException {
        String sqlPath = sqlFilePath;

        Freemarker.createFile(dataMap, sqlPath, dataMap.getString("className") + "Sql.sql", templatePath, "sqlTemplate.ftl");
    }

    /**
     * 创建文件
     * @param dataMap 数据信息
     * @param filePath 创建的文件的全路径（不包含文件名）
     * @param fileName 创建的文件名
     * @param templatePath 模板文件的全路径（不包含文件名）
     * @param templateName 模板文件名
     * @throws IOException
     * @throws TemplateException
     */
    public static void createFile(DataMap dataMap, String filePath, String fileName, String templatePath, String templateName) throws IOException, TemplateException {
        if(!filePath.substring(filePath.length() - 1).equals("/"))
            filePath += "/";
        if(!templatePath.substring(templatePath.length() - 1).equals("/"))
            templatePath += "/";
        File file = new File(filePath + fileName);
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }

        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
        Configuration cfg = new Configuration();
        cfg.setEncoding(Locale.CHINA, "utf-8");
        cfg.setDirectoryForTemplateLoading(new File(templatePath));
        Template template = cfg.getTemplate(templateName);
        template.process(dataMap, writer);
    }
}
