package com.nobug.util;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author 389561407@qq.com
 * @version 1.0
 * @since 2022-11-22
 */
public class AESFile {

    public static void main(String[] args) {
        String password = "5d5aaaafceae4023296c698248097364";
        String path = "C:\\Users\\Administrator\\Desktop\\fileEncryption-v1.0\\readme.txt";
        String encrypt = encryptV2(path, password);
        System.out.println("加密成功："+encrypt);

        String decrypt = decryptV2(encrypt, password);
        System.out.println("解密成功："+decrypt);


    }


    /**
        V2 加密 原文件名称存入文件
     * @param path
     * @param password
     * @return
     */
    public static String encryptV2(String path,String password) {
        password = getPassword(password);
        List<FileEncUtilBean> fileEncUtilBeans = FileIOUtil.fileByteReader(path, 1024 * 10);
        System.out.println("读取成功");

        // 添加一些数据到头部
        File file = Paths.get(path).toFile();
        String fileName = file.getName();
        byte[] headByte = stringToBytes(fileName,1024*10);
        FileEncUtilBean bean = new FileEncUtilBean(headByte, headByte.length);
        fileEncUtilBeans.add(0,bean);
        // 添加一些数据到头部


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

        String outPath = FileUtil.getDecryptName(path);
        FileIOUtil.fileByteWriter(fileEncUtilBeans,outPath);
        System.out.println("写出成功！");

        return outPath;
    }

    private static byte[] stringToBytes(String fileName, int len) {
        byte[] bytes = fileName.getBytes(StandardCharsets.UTF_8);
        byte[] headByte = new byte[len];
        for (int i = 0; i < bytes.length; i++) {
            headByte[i] = bytes[i];
        }
        return headByte;
    }


    /**
     * V2 解密 回复原文件名称
     * @param path
     * @param password
     * @return
     */
    public static String decryptV2(String path,String password) {
        password = getPassword(password);
        List<FileEncUtilBean> fileEncUtilBeans = FileIOUtil.fileByteReader(path, 10256);
        System.out.println("读取成功"+fileEncUtilBeans.size());

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

        FileEncUtilBean remove = fileEncUtilBeans.remove(0);
        byte[] bytes = remove.getBytes();
        System.out.println("获取到头部数据：");
        String fileName = new String(bytes, StandardCharsets.UTF_8).trim();

        String outPath = FileUtil.getOutPath(path,fileName);
        outPath = FileUtil.reFileNamePath(outPath);
        FileIOUtil.fileByteWriter(fileEncUtilBeans,outPath);
        System.out.println("写出成功！");
        return outPath;
    }


    /**
     * v1.0
     * @param path
     * @param password
     * @return
     */
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

        String outPath = FileUtil.getDecryptName(path);
        FileIOUtil.fileByteWriter(fileEncUtilBeans,outPath);
        System.out.println("写出成功！");
        return outPath;
    }


    /**
     * v1.0
     * @param path
     * @param password
     * @return
     */
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
        String outPath = FileUtil.getDecryptName(path);
        FileIOUtil.fileByteWriter(fileEncUtilBeans,outPath);
        System.out.println("写出成功！");

        return outPath;
    }

    private static String getPassword(String password) {
        return HashUtil.md5(password);
    }


}
