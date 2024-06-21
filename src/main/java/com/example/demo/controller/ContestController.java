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
    public String download(String name, HttpServletRequest req, HttpServletResponse rep) {
        File file = new File(name);
        if (!file.exists()) {
            try {
                rep.sendError(404, "文件不存在，请及时练习监考老师！\n 祝您比赛顺利");
            } catch (IOException e) {
                e.printStackTrace();
                jsAlert("找不到文件", rep);
            }
            return null;
        }
        FileInputStream input = null;
        try {
            input = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            jsAlert("内部错误", rep);
        }
        ServletOutputStream output = null;
        try {
            output = rep.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            jsAlert("输出文件失败", rep);
        }
        try {
            IOUtils.copy(input, output);
        } catch (IOException e) {
            e.printStackTrace();
            jsAlert("服务器异常", rep);
        }
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @RequestMapping("/sample")
    public String sample(String name, HttpServletRequest req, HttpServletResponse rep) {

        File file = new File(name);
        if (!file.exists()) {
            try {
                rep.sendError(404, "文件不存在，请及时练习监考老师！\n 祝您比赛顺利");
            } catch (IOException e) {
                e.printStackTrace();
                jsAlert("找不到文件", rep);
            }
            return null;
        }
//        System.out.println(name);
        FileInputStream input = null;
        try {
            input = new FileInputStream(name);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ServletOutputStream output = null;
        try {
            output = rep.getOutputStream();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        int b;
//        while ((b = input.read()) != -1) {
//            output.write(b);
//        }
        try {
            IOUtils.copy(input, output);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            input.close();
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest req, HttpServletResponse rep) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String upath = req.getParameter("upath"); // 假设 upath 是一个合法的目录路径
        Part part = null;
        try {
            part = req.getPart("ufile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        if (part == null || part.getSubmittedFileName() == null || part.getSubmittedFileName().isEmpty()) {
            return jsAlert("未选择文件，提交失败", rep);
        }

        String fileName = part.getSubmittedFileName(); // 获取原始文件名
        File directory = new File(upath + "/subCode"); // 创建File对象表示目录

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

        return jsAlert("提交成功", rep);
    }
}
