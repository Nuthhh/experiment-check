package com.ysu.service.impl.test;

import com.ysu.service.IReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ReportServiceTest {

    @Autowired
    IReportService reportService;

    @Test
    public void dealReport() {

        reportService.dealReport("http://39.105.156.250/file/document/qq.doc",1,9);
    }
}