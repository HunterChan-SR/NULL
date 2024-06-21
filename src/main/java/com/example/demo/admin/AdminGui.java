package com.example.demo.admin;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.util.Arrays;
//import java.util.List;
//
//public class AdminGui extends JFrame {
//    private JComboBox<String> startTimeComboBox;
//    private JComboBox<String> endTimeComboBox;
//    private JButton setTimeButton;
//    private JPanel rightPanel;
//
//    private List<String> contestFolders; // 假设FILES文件夹下的比赛名称列表
//
//    public AdminGui() {
//        initializeComponents();
//        setupLayout();
//        addEventListeners();
//    }
//
//    private void initializeComponents() {
//        // 初始化组件
//        startTimeComboBox = new JComboBox<>(new String[]{"请选择开始时间", "09:00", "10:00", "11:00"});
//        endTimeComboBox = new JComboBox<>(new String[]{"请选择结束时间", "10:00", "11:00", "12:00"});
//        setTimeButton = new JButton("设置比赛时间");
//
//        // 假设FILES文件夹下比赛名称的获取逻辑
//        File filesDir = new File("FILES");
//        contestFolders = Arrays.asList(filesDir.list());
//        rightPanel = new JPanel(new GridLayout(contestFolders.size() + 1, 1)); // 增加一行用于放置按钮
//
//        // 为每个比赛添加对应的按钮
//        for (String contest : contestFolders) {
//            JPanel contestPanel = new JPanel(new FlowLayout());
//            contestPanel.add(new JLabel(contest));
//            JButton uploadProblemButton = new JButton("上传题面");
//            JButton uploadSampleButton = new JButton("上传sample");
//            JButton packButton = new JButton("一键打包");
//            contestPanel.add(uploadProblemButton);
//            contestPanel.add(uploadSampleButton);
//            contestPanel.add(packButton);
//            rightPanel.add(contestPanel);
//        }
//    }
//
//    private void setupLayout() {
//        // 设置布局
//        setLayout(new GridLayout(1, 2));
//
//        // 左侧时间选择面板
//        JPanel leftPanel = new JPanel(new BorderLayout());
//        leftPanel.add(new JLabel("比赛时间设置"), BorderLayout.NORTH);
//        leftPanel.add(startTimeComboBox, BorderLayout.CENTER);
//        leftPanel.add(endTimeComboBox, BorderLayout.CENTER);
//        leftPanel.add(setTimeButton, BorderLayout.SOUTH);
//
//        // 添加到主窗体
//        add(leftPanel);
//        add(rightPanel);
//
//        // 窗口基本设置
//        setTitle("比赛设置界面");
//        setDefaultCloseOperation(EXIT_ON_CLOSE);
//        pack();
//        setLocationRelativeTo(null);
//    }
//
//    private void addEventListeners() {
//        setTimeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                String startTime = (String) startTimeComboBox.getSelectedItem();
//                String endTime = (String) endTimeComboBox.getSelectedItem();
//                if (!startTime.equals("请选择开始时间") && !endTime.equals("请选择结束时间")) {
//                    JOptionPane.showMessageDialog(null, "比赛时间已设置：" + startTime + " 至 " + endTime);
//                } else {
//                    JOptionPane.showMessageDialog(null, "请完整选择比赛时间");
//                }
//            }
//        });
//
//        // 这里可以为每个比赛的按钮添加事件监听器
//        // 示例：为第一个比赛的"上传题面"按钮添加监听器
//        if (!contestFolders.isEmpty()) {
//            JPanel firstContestPanel = (JPanel) rightPanel.getComponent(0);
//            JButton firstUploadProblemButton = (JButton) firstContestPanel.getComponents()[1];
//            firstUploadProblemButton.addActionListener(new ActionListener() {
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    JFileChooser fileChooser = new JFileChooser();
//                    int returnValue = fileChooser.showOpenDialog(null);
//                    if (returnValue == JFileChooser.APPROVE_OPTION) {
//                        System.out.println("选择了题面文件: " + fileChooser.getSelectedFile().getName());
//                    }
//                }
//            });
//            // 类似地，为其他按钮添加监听器...
//        }
//    }
//
//}

/**
 * 弃用
 */
public class AdminGui {

}