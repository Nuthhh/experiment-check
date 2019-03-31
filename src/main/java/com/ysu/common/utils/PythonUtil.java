package com.ysu.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: han jianguo
 * @Date: 2019/3/31 13:31
 * @Description:
 **/
public class PythonUtil {

    /**
     * 功能描述: 调用本地python脚本
     *
     * @param command    pyhton命令，常见如python python3
     * @param scriptPath 脚本绝对路径
     * @param params     运行脚本参数
     * @auther: han jianguo
     * @date: 2019/3/31 13:46
     */
    public static String runPython(String command, String scriptPath, String... params) {
        StringBuffer sb = new StringBuffer();
        try {
            String[] args = new String[params.length + 2];
            args[0] = command;
            args[1] = scriptPath;
            if (null != params && params.length != 0) {
                for (int i = 0; i < params.length; i++) {
                    args[i + 2] = params[i];
                }
            }
            Process proc = Runtime.getRuntime().exec(args);// 执行py文件
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();

        }

        return sb.toString();
    }

}
