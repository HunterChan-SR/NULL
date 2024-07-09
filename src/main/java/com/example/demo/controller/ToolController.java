package com.example.demo.controller;

import com.example.demo.bean.Contest;
import com.example.demo.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tool")
public class ToolController extends BaseController {
    @RequestMapping("/atcoder")
    public String atcoder(HttpServletRequest req) {
        req.setAttribute("uppath", "FILES/other");
        File filesDirectory = new File("FILES/other");
        File[] files = filesDirectory.listFiles();

        List<String> retList = new ArrayList<>();

        for (File file : files) {
            if (!file.isDirectory()) {
                retList.add(file.getPath());
            }
        }
        System.out.println(retList.size());
        System.out.println(retList);
        req.setAttribute("retList", retList);
        return "tool/atcoder";
    }


    @RequestMapping("/upload")
    public String upload(HttpServletRequest req, HttpServletResponse rep) {
        try {
            req.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String uppath = req.getParameter("uppath");
        Part part = null;
        try {
            part = req.getPart("upfile");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
        if (part == null || part.getSubmittedFileName() == null || part.getSubmittedFileName().isEmpty()) {
            return jsAlert("未选择文件，提交失败", rep);
        }

        File file = Utils.saveFile(uppath, part);

        Utils.atcoderTran(file);


        return jsAlert("上传成功", rep);
    }

    @RequestMapping("/download")
    public String download(String filename, HttpServletRequest req, HttpServletResponse rep) {
        FileInputStream input = null;
        try {
            input = new FileInputStream(filename);
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

}
