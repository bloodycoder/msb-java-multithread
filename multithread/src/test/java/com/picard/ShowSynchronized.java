package com.picard;

public class ShowSynchronized {
    public static void main(String[] args) {
        //synchronized 字节码演示
        Object o = new Object();
        synchronized (o){
            System.out.println("123");
        }
    }
}
