package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipUtil;

import javax.print.attribute.standard.Compression;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
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
    public static void zipFolder(File folder, String pathInZip, ZipOutputStream zos) throws IOException {
        byte[] buffer = new byte[1024];
        File[] files = folder.listFiles();
        for (File file : files) {
            if (file.isDirectory()) {
                // 如果是目录，则递归调用
                zipFolder(file, pathInZip + "/" + file.getName(), zos);
            } else {
                // 如果是文件，则压缩该文件
                FileInputStream fis = new FileInputStream(file);
                ZipEntry zipEntry = new ZipEntry(pathInZip + "/" + file.getName());
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
    public static File saveFile(String dirFilePath, Part part) {
        String fileName = part.getSubmittedFileName(); // 获取原始文件名
        File directory = new File(dirFilePath); // 创建File对象表示目录

        // 确保目录存在，不存在则创建
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 将文件写入到指定目录下，注意这里是目录的路径加上文件名
        String filePath = directory.getAbsolutePath() + File.separator + fileName; // 使用File.separator确保跨平台兼容性
        try {
            part.write(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new File(filePath);
    }

    public static void atcoderTran(File file) {
        Map<String, String> mp = new HashMap<>();
        File userList = new File("FILES/other/user_list.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(userList));) {
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                String[] split = line.split(",");
                mp.put(split[1], split[0]);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(mp);


        String res = new String();
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
        ) {
            boolean f = false;
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null) break;
                for (Map.Entry<String, String> entry : mp.entrySet()) {
                    line = line.replace(">" + entry.getKey() + "<", ">" + entry.getKey() + "(" + entry.getValue() + ")" + "<");
                }
                //在line中查找"table-responsive"
//                if (line.contains("<table class=\"table table-bordered table-striped table-hover th-center td-center td-middle\">")) {
//                    f = true;
//                }
//                if (f)
                res += line + "\n";
//                if (line.contains("</table>"))
//                    f = false;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File csvFile = new File(file.getPath().replace(".html", ".csv"));
        try {
            htmlTableToCsv(res, csvFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            convertCsvToXlsx(csvFile, new File(file.getPath().replace(".html", ".xlsx")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileWriter writer = new FileWriter(file)) {
            writer.write(res);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        System.out.println(res);

//        return new File(file.getPath());
    }

    public static void htmlTableToCsv(String htmlContent, File outputFile) throws IOException {
        Document doc = Jsoup.parse(htmlContent);

        Elements headers = doc.select("thead tr th");
        StringBuilder csvContent = new StringBuilder();
        headers.forEach(header -> csvContent.append(header.text()).append(","));
        csvContent.setLength(csvContent.length() - 1); // 移除最后一个逗号
        csvContent.append("\n");

        // 获取表格主体的行
        Elements rows = doc.select("tbody tr");
        for (int i = 0; i < rows.size(); i++) {
            Element row = rows.get(i);
            Elements cols = row.select("td");
            if (i == rows.size() - 2 || i == rows.size() - 1) {
                csvContent.append(cols.get(0).text().replace(",", "")).append(",");
                csvContent.append("-").append(",");
                csvContent.append("-").append(",");
                for (int j = 1; j < cols.size(); j++) {
                    Element col = cols.get(j);
                    csvContent.append(col.text().replace(",", "")).append(",");
                }
            } else {
                for (Element col : cols) {
                    csvContent.append(col.text().replace(",", "")).append(",");
                }
            }
            csvContent.setLength(csvContent.length() - 1); // 移除最后一个逗号
            csvContent.append("\n");
        }

        // 写入CSV文件
        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write(csvContent.toString());
        }
    }

    public static void convertCsvToXlsx(File csvFile, File xlsxFile) throws IOException {
        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
            String line;
            int rowNumber = 0;
            while ((line = reader.readLine()) != null) {
                Row row = sheet.createRow(rowNumber++);
                String[] values = line.split(",");
                int cellNumber = 0;
                for (String value : values) {
                    Cell cell = row.createCell(cellNumber++);
                    cell.setCellValue(value.trim());
                }
            }
        }

        try (FileOutputStream outputStream = new FileOutputStream(xlsxFile)) {
            workbook.write(outputStream);
        }

        workbook.close();
    }

}
