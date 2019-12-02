package cn.xdl.bean;

import java.io.Serializable;

public class Property implements Serializable {
    private static final long serialVersionUID = 7756687305108139718L;
    private String name;
    private Object value;

    public Property() {
    }

    public Property(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Property{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
