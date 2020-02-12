package com.zero.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * author: zero
 * description: []
 * time: 2019-12-14
 * version: []
 */
public class ConvertToExcel {
    public static List<String> readLines = new ArrayList<>();

    private static final Logger LOGGER = LoggerFactory.getLogger(ConvertToExcel.class);
    public static void exportExcel() throws Exception {

        // 定义表的标题
        String title = "Finance-excel转换工具v1.0";

        //定义表的列名
        String[] rowsName = new String[] { "栏目1", "栏目2", "栏目3", "栏目4","栏目5", "栏目6", "栏目7","栏目8", "栏目9","栏目10"};

        //定义表的内容
        List<Object[]> dataList = new ArrayList<Object[]>();
        LOGGER.info("start to convert");
        LOGGER.debug("start debug");
        try {
            readLines = FileUtils.readLines(new File("input.txt"),"GBK");
            if (CollectionUtils.isEmpty(readLines)) {
                JOptionPane.showMessageDialog(null, "txt源文件为空，加点东西吧", "提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "txt源文件异常，请检查文件路径或格式", "提示", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
        for (String readLine : readLines) {
            if (StringUtils.isEmpty(readLine.trim())) {
                continue;
            }
            String[] line  = readLine.trim().split("\\s+");
            dataList.add(line);
        }
        // 创建ExportExcel对象
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);

        // 输出Excel文件
        try {
            OutputStream output = new FileOutputStream("finance.xls");
            ex.export(output);
            output.close();
            JOptionPane.showMessageDialog(null, "导出成功，请查看finance.xls文件", "提示", JOptionPane.WARNING_MESSAGE);
        } catch (IOException e) {
            LOGGER.error("output happened exception,{}", ExceptionUtils.getStackTrace(e));
        }

    }

}
