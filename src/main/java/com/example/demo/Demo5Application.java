package com.example.demo;

import com.example.demo.admin.AdminGui;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;
import java.io.File;

@SpringBootApplication
public class Demo5Application extends JFrame {

    public static void main(String[] args) {

        SpringApplication.run(Demo5Application.class, args);

        // 获取当前工作目录下的FILES文件夹
        File filesDirectory = new File("FILES");

        // 确保FILES文件夹存在
        if (!filesDirectory.exists() || !filesDirectory.isDirectory()) {
            System.out.println("The 'FILES' directory does not exist or is not a directory.");
            filesDirectory.mkdir();
            //创建文件"FILES/.pwd"
            File pwdFile = new File(filesDirectory, ".pwd");


//            return null;
        }

        System.out.println("~~~~元神,启动~~~~~");

    }


}
