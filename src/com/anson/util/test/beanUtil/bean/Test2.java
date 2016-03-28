package com.anson.util.test.beanUtil.bean;

/**
 * Created by ludao on 16/3/27.
 */
public class Test2 extends Test1{
    private Integer tall;

    private String nickname;

    public Test2() {
    }

    public Test2(Integer tall, String nickname) {
        this.tall = tall;
        this.nickname = nickname;
    }

    public Test2(String name, Integer age, Integer tall, String nickname) {
        super(name,age);
        this.tall = tall;
        this.nickname = nickname;
    }

    public Integer getTall() {
        return tall;
    }

    public void setTall(Integer tall) {
        this.tall = tall;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Test2{" +
                "tall=" + tall +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
