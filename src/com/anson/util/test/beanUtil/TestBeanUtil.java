package com.anson.util.test.beanUtil;

import com.anson.util.beanUtil.BeanUtils;
import org.junit.Test;

/**
 * Created by ludao on 16/2/4.
 */
public class TestBeanUtil {

    @Test
    public void testCopyProperties(){
        Test1 test1 = new Test1("test",1);
        Test2 test2 = new Test2();
        BeanUtils.copyProperties(test1,test2);
        System.out.println(test2);
    }

}
class Test1 {
    private String name ;

    private Integer age;

    public Test1() {
    }

    public Test1(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Test1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

class Test2{
    private String name ;

    private Integer age;

    private Integer tall;

    private String nickname;

    public Test2() {
    }

    public Test2(String name, Integer age, Integer tall, String nickname) {
        this.name = name;
        this.age = age;
        this.tall = tall;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", tall=" + tall +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}