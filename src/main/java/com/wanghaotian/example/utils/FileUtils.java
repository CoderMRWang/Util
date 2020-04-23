package com.wanghaotian.example.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : wanghaotian
 * @despriction :
 * @date : Created in 2020/1/5
 * @modify By:
 */
public class FileUtils {

    /**
     * Description:
     * @author: wanghaotian
     * @date: 2020/1/5 15:03
     * @param:[path] 文件夹路径
     * @return:java.util.List<java.io.File>
     */
    public static List<File> getAllfile(String path) {
        List<File> allfilelist = new ArrayList<File>();
        return getAllfile(new File(path), allfilelist);
    }

    private static List<File> getAllfile(File file, List<File> allfilelist) {
        if (file.exists()) {
            //判断文件是否是文件夹，如果是，开始递归
            if (file.isDirectory()) {
                File f[] = file.listFiles();
                for (File file2 : f) {
                    getAllfile(file2, allfilelist);
                }
            } else {
                allfilelist.add(file);
            }
        }
        return allfilelist;
    }
    /**
     * Description:
     * @author: wanghaotian
     * @date: 2020/1/5 15:07
     * @param:[from, to] 原文件,传输文件
     * @return:void
     */
    public static void transferFromDemo(final String from,final String to) throws IOException {
        FileChannel fromChannel = new FileInputStream(from).getChannel();
        FileChannel toChannel = new FileOutputStream(to).getChannel();
        long position = 0;
        long count = fromChannel.size();
        toChannel.transferFrom(fromChannel, position, count);
        fromChannel.close();
        toChannel.close();
    }

}
