package com.example.demo.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    /**
     * 列出指定目录下的所有子目录。
     *
     * @param directory 要扫描的目录。
     * @return 包含所有子目录名称的列表。
     */
     public static List<String> listSubDirectories(File directory) {
        List<String> subDirs = new ArrayList<>();
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    subDirs.add(file.getName());
                }
            }
        }

        return subDirs;
    }
}
