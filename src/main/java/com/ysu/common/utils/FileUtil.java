package com.ysu.common.utils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/22 15:52
 * @Description:
 **/
public class FileUtil {

    // word文档报告后缀
    private static final String WORD_SUF_DOC = "doc";
    private static final String WORD_SUF_DOCX = "docx";

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
     * 功能描述: 读取本地word文档内容，支持格式 docx doc
     *
     * @auther: han jianguo
     * @date: 2019/2/14 13:55
     */
    public static String readWord(String path) {
        String context = "";
        try {
            if (path.endsWith(WORD_SUF_DOC)) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                context = ex.getText();
                ex.close();
            } else if (path.endsWith(WORD_SUF_DOCX)) {
                XWPFWordExtractor docx = new XWPFWordExtractor(POIXMLDocument.openPackage(path));
                context = docx.getText();
                docx.close();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return context;
    }

    /**
     * 功能描述: 读取url上的word文档内容，支持格式 doc
     *
     * @auther: han jianguo
     * @date: 2019/2/14 14:39
     */
    public static String readWordByUrl(String fileUrl) {
        String context = new String();
        int HttpResult; // 服务器返回的状态
        try {
            URL url = new URL(fileUrl); // 创建URL
            URLConnection urlConn = url.openConnection(); // 试图连接并取得返回状态码
            urlConn.connect();
            HttpURLConnection httpConn = (HttpURLConnection) urlConn;
            HttpResult = httpConn.getResponseCode();
            // 判断连接是否成功，不成功返回null
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                return null;
            }
            BufferedInputStream bis = new BufferedInputStream(urlConn.getInputStream());
            WordExtractor extractor = new WordExtractor(bis);
            context = extractor.getText();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return context;
    }

}
