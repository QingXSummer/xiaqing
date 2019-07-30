package com.bonc;


import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author QingX
 * @date
 */
public class Test {

    public void test(String name){
        System.out.println("调用人"+name);
    }

    public static void main(String[] args) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException, ClassNotFoundException {
        JarFile jarFile = new JarFile("C:\\Users\\QingX\\IdeaProjects\\xiaqing\\target\\classes\\test.jar");
        Enumeration <JarEntry> enumeration = jarFile.entries();
        while (enumeration.hasMoreElements()) {
            JarEntry entry = enumeration.nextElement();
//            System.out.println(entry.getName());
            if (StringUtils.equals(entry.getName(), "META-INF/MANIFEST.MF")){
                InputStream stream = jarFile.getInputStream(entry);
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
                String tmp ;
                while ((tmp=reader.readLine())!=null){
                    System.out.println(tmp);
                }
            }
        }
        Class cl = new JarClassLoader("C:\\Users\\QingX\\IdeaProjects\\xiaqing\\target\\classes\\test.jar")
                .loadClass("org/Test.class");
        System.out.println(cl.getName());
        Method method = cl.getMethod("test",String.class);
        method.invoke(cl.newInstance(),"夏清");
//        File file = new File(StringUtils.join("C:\\Users\\QingX\\IdeaProjects\\xiaqing\\target\\classes\\test.jar",
//                "!",File.separator,"org/Test.class"));
//        InputStream stream = new FileInputStream(file);
//        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//        String tmp ;
//        while ((tmp=reader.readLine())!=null){
//            System.out.println(tmp);
//        }
    }
}
