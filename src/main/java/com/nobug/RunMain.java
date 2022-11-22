package com.nobug;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.nobug.util.AESFile;
import com.nobug.util.FileEncUtil;
import com.nobug.util.FileToFileUtil;
import com.nobug.util.RSATextUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Objects;

/**
 * @author 389561407@qq.com
 * @version 1.0
 * @since 2022-11-17
 */
public class RunMain {
    private JPanel root;
    private JLabel filePath;
    private JTextField textField1;
    private JTextField textField3;
    private JLabel password;
    private JLabel passwordmode;
    private JButton fileb;
    private JButton bjia;
    private JButton bjie;
    private JComboBox comboBox1;

    private static boolean flag = false;

    public RunMain() {
        fileb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jFileChooser = new JFileChooser();
                jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jFileChooser.showSaveDialog(null);
                File selectedFile = jFileChooser.getSelectedFile();
                if (selectedFile == null) {
                    return;
                }
                String path = selectedFile.getPath();
                textField1.setText(path);
            }
        });

        bjia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!flag) {
                    flag = true;
                    System.out.println("加密");
                    String filePath = textField1.getText();
                    String password = textField3.getText();
                    String mode = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();

                    if ("视频V3".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encryptV3(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }

                            flag = false;
                        }).start();
                    }


                    if ("视频V2".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encryptV2(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }

                            flag = false;
                        }).start();
                    }


                    if ("视频".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encrypt(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }

                            flag = false;
                        }).start();
                    }


                    if ("txt".equals(mode)) {
                        new Thread(() -> {
                            RSATextUtil.encryptRSASync(filePath, password);
                            JOptionPane.showMessageDialog(null, "成功！");
                            flag = false;
                        }).start();
                    }


                    if ("wbc".equals(mode)) {
                        new Thread(() -> {
                            String message = FileEncUtil.encodeFile(filePath, password, mode);
                            JOptionPane.showMessageDialog(null, message);
                            flag = false;
                        }).start();
                    }

                    if ("data".equals(mode)) {
                        new Thread(() -> {
                            try {
                                FileEncUtil.encryptRSASync(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！");

                            } catch (RuntimeException ee) {
                                String message = ee.getMessage();
                                JOptionPane.showMessageDialog(null, message);
                            }
                            flag = false;
                        }).start();

                    }

                    if ("mp4".equals(mode)) {
                        new Thread(() -> {
                            try {
                                FileEncUtil.encryptRSASyncMode(filePath, password, mode);
                                JOptionPane.showMessageDialog(null, "成功！");

                            } catch (RuntimeException ee) {
                                String message = ee.getMessage();
                                JOptionPane.showMessageDialog(null, message);
                            }
                            flag = false;
                        }).start();

                    }
                } else {
                    JOptionPane.showMessageDialog(null, "程序运行中。。。");
                }


            }
        });
        bjie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!flag) {

                    System.out.println("解密");
                    flag = true;
                    String filePath = textField1.getText();
                    String password = textField3.getText();
                    String mode = Objects.requireNonNull(comboBox1.getSelectedItem()).toString();

                    if ("视频V3".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String decrypt = AESFile.decryptV2(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！" + decrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
                            flag = false;
                        }).start();
                    }


                    if ("视频V2".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String decrypt = AESFile.decryptV2(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！" + decrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
                            flag = false;
                        }).start();
                    }


                    if ("视频".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String decrypt = AESFile.decrypt(filePath, password);
                                JOptionPane.showMessageDialog(null, "成功！" + decrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
                            flag = false;
                        }).start();
                    }


                    if ("txt".equals(mode)) {
                        new Thread(() -> {
                            RSATextUtil.decryptRSASync(filePath, password);
                            JOptionPane.showMessageDialog(null, "成功！");
                            flag = false;
                        }).start();
                    }

                    if ("wbc".equals(mode)) {
                        new Thread(() -> {
                            String message = FileEncUtil.decoderFile(filePath, password);
                            JOptionPane.showMessageDialog(null, message);
                            flag = false;
                        }).start();
                    }

                    if ("data".equals(mode)) {
                        new Thread(() -> {
                            String message = "解密成功！";
                            try {
                                FileEncUtil.decryptRSASync(filePath, password);
                            } catch (RuntimeException ee) {
                                message = ee.getMessage();
                            }
                            JOptionPane.showMessageDialog(null, message);
                            flag = false;
                        }).start();
                    }

                    if ("mp4".equals(mode)) {
                        new Thread(() -> {
                            String message = "解密成功！";
                            try {
                                FileEncUtil.decryptRSASyncMode(filePath, password, mode);
                            } catch (RuntimeException ee) {
                                message = ee.getMessage();
                            }
                            JOptionPane.showMessageDialog(null, message);
                            flag = false;
                        }).start();
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "程序运行中。。。");
                }
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("加密解密工具^_^(个人学习使用，请勿用于非法)");
        frame.setContentPane(new RunMain().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        int lx = Toolkit.getDefaultToolkit().getScreenSize().width;

        int ly = Toolkit.getDefaultToolkit().getScreenSize().height;
        frame.setLocation(new Point((int) (lx / 2) - 150, (int) (ly / 2) - 150));// 设定窗口出现位置
        frame.setSize(lx / 4, ly / 4);// 设定窗口大小
        frame.setVisible(true);
    }



}
