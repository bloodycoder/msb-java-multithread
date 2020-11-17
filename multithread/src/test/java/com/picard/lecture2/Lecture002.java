package com.picard.lecture2;

import org.junit.Test;

import java.lang.ref.WeakReference;

class M{
    @Override
    protected void finalize() throws Throwable{
        System.out.println("finalize");
    }
}
public class Lecture002 {
    @Test
    public void test01(){
        /***
         * 强引用演示
         */
        M m = new M();
        m = null;
        System.gc();
    }
    @Test
    public void test02(){
        /**
         * 弱引用
         * */
        WeakReference<M> m = new WeakReference<>(new M());
        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        //
        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();
    }

}
