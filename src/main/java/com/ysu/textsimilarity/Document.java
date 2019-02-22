package com.ysu.textsimilarity;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.File;
import java.io.FileInputStream;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/21 15:22
 * @Description: word文档相关操作
 **/
public class Document {

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
