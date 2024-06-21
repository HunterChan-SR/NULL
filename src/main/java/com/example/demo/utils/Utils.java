package com.example.demo.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class Utils {
    final static int MOD = 998244353;
    final static int HASHCODE = ((-1292305044 + MOD) % MOD + MOD) % MOD;

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

    public static int qpow(int a, int b) {
        int ans = 1;
        while (b > 0) {
            if ((b & 1) == 1) {
                ans = (int) ((long) ans * a % MOD);
            }
            a = (int) ((long) a * a % MOD);
            b >>= 1;
        }
        return ans;
    }

    //    public static String generate() {
//        int x =
//    }
    public static String getToken(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // 添加此方法用于递归压缩文件夹
    public static void zipFolder(File folder, ZipOutputStream zos) throws IOException {
        byte[] buffer = new byte[1024];
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是目录，则递归调用
                zipFolder(file, zos);
            } else {
                // 如果是文件，则压缩该文件
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(file.getAbsolutePath());
                zos.putNextEntry(zipEntry);
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();
            }
        }
    }


    public static void unzipAllZipsInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定路径不存在或不是一个目录");
            return;
        }

        File[] zipFiles = directory.listFiles((dir, name) -> name.endsWith(".zip"));
        if (zipFiles == null) {
            System.out.println("没有找到ZIP文件");
            return;
        }

        for (File zipFile : zipFiles) {
            unzip(zipFile.getAbsolutePath());
        }
    }

    public static void DeleteAllZipsInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定路径不存在或不是一个目录");
            return;
        }

        File[] zipFiles = directory.listFiles((dir, name) -> name.endsWith(".zip"));
        if (zipFiles == null) {
            System.out.println("没有找到ZIP文件");
            return;
        }

        for (File zipFile : zipFiles) {
            zipFile.delete();
        }
    }

    public static void DeleteAllInDirectory(String directoryPath) {
        File directory = new File(directoryPath);
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("指定路径不存在或不是一个目录");
            return;
        }

        File[] files = directory.listFiles();
        if (files == null) {
            System.out.println("没有找到ZIP文件");
        }

        for (File file : files) {
            if (file.isDirectory()) {
                DeleteAllInDirectory(file.getAbsolutePath());
            } else {
                file.delete();
            }
        }
        directory.delete();
    }

    /**
     * 解压单个ZIP文件到指定目录下的子文件夹
     *
     * @param zipFilePath ZIP文件的绝对路径
     */
    public static void unzip(String zipFilePath) {
        try (ZipFile zip = new ZipFile(zipFilePath)) {
            Path destDir = Paths.get(zip.getName().substring(0, zip.getName().lastIndexOf('/')));
//            Files.createDirectories(destDir); // 创建目标目录

            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                Path filePath = destDir.resolve(entry.getName());
                if (entry.isDirectory()) {
                    Files.createDirectories(filePath);
                } else {
                    Files.copy(zip.getInputStream(entry), filePath);
                }
            }
            System.out.println(zip.getName() + " 解压完成");
        } catch (IOException e) {
            System.err.println("解压 " + zipFilePath + " 时发生错误: " + e.getMessage());
        }
    }

//    public static  Elgamal() {
//
//    }


}
