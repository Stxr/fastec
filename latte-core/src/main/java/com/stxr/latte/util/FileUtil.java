package com.stxr.latte.util;

import com.stxr.latte.app.Latte;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by stxr on 2018/7/18.
 */

public class FileUtil {
    public static File writeToDisk(InputStream is, String downloadDir, String name, String extension) {
        BufferedInputStream bs = new BufferedInputStream(is);
        return null;
    }
    public static File writeToDisk(InputStream is, String downloadDir, String name) {
        return null;
    }

    public static String getExtension(String fileName) {
        if (fileName==null) {
            return null;
        }
        int index = fileName.lastIndexOf('.');

        return fileName.substring(index+1,fileName.length());
    }

    public static String getRawFile(int rawId) {
        InputStream is = Latte.getApplication().getResources().openRawResource(rawId);
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();
        String s;
        try {
            while ((s=br.readLine()) != null) {
                builder.append(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return builder.toString();
    }
}
