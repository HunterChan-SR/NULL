package com.example.demo.utils;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipUtil;

import javax.print.attribute.standard.Compression;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import static io.netty.handler.ssl.OpenSslCertificateCompressionConfig.AlgorithmMode.Compress;

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


    public static void unzipAllZipsInDirectory(File directory) {
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
            unzip(zipFile, directory);
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
     * 解压单个ZIP文件
     *
     * @param zipFile   ZIP文件
     * @param outputDir 解压后的文件保存的目录
     */
    public static void unzip(File zipFile, File outputDir) {
        if (!outputDir.isDirectory()) {
            System.out.println(outputDir + "不是一个文件夹");
            return;
        }
        try (ZipInputStream zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)))) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                File file = new File(outputDir, entry.getName());
                if (entry.isDirectory()) {
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                } else {
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file))) {
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            bos.write(buffer, 0, len);
                        }
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.out.println("解压文件时发生错误: " + e.getMessage());
        }
    }

//    public static  Elgamal() {
//
//    }


}
