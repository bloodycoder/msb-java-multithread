package com.picard.lecture4;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock03 {
    Lock lock = new ReentrantLock();

    @Test
    public void test() throws IOException {
        Thread t1 = new Thread(()->{
            try{
                lock.lock();
                System.out.println("t1 start");
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
                System.out.println("t1 end");
            }catch (InterruptedException e){
                System.out.println("interupted");
            }finally {
                lock.unlock();
            }
        });
        Thread t2 = new Thread(()->{
            try{
                lock.lockInterruptibly();
                System.out.println("t2 start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("t2 end");
            }catch (InterruptedException e){
                System.out.println("interupted");
            }finally {
                lock.unlock();
            }
        });
        t1.start();
        t2.start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        t2.interrupt();
    }
}
