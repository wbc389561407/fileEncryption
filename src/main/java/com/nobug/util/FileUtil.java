package com.nobug.util;

import java.io.File;
import java.nio.file.Path;

/**
 * @author 389561407@qq.com
 * @version 1.0
 * @since 2022-11-22
 */
public class FileUtil {

    public static String reFileNamePath(String filePath) {
        return reFileNamePath(filePath,0);
    }

    public static String reFileNamePath(String filePath, int i) {
        File file = new File(filePath);
        String newPath = filePath;
        if(file.exists()){
            //如果即将输出的文件存在 则修改一下名字
            Path path = file.toPath();
            Path parent = path.getParent();
            String fileName = file.getName();
            i++;

            File file1 = null;
            do{
                i++;
                newPath =  parent.toString()+"/("+ i +")"+fileName;
                file1 = new File(newPath);
            }while (file1.exists());

        }

        return newPath;

    }
}
