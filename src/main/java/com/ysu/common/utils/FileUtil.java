package com.ysu.common.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/22 15:52
 * @Description:
 **/
public class FileUtil {

    /**
     * 功能描述: 单文件上传到文件服务器
     *
     * @param file 需要上传的文件
     * @param url  上传文件的路径
     * @return 储存在文件系统的 实验报告名称
     * @auther: han jianguo
     * @date: 2019/2/22 17:16
     */
    public static String uploadOneFile(MultipartFile file, String url) throws IOException {

        // 上传文件原名
        String fileName = file.getOriginalFilename();
        // 上传文件的后缀名称
        String fileNameSuf = fileName.substring(fileName.lastIndexOf("."));
        // 需要上传的文件的新名称
        String fileNewName = GUID.getGUID() + fileNameSuf;

        //jersy的客户端
        Client client = new Client();
        //括号里为上传的文件地址（由于我使用云服务器，所以会是如下），fileName是对上传的文件名重新赋值，因为会上传很多文件，导致文件名冲突，所以会对文件重新命名。可使用UUID工具类直接生成。
        //这里需注意，为以后下载时能还原为原先的文件名称，需要在数据库储存一个服武器文件名和原先文件名的对照表。
        WebResource webresource = client.resource(url + fileNewName);
        //上传文件，第一个参数不懂
        webresource.put(String.class, file.getBytes());

        return fileNewName;
    }

    /**
     * 功能描述: 多文件上传到文件服务器
     *
     * @auther: han jianguo
     * @date: 2019/2/22 17:17
     */
    public static String uploadManyFiles(@RequestParam("matUrl") MultipartFile[] matUrl, String url) throws IOException {
        //说明：多文件需要在后台使用数组接受，其前面需要加 @RequestParam注解才能接收，注解括号里的名称①如果是前后端分类，则是vue定义的data里变量的名称；②如果是jsp文件，则是id的名称
        //重复调用单文件上传即可
        for (MultipartFile mat : matUrl) {
            uploadOneFile(mat, url);
        }

        return "";
    }

    /**
     * 功能描述: 删除文件服务器上文件
     *
     * @param url 文件在文件服务器的完整地址
     * @auther: han jianguo
     * @date: 2019/2/22 17:37
     */
    public static void deleteFile(String url) {
        Client client = new Client();
        WebResource webResource = client.resource(url);
        webResource.delete(url);
    }

    /**
     * 功能描述: 读取本地word文档内容，支持格式 docx
     *
     * @auther: han jianguo
     * @date: 2019/2/14 13:55
     */
    public static String readDocx(String path) {
        String context = "";
        try {
            File file = new File(path);
            FileInputStream fis = new FileInputStream(file);
            XWPFDocument xdoc = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(xdoc);
            context = extractor.getText();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

    /**
     * 功能描述: 读取url上的word文档内容，支持格式 docx
     *
     * @auther: han jianguo
     * @date: 2019/2/14 14:39
     */
    public static String readDocxByUrl(String url) {
        String context = "";
        // todo

        return context;
    }

}
