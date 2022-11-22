package com.nobug.util;

import java.util.List;

/**
 * @author 389561407@qq.com
 * @version 1.0
 * @since 2022-11-22
 */
public class AESFile {
//    public static void main(String[] args) {
//        String password = "5d5aaaafceae4023296c698248097364";
//        String path = "C:\\Users\\Administrator\\Desktop\\dev1\\666.mkv";
//        String encrypt = encrypt(path, password);
//        System.out.println("加密成功："+encrypt);
//
//        String decrypt = decrypt(encrypt, password);
//        System.out.println("解密成功："+decrypt);
//
//    }

    public static String decrypt(String path,String password) {
        password = getPassword(password);
        List<FileEncUtilBean> fileEncUtilBeans = FileIOUtil.fileByteReader(path, 10256);
        System.out.println("读取成功");
        long l = System.currentTimeMillis();
        for (FileEncUtilBean fileEncUtilBean : fileEncUtilBeans) {
            byte[] bytes = fileEncUtilBean.getBytes();
            byte[] decrypt = new byte[0];
            try {
                decrypt = AESUtil.decrypt(bytes, password);
            } catch (Exception e) {
                throw new RuntimeException("解密失败！");
            }
            fileEncUtilBean.setBytes(decrypt);
            fileEncUtilBean.setLen(decrypt.length);
        }
        System.out.println("解密完成："+(System.currentTimeMillis() - l));

        String outPath = FileUtil.reFileNamePath(path);
        FileIOUtil.fileByteWriter(fileEncUtilBeans,outPath);
        System.out.println("写出成功！");
        return outPath;
    }


    public static String encrypt(String path,String password) {
        password = getPassword(password);
        List<FileEncUtilBean> fileEncUtilBeans = FileIOUtil.fileByteReader(path, 1024 * 10);
        System.out.println("读取成功");
        long l = System.currentTimeMillis();

        for (FileEncUtilBean fileEncUtilBean : fileEncUtilBeans) {
            byte[] bytes = fileEncUtilBean.getBytes();
            byte[] encrypt = new byte[0];
            try {
                encrypt = AESUtil.encrypt(bytes, password);
            } catch (Exception e) {
                throw new RuntimeException("加密失败！");
            }
            fileEncUtilBean.setBytes(encrypt);
            fileEncUtilBean.setLen(encrypt.length);
        }
        System.out.println("加密完成："+(System.currentTimeMillis() -l));
        String outPath = FileUtil.reFileNamePath(path);
        FileIOUtil.fileByteWriter(fileEncUtilBeans,outPath);
        System.out.println("写出成功！");

        return outPath;
    }

    private static String getPassword(String password) {
        return HashUtil.md5(password);
    }


}
