package com.ckj.base.pressure;

import com.google.common.collect.Lists;

import java.io.*;
import java.util.ArrayList;

public class PressureCase {

    public static void main(String[] args) {
        ArrayList<String> sqls = Lists.newArrayList();

        for (int i = 0; i < 100000; i++) {
            String sql = "insert into pressure_test.pressure_info (task_id,target,level) values (" + 300 + i + ",\"target_01\"," + i + ");";
            sqls.add(sql);
        }

        for (int i = 0; i < 100000; i++) {
            String sql = "insert into pressure_test.pressure_info (task_id,target,level) values (" + 400 + i + ",\"target_02\"," + i + ");";
            sqls.add(sql);
        }

        try {
            OutputStream os = new FileOutputStream("text2.sql");
            OutputStreamWriter writer = new OutputStreamWriter(os, "UTF-8");
            sqls.stream().forEach(f -> {
                try {
                    writer.write(f);
                    writer.write("\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            writer.close();
            // 关闭写入流,同时会把缓冲区内容写入文件,所以上面的注释掉
            os.close();
            // 关闭输出流,释放系统资源
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
