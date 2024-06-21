package com.example.demo.controller;


import com.example.demo.utils.Utils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class RootController extends BaseController {
    //    @RequestMapping("")
//    public String root(HttpServletRequest req)
//    {
//        return "redirect:/contest/list";
//    }
    @RequestMapping("admin")
    public String admin(HttpServletRequest req) {
        return "redirect:/admin/home";
    }

}
