package cn.xdl.bean;

import java.io.Serializable;
import java.util.Map;

public class Bean implements Serializable {
    private static final long serialVersionUID = 1837268034749038237L;
    private String id;
    private String classpath;
    private Map<Object,Object> propertyMap;

    public Bean(String id, String classpath, Map propertyMap) {
        this.id = id;
        this.classpath = classpath;
        this.propertyMap = propertyMap;
    }

    public Bean() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClasspath() {
        return classpath;
    }

    public void setClasspath(String classpath) {
        this.classpath = classpath;
    }

    public Map getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map propertyMap) {
        this.propertyMap = propertyMap;
    }

    @Override
    public String toString() {
        return "Bean{" +
                "id='" + id + '\'' +
                ", classpath='" + classpath + '\'' +
                ", propertyMap=" + propertyMap +
                '}';
    }
}
