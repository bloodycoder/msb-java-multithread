package com.picard;

import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;


public class Lecture001 {
    @Test
    public void test01(){
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
    @Test
    public void test02(){
        Object o = null;
        synchronized (o){
            System.out.println("123");
        }
    }
}
