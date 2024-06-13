package com.example.demo.controller;

import com.example.demo.bean.Contest;
import com.example.demo.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contest")
public class ContestController extends BaseController {
    @RequestMapping("/list")
    public String list(HttpServletRequest req) {
        // 获取当前工作目录下的FILES文件夹
        File filesDirectory = new File("FILES");

        // 确保FILES文件夹存在
        if (!filesDirectory.exists() || !filesDirectory.isDirectory()) {
            System.out.println("The 'FILES' directory does not exist or is not a directory.");
//            return null;
        }

        List<String> subDirectories = Utils.listSubDirectories(filesDirectory);
        // 确保这个方法返回的就是你的subDirectories列表
        List<Contest> retList = new ArrayList<>();
        for (var s : subDirectories) {
            Contest bean = new Contest();
            bean.setName(s);
            retList.add(bean);
        }

        req.setAttribute("retList", retList);
        return "/contest/list";
    }


    @RequestMapping("/submit")
    public String submit(String name, HttpServletRequest req) {
        String path = "FILES/" + name + "/";
        req.setAttribute("file", path + name + ".pdf");
        req.setAttribute("path", path);
        req.setAttribute("sample", path + "sample.zip");
        return "/contest/submit";
//        return "/contest/submit?name=" + name;
    }

    @RequestMapping("/download")
    public String download(String name, HttpServletRequest req, HttpServletResponse rep) throws IOException {
        FileInputStream input = new FileInputStream(name);
        ServletOutputStream output = rep.getOutputStream();
        IOUtils.copy(input, output);
        input.close();
        output.close();
        return null;
    }

    @RequestMapping("/sample")
    public String sample(String name, HttpServletRequest req, HttpServletResponse rep) throws IOException {
//        System.out.println(name);
        FileInputStream input = new FileInputStream(name);
        ServletOutputStream output = rep.getOutputStream();
//        int b;
//        while ((b = input.read()) != -1) {
//            output.write(b);
//        }
        IOUtils.copy(input, output);
        input.close();
        output.close();
        return null;
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
        req.setCharacterEncoding("UTF-8");
        String upath = req.getParameter("upath"); // 假设 upath 是一个合法的目录路径
        Part part = req.getPart("ufile");

        String fileName = part.getSubmittedFileName(); // 获取原始文件名
        File directory = new File(upath); // 创建File对象表示目录

        // 确保目录存在，不存在则创建
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // 将文件写入到指定目录下，注意这里是目录的路径加上文件名
        String filePath = directory.getAbsolutePath() + File.separator + fileName; // 使用File.separator确保跨平台兼容性
        part.write(filePath);

        return jsAlert("提交成功", rep);
    }
}
