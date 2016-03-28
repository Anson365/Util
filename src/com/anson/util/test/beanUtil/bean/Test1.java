package com.anson.util.test.beanUtil.bean;

/**
 * Created by ludao on 16/3/27.
 */
public class Test1 {
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
