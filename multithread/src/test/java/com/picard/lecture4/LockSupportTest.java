package com.picard.lecture4;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    @Test
    public void test() throws InterruptedException {
        Thread t = new Thread(()->{
            for(int i=0;i<10;i++){
                System.out.println(i);
                if(i == 5){
                    LockSupport.park();
                }
                try{
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        t.start();
        TimeUnit.SECONDS.sleep(8);
        System.out.println("8 seconds later");
        LockSupport.unpark(t);
        t.join();
    }
}
