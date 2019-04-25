package com.qinyaoz.baselib.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;

public class ClassesUtil {
    public static List<Class> getAllClassesByBase(Class<?> clz) throws Exception{
        List<Class> ret = new ArrayList<>();
        if (null == clz) {
            return ret;
        }
        Package clzPackage = clz.getPackage();
        if (null == clzPackage) {
            return ret;
        }
        String packageName = clzPackage.getName();
        //找出所有继承clz的类
        List<Class> allClasses = getClasses(packageName, clz);
        //排除当前class
        for (Class subClass : allClasses) {
            if (clz.isAssignableFrom(subClass) && !clz.equals(subClass)) {
                if (!ret.contains(subClass)) {
                    ret.add(subClass);
                }
            }
        }

        return ret;
    }

    private static List<Class> getClasses(String packageName, Class<?> clz) throws Exception{
        List<Class> ret = new ArrayList<>();
        List<DexFile> dexFileList = new ArrayList<>();

        BaseDexClassLoader classLoader = (BaseDexClassLoader) Thread.currentThread().getContextClassLoader();
        Field field = classLoader.getClass().getSuperclass().getDeclaredField("pathList");
        field.setAccessible(true);
        Object pathList = field.get(classLoader);

        Field elementField = pathList.getClass().getDeclaredField("dexElements");
        elementField.setAccessible(true);
        Object dexElements = elementField.get(pathList);
        int dexSize = Array.getLength(dexElements);

        for (int i = 0; i < dexSize; i++) {
            Object dexElement = Array.get(dexElements, i);
            Field dexField = dexElement.getClass().getDeclaredField("dexFile");
            dexField.setAccessible(true);

            DexFile dexFile = (DexFile) dexField.get(dexElement);
            if (dexFile != null) {
                dexFileList.add(dexFile);
            }
        }

        for (DexFile dexFile : dexFileList) {
            for (Enumeration<String> enumeration = dexFile.entries(); enumeration.hasMoreElements();) {
                String name = enumeration.nextElement();
                if (name.endsWith("ComponentBoot")) {
                    Class subClz = Class.forName(name);
                    if (clz.isAssignableFrom(subClz) && !clz.equals(subClz)) {
                        ret.add(subClz);
                    }
                }
            }
        }

        return ret;
    }
}
