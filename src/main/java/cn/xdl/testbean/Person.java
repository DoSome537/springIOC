package cn.xdl.testbean;

public class Person {
    private String nam;
    private String ag;

    public Person() {
    }

    public Person(String nam, String ag) {
        this.nam = nam;
        this.ag = ag;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
    }

    @Override
    public String toString() {
        return "Person{" +
                "nam='" + nam + '\'' +
                ", ag='" + ag + '\'' +
                '}';
    }
}
