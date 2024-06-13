package com.example.demo;

import com.example.demo.admin.AdminGui;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.swing.*;

@SpringBootApplication
public class Demo5Application extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new AdminGui().setVisible(true);
            }
        });


        SpringApplication.run(Demo5Application.class, args);
        System.out.println("YuanShen QiDong");
    }


}
