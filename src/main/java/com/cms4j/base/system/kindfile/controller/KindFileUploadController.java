package com.cms4j.base.system.kindfile.controller;

import com.cms4j.base.controller.ApiBaseController;
import com.cms4j.base.plugin.BaseSetting;
import com.cms4j.base.util.DataMap;
import com.cms4j.base.util.LoggerUtil;
import com.cms4j.base.util.SessionUtil;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class KindFileUploadController extends ApiBaseController {

    public KindFileUploadController() {
        this.logger = LoggerUtil.getLogger(this.getClass());
    }

    @Autowired
    private BaseSetting baseSetting;

    @RequestMapping(value = "/admin/kindfile/upload")
    public Map<String, Object> upload(HttpServletRequest request, HttpServletResponse response) {
        //文件保存目录路径
        String savePath = baseSetting.getKindfile_path() + "/";
        //文件保存目录URL
        String saveUrl = request.getContextPath() + "/admin/kindfile/down/";
        if(!ServletFileUpload.isMultipartContent(request)){
            return getError("请选择文件。");
        }
        //检查目录
        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            return getError("上传目录不存在。");
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            return getError("上传目录没有写权限。");
        }

        String dirName = request.getParameter("dir");
        if (dirName == null) {
            dirName = "image";
        }

        //定义允许上传的文件扩展名
        Map<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("flash", "swf,flv");
        extMap.put("media", "swf,flv,mp3,wav,wma,wmv,mid,avi,mpg,asf,rm,rmvb");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,xml,txt,zip,rar,gz,bz2");

        if(!extMap.containsKey(dirName)){
            return getError("目录名不正确。");
        }
        DataMap curUser = SessionUtil.getCurAdminUser();
        //创建文件夹
        savePath += curUser.getString("USER_ID") + "/" + dirName + "/";
        saveUrl += curUser.getString("USER_ID") + "/" + dirName + "/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        //最大文件大小
        long maxSize = 1000000;

        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("UTF-8");
        List<?> items = null;
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
            MultipartFile item = req.getFile(iterator.next());
            String fileName = item.getOriginalFilename();
                //检查文件大小
                if(item.getSize() > maxSize){
                    return getError("上传文件大小超过限制。");
                }
                //检查扩展名
                String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                if(!Arrays.<String>asList(extMap.get(dirName).split(",")).contains(fileExt)){
                    return getError("上传文件扩展名是不允许的扩展名。\n只允许" + extMap.get(dirName) + "格式。");
                }

                SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
                String newFileName = df.format(new Date()) + "_" + new Random().nextInt(1000) + "." + fileExt;
                try{
                    File uploadedFile = new File(savePath, newFileName);
                    item.transferTo(uploadedFile);
                }catch(Exception e){
                    return getError("上传文件失败。");
                }

                Map<String, Object> succMap = new HashMap<String, Object>();
                succMap.put("error", 0);
                succMap.put("url", saveUrl + newFileName);
                return succMap;
        }

        return null;
    }

    private Map<String, Object> getError(String errorMsg) {
        Map<String, Object> errorMap = new HashMap<String, Object>();
        errorMap.put("error", 1);
        errorMap.put("message", errorMsg);
        return errorMap;
    }

    @RequestMapping(value = "/admin/kindfile/manager")
    public Object manager(HttpServletRequest request, HttpServletResponse response) {
        DataMap curUser = SessionUtil.getCurAdminUser();
        //文件保存目录路径
        String rootPath = baseSetting.getKindfile_path() + "/" + curUser.getString("USER_ID") + "/";
        //文件保存目录URL
        String rootUrl = request.getContextPath() + "/admin/kindfile/down/" + curUser.getString("USER_ID") + "/";

        //图片扩展名
        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};

        String dirName = request.getParameter("dir");
        if (dirName != null) {
            if(!Arrays.<String>asList(new String[]{"image", "flash", "media", "file"}).contains(dirName)){
                return "Invalid Directory name.";
            }
            rootPath += dirName + "/";
            rootUrl += dirName + "/";
            File saveDirFile = new File(rootPath);
            if (!saveDirFile.exists()) {
                saveDirFile.mkdirs();
            }
        }
        //根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

        //排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

        //不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            return "Access is not allowed.";
        }
        //最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            return "Parameter is not valid.";
        }
        //目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if(!currentPathFile.isDirectory()){
            return "Directory does not exist.";
        }

        //遍历目录取的文件信息
        List<Map<String, Object>> fileList = new ArrayList<Map<String, Object>>();
        if(currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if(file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if(file.isFile()){
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);

        return result;
    }

    private class NameComparator implements Comparator<Map<String, Object>> {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
            }
        }
    }

    private class SizeComparator implements Comparator<Map<String, Object>> {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
                    return 1;
                } else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }

    private class TypeComparator implements Comparator<Map<String, Object>> {
        public int compare(Map<String, Object> hashA, Map<String, Object> hashB) {
            if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
                return -1;
            } else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
                return 1;
            } else {
                return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
            }
        }
    }

    @RequestMapping(value = "/admin/kindfile/down/{userId}/{fileType}/{uploadDate}/{fileName}.{suffix}")
    public void download(HttpServletRequest request, HttpServletResponse response,
                         @PathVariable String userId,
                         @PathVariable String fileType,
                         @PathVariable String uploadDate,
                         @PathVariable String suffix,
                         @PathVariable String fileName) {
        InputStream is = null;
        OutputStream os = null;
        try {
            File file = new File(baseSetting.getKindfile_path() + "/" + userId + "/" + fileType + "/" + uploadDate + "/" + fileName + "." + suffix);
            is = new FileInputStream(file);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);

            os = new BufferedOutputStream(response.getOutputStream());
            os.write(buffer);
            os.flush();
        } catch (Exception e) {
            //判断suffix
            //图片请求可以在此显示一个默认图片
            //file显示文件已损坏等错误提示...
            logger.error("读取文件失败", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error("读取文件失败", e);
                }

                if (os != null) {
                    try {
                        os.close();
                    } catch (IOException e) {
                        logger.error("读取文件失败", e);
                    }
                }
            }
        }

    }
}
