package com.picard.lecture3;

import org.junit.Test;

public class T02_HowToCreateThread {
    static class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                System.out.println("Hello Mythread!");
            }

        }
    }
    static class MyRun implements  Runnable{
        @Override
        public void run() {
            for(int i=0;i<10;i++)
                System.out.println("Hello Myrun!");
        }
    }
    @Test
    public void test(){
        new MyThread().start();
        new Thread(new MyRun()).start();
    }
}
