package com.example.demo.controller;


import com.example.demo.bean.Contest;
import com.example.demo.utils.Utils;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipOutputStream;


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
    private final String PWD = "123monisai";

    public boolean AssertPWD(HttpServletRequest req) {
        String pwd = Utils.getToken(req, "Token");
        if (pwd == null || PWD.equals(pwd) == false) {
            return false;
        } else {
            return true;
        }
    }

    @RequestMapping("/")
    public String admin(HttpServletRequest req) {
        if (!AssertPWD(req)) {
            return "redirect:/admin/login";
        } else {
            return "redirect:/admin/home";
        }
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse rep) {
        return "/admin/login";
    }

    @RequestMapping("/subpwd")
    public String subpwd(HttpServletRequest req, HttpServletResponse rep) {
        String pwd = req.getParameter("pwd");
        if (pwd == null || PWD.equals(pwd) == false) {
            return jsAlert("密码错误", rep);
        } else {

            Cookie passwordCookie = new Cookie("Token", pwd);
            passwordCookie.setMaxAge(3600 * 24 * 2);
            passwordCookie.setPath("/");
            rep.addCookie(passwordCookie);

            return jsAlert("登录成功", "/admin/home", rep);
        }
    }

    @RequestMapping("/home")
    public String home(HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            File filesDirectory = new File("FILES");
            List<String> subDirectories = Utils.listSubDirectories(filesDirectory);
            // 确保这个方法返回的就是你的subDirectories列表
            List<Contest> retList = new ArrayList<>();
            for (var s : subDirectories) {
                if (s.equals("other")) {
                    continue;
                }
                Contest bean = new Contest();
                bean.setName(s);
                File packageFile = new File("FILES/" + s + "/subCode_" + s + ".zip");
                bean.setPackaged(packageFile.exists());
                retList.add(bean);
            }
            req.setAttribute("retList", retList);


            return "/admin/home";
        }
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            String addname = req.getParameter("addname");
            if (addname == null || addname.isEmpty()) {
                return jsAlert("请输入比赛名称", rep);
            } else {
                File file = new File("FILES/" + addname);
                if (file.exists()) {
                    return jsAlert("比赛名称已存在", "/admin/home", rep);
                } else {
                    file.mkdir();
                    return jsAlert("比赛名称添加成功", "/admin/home", rep);
                }
            }
        }
    }


    @RequestMapping("/delete")
    public String delete(HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            String name = req.getParameter("name");
            if (name == null || name.isEmpty()) {
                return jsAlert("删除失败", rep);
            } else {
                File file = new File("FILES/" + name);
                if (file.exists()) {
//                    file.delete();
                    Utils.DeleteAllInDirectory("FILES/" + name);
                    return jsAlert("比赛删除成功", "/admin/home", rep);
                } else {
                    return jsAlert("删除失败", "/admin/home", rep);
                }
            }
        }
    }

    @RequestMapping("/uploadpdf")
    public String uploadpdf(HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            String name = req.getParameter("name");
            if (name == null || name.isEmpty()) {
                return jsAlert("上传失败", rep);
            } else {
                File file = new File("FILES/" + name);
                if (!file.exists()) {
                    return jsAlert("上传失败", "/admin/home", rep);
                } else {
                    File pdffile = new File("FILES/" + name + "/" + name + ".pdf");
                    try {
                        req.setCharacterEncoding("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return jsAlert("编码失败", rep);
                    }
                    Part part = null;
                    try {
                        part = req.getPart("pdffile");
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                        return jsAlert("上传失败", "/admin/home", rep);
                    }

                    if (part == null || part.getSubmittedFileName() == null || part.getSubmittedFileName().isEmpty()) {
                        return jsAlert("上传失败", "/admin/home", rep);
                    }

                    try {
                        part.write(pdffile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return jsAlert("写入失败", rep);
                    }

                    return jsAlert("上传成功:" + pdffile.getName(), "/admin/home", rep);
                }
            }
        }
    }

    @RequestMapping("/uploadzip")
    public String uploadzip(HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            String name = req.getParameter("name");
            if (name == null || name.isEmpty()) {
                return jsAlert("上传失败", rep);
            } else {
                File file = new File("FILES/" + name);
                if (!file.exists()) {
                    return jsAlert("上传失败", "/admin/home", rep);
                } else {
                    File zipfile = new File("FILES/" + name + "/" + "sample.zip");
                    try {
                        req.setCharacterEncoding("UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                        return jsAlert("编码错误", rep);
                    }
                    Part part = null;
                    try {
                        part = req.getPart("zipfile");
                    } catch (IOException | ServletException e) {
                        e.printStackTrace();
                        return jsAlert("上传失败", "/admin/home", rep);
                    }
                    if (part == null || part.getSubmittedFileName() == null || part.getSubmittedFileName().isEmpty()) {
                        return jsAlert("上传失败", "/admin/home", rep);
                    }
                    try {
                        part.write(zipfile.getAbsolutePath());
                    } catch (IOException e) {
                        e.printStackTrace();
                        return jsAlert("写入失败", rep);
                    }
                    return jsAlert("上传成功:" + zipfile.getName(), "/admin/home", rep);

                }
            }
        }
    }

    @RequestMapping("/package")
    public String packagefile(HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            String name = req.getParameter("name");
            File file = new File("FILES/" + name + "/subCode");
            if (!file.exists()) {
                return jsAlert("打包失败,请保证至少存在一份学生提交代码", "/admin/home", rep);
            } else {
                Utils.unzipAllZipsInDirectory(new File("FILES/" + name + "/" + "subCode"));
//                Utils.DeleteAllZipsInDirectory("FILES/" + name + "/" + "subCode");

                File zipfile = new File("FILES/" + name + "/subCode_" + name + ".zip");
                try {
                    // 创建 ZIP 输出流
                    FileOutputStream fos = new FileOutputStream(zipfile);
                    ZipOutputStream zos = new ZipOutputStream(fos);
//
//                    // 调用递归方法将文件夹内容添加到 ZIP 输出流
                    Utils.zipFolder(file, name, zos);
//
//                    // 关闭流
                    zos.close();
                    fos.close();
//
//                    FileInputStream input = new FileInputStream("FILES/" + name + "/subCode_" + name + ".zip");
//                    ServletOutputStream output = rep.getOutputStream();
//                    IOUtils.copy(input, output);
//                    input.close();
//                    output.close();

//
                    return jsAlert("打包成功:" + zipfile.getName(), "/admin/home", rep);
                } catch (IOException e) {
                    e.printStackTrace();
                    return jsAlert("打包过程中发生错误", "/admin/home", rep);
                }
            }
        }
//        return "/admin/home";
    }

    @RequestMapping("/download")
    public String download(String name, HttpServletRequest req, HttpServletResponse rep) {
        if (!AssertPWD(req)) {
            return jsAlert("请输入正确的管理员密码", "/admin/login", rep);
        } else {
            try (FileInputStream input = new FileInputStream("FILES/" + name + "/subCode_" + name + ".zip");
                 ServletOutputStream output = rep.getOutputStream()) {

                // 设置响应头以指定下载文件的名称
                String contentDisposition = "attachment;filename=" + URLEncoder.encode(name + ".zip", StandardCharsets.UTF_8);
                rep.setHeader(HttpHeaders.CONTENT_DISPOSITION, contentDisposition);

                // 设置响应类型为application/octet-stream，指示这是一个二进制文件
                rep.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);

                // 复制文件流到输出流
                IOUtils.copy(input, output);

            } catch (FileNotFoundException e) {
                // 更好的错误处理，比如记录日志，返回错误信息给前端
                throw new RuntimeException("File not found", e);
            } catch (IOException e) {
                // 同样，记录日志，适当处理
                throw new RuntimeException("Error during file download", e);
            }
            return null;
        }
    }


}
