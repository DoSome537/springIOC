package cn.xdl.bean;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SprIOC {
    List<Bean> beanList;

    public SprIOC(String path) {
        getBeanList(path);
    }


    public void getBeanList(String path){
        InputStream is = null;
        try {
            is = new FileInputStream (path);
            SAXReader sr = new SAXReader();
            Document doc = sr.read(is);
            Element root = doc.getRootElement();
            List<Element> elementList= root.elements();
            beanList = new ArrayList();
            for (Element e: elementList) {
                String id = e.attributeValue("id");
                String classpath = e.attributeValue("class");
                System.out.println("id="+id);
                System.out.println("class="+classpath);
                List<Element> propertyEleList = e.elements();
                Map<Object,Object>propertyMap = new HashMap();
                for (Element el : propertyEleList) {
                    String name = el.attributeValue("name");
                    String value = el.attributeValue("value");
                    System.out.println("InMapname="+name);
                    System.out.println("InMapvalue="+value);
                    propertyMap.put(name,value);
                }
                Bean beanTemp = new Bean(id,classpath,propertyMap);
                beanList.add(beanTemp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //得到要创建对象的数据
    /*public Bean getCreateBean(String className){
        for (Bean b : beanList) {
            if (className.equals(b.getClasspath()))
            {
                return b;
            }
        }
        return null;
    }*/
    public Bean getCreateBean(String id){
        for (Bean b : beanList) {
            if (id.equals(b.getId()))
            {
                return b;
            }
        }
        return null;
    }
   /* //得到要创建对象的构造器
    public Constructor getConstructor(String className){
        try {
            Class<?> tc = Class.forName(className);
            Constructor<?> constructor = tc.getConstructor();
            return constructor;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Field[] getField(String className){
        try {
            Class<?> tc = Class.forName(className);
            Field[] fields = tc.getDeclaredFields();
            return fields;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Method[] getMethod(String className){
        try {
            Class tc = Class.forName(className);
            Method[] methods = tc.getMethods();
            return methods;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }*/

    public Object getBean(String id){
        Bean b = getCreateBean(id);
        String className = b.getClasspath();
        Map<String,Object> propertyMap = b.getPropertyMap();
        try {
            Class tc = Class.forName(className);
            Object obj = tc.newInstance();
            Field[] fields = tc.getDeclaredFields();
            Method[] methods = tc.getMethods();
            for (int i = 0;i < fields.length;i++){
                fields[i].setAccessible(true);
                System.out.println(fields[i].getName());
                System.out.println(fields[i].getGenericType());
                for (Map.Entry entry : propertyMap.entrySet()) {
                    if(entry.getKey().equals(fields[i].getName())){
                        Method method = tc.getMethod("set" + fields[i].getName().substring(0, 1).toUpperCase() + fields[i].getName().substring(1), (Class<?>) fields[i].getGenericType());
                        if (fields[i].getGenericType().toString().contains("int")){
                            method.invoke(obj,Integer.parseInt((String) entry.getValue()));
                        }
                        if (fields[i].getGenericType().toString().contains("String")){
                            method.invoke(obj,entry.getValue());
                        }
                    }
                }
            }
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
