package com.picard.lecture4;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest {
    @Test
    public void test(){
        /**
         * count down latch 测试
         * */
        Thread[] threads = new Thread[100];
        CountDownLatch latch = new CountDownLatch(threads.length);
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(()->{
                int result = 0;
                for(int j=0;j<10000;j++) result+=j;
                latch.countDown();
            });
            threads[i].start();
        }
        try{
            latch.await();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("everything is down");
    }
}
