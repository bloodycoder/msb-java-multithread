package com.picard.lecture3;

import org.junit.Test;

public class T03_SleepYield {
    static class MyThread extends Thread{
        @Override
        public void run() {
            for(int i=0;i<10;i++){
                System.out.println("Hello Mythread!");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    @Test
    public void testYield() throws InterruptedException {
        Thread t1 = new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("A"+i);
                if(i%10==0)Thread.yield();
            }
        });
        t1.start();
        Thread t2 = new Thread(()->{
            for(int i=0;i<100;i++){
                System.out.println("---------B"+i);
                if(i%10 == 0)Thread.yield();
            }
        });
        t2.start();
        t1.join();
        t2.join();
    }
    @Test
    public void testState(){
        Thread t = new MyThread();
        System.out.println(t.getState());
        t.start();
        System.out.println(t.getState());
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
    }
    
}
