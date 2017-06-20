package com.cms4j.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 引用的Util，留存描述信息
 * java删除所有文件和文件夹
 * 创建人：FH 创建时间：2015年1月12日
 * @version
 */
public class FileUtil {

    /**
     * @param folderPath 文件路径 (只删除此路径的最末路径下所有文件和文件夹)
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); 	// 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); 		// 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除指定文件夹下所有文件
     * @param path 文件夹完整绝对路径
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);	// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);	// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @param inputFileName 你要压缩的文件夹(整个完整路径)
     * @param zipFileName 压缩后的文件(整个完整路径)
     */
    public static void zip(String inputFileName, String zipFileName) throws Exception {
        zip(zipFileName, new File(inputFileName));
    }

    private static void zip(String zipFileName, File inputFile) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(
                zipFileName));
        zip(out, inputFile, "");
        out.flush();
        out.close();
    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles();
            out.putNextEntry(new ZipEntry(base + "/"));
            base = base.length() == 0 ? "" : base + "/";
            for (int i = 0; i < fl.length; i++) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            int b;
            //System.out.println(base);
            while ((b = in.read()) != -1) {
                out.write(b);
            }
            in.close();
        }
    }
}
