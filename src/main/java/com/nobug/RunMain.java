package com.nobug;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.nobug.util.AESFile;

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
    private JLabel status;
    private JLabel stautsShow;

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

                    //type to type 文件类型不变 V1
                    if ("T2T".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encrypt(filePath, password, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
                            flag = false;
                        }).start();
                    }

                    //rename type to Time mp4 文件改为MP4文件 文件名为时间戳 解密后会恢复原有名字
                    if ("RT2TM".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encrypt(filePath, password, mode, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
                            flag = false;
                        }).start();
                    }

                    //rename type to MD5 mp4 文件改为MP4文件 文件名为 32为 MD5 解密后会恢复原有名字
                    if ("RT2MM".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encrypt(filePath, password, mode, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }

                            flag = false;
                        }).start();
                    }


                    //rename to time 文件名改为时间戳  解密后会恢复原有名字 V3
                    if ("R2T".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encrypt(filePath, password, mode, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }

                            flag = false;
                        }).start();
                    }
                    //rename to MD5  文件名改为 MD5 解密后会恢复原有名字
                    if ("R2M".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String encrypt = AESFile.encrypt(filePath, password, mode, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + encrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
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

                    if ("T2T".equals(mode)) {
                        new Thread(() -> {
                            try {
                                String decrypt = AESFile.decrypt(filePath, password, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + decrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
                            flag = false;
                        }).start();
                    } else {
                        new Thread(() -> {
                            try {
                                String decrypt = AESFile.decrypt(filePath, password, mode, stautsShow);
                                JOptionPane.showMessageDialog(null, "成功！" + decrypt);
                            } catch (RuntimeException e1) {
                                JOptionPane.showMessageDialog(null, e1.getMessage());
                            }
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


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new GridLayoutManager(9, 4, new Insets(0, 0, 0, 0), -1, -1));
        root.setBackground(new Color(-11611918));
        filePath = new JLabel();
        filePath.setBackground(new Color(-857102));
        Font filePathFont = this.$$$getFont$$$(null, Font.BOLD, 18, filePath.getFont());
        if (filePathFont != null) filePath.setFont(filePathFont);
        filePath.setText("文件路径：");
        root.add(filePath, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        passwordmode = new JLabel();
        Font passwordmodeFont = this.$$$getFont$$$(null, Font.BOLD, 18, passwordmode.getFont());
        if (passwordmodeFont != null) passwordmode.setFont(passwordmodeFont);
        passwordmode.setText("加密模式：");
        root.add(passwordmode, new GridConstraints(1, 2, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField1 = new JTextField();
        Font textField1Font = this.$$$getFont$$$(null, Font.BOLD, 18, textField1.getFont());
        if (textField1Font != null) textField1.setFont(textField1Font);
        root.add(textField1, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        fileb = new JButton();
        Font filebFont = this.$$$getFont$$$(null, Font.BOLD, 18, fileb.getFont());
        if (filebFont != null) fileb.setFont(filebFont);
        fileb.setText("选择文件");
        root.add(fileb, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bjia = new JButton();
        Font bjiaFont = this.$$$getFont$$$(null, Font.BOLD, 18, bjia.getFont());
        if (bjiaFont != null) bjia.setFont(bjiaFont);
        bjia.setText("加密");
        root.add(bjia, new GridConstraints(8, 0, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        bjie = new JButton();
        Font bjieFont = this.$$$getFont$$$(null, Font.BOLD, 18, bjie.getFont());
        if (bjieFont != null) bjie.setFont(bjieFont);
        bjie.setText("解密");
        root.add(bjie, new GridConstraints(8, 2, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        comboBox1 = new JComboBox();
        Font comboBox1Font = this.$$$getFont$$$(null, Font.BOLD, 18, comboBox1.getFont());
        if (comboBox1Font != null) comboBox1.setFont(comboBox1Font);
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("T2T");
        defaultComboBoxModel1.addElement("RT2TM");
        defaultComboBoxModel1.addElement("RT2MM");
        defaultComboBoxModel1.addElement("R2T");
        defaultComboBoxModel1.addElement("R2M");
        comboBox1.setModel(defaultComboBoxModel1);
        comboBox1.setToolTipText("");
        root.add(comboBox1, new GridConstraints(1, 3, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        password = new JLabel();
        Font passwordFont = this.$$$getFont$$$(null, Font.BOLD, 18, password.getFont());
        if (passwordFont != null) password.setFont(passwordFont);
        password.setText("密码：");
        root.add(password, new GridConstraints(1, 0, 4, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        textField3 = new JPasswordField();
        Font textField3Font = this.$$$getFont$$$(null, Font.BOLD, 18, textField3.getFont());
        if (textField3Font != null) textField3.setFont(textField3Font);
        root.add(textField3, new GridConstraints(1, 1, 4, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        stautsShow = new JLabel();
        Font stautsShowFont = this.$$$getFont$$$(null, Font.BOLD, 20, stautsShow.getFont());
        if (stautsShowFont != null) stautsShow.setFont(stautsShowFont);
        stautsShow.setText("");
        stautsShow.setToolTipText("");
        root.add(stautsShow, new GridConstraints(5, 1, 3, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        status = new JLabel();
        Font statusFont = this.$$$getFont$$$(null, Font.BOLD, 20, status.getFont());
        if (statusFont != null) status.setFont(statusFont);
        status.setText("状态：");
        root.add(status, new GridConstraints(5, 0, 3, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }

}
