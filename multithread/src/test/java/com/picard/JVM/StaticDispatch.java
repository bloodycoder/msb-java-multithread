package com.picard.JVM;

public class StaticDispatch {
    static abstract class Human{}
    static class Man extends Human{}
    static class Women extends Human{}
    public void sayHello(Human guy){
        System.out.println("hello guy");
    }
    public void sayHello(Man guy){
        System.out.println("hello gentleman");
    }
    public void sayHello(Women guy){
        System.out.println("hello lady");
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human women = new Women();
        StaticDispatch sr = new StaticDispatch();
        sr.sayHello(man);
        sr.sayHello(women);
    }
}
