package com.bonc;

import org.apache.commons.lang3.StringUtils;

import java.io.*;

public class JarClassLoader extends ClassLoader {

    private String loadPath;

    public JarClassLoader(String path) {
        this.loadPath=path;
    }

    @Override
    protected Class <?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes =loadClassData(name);
        return defineClass(name,bytes,0,bytes.length);
    }

    private byte[] loadClassData(String name) {
        String classPath = loadPath+"!"+File.separator+
                StringUtils.replace(name,".",File.separator)+".class";
        FileInputStream fis;
        byte[] data = null;
        try {
            File file = new File(classPath);
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int ch;
            while ((ch = fis.read()) != -1) {
                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

}