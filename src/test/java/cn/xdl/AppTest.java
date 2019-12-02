package cn.xdl;

import static org.junit.Assert.assertTrue;

import cn.xdl.bean.SprIOC;
import cn.xdl.testbean.Person;
import cn.xdl.testbean.Student;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        SprIOC spr = new SprIOC("C:\\Users\\java-c\\reflect\\src\\bean.xml");
        //Student bean = (Student)spr.getBean("student");
        Person bean = (Person)spr.getBean("person");
        System.out.println("----------------------");
        System.out.println(bean);
    }
}
