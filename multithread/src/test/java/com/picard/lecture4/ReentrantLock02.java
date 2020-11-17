package com.picard.lecture4;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLock02 {
    Lock lock = new ReentrantLock();
    void m1(){
        try{
            lock.lock();
            for(int i=0;i<10;i++){
                TimeUnit.SECONDS.sleep(1);
                System.out.println(i);
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();  //务必记得解锁
        }
    }
    void m2(){
        try{
            lock.lock();
            System.out.println("m2 .....");
        }finally {
            lock.unlock();
        }
    }
    @Test
    public void test() throws IOException {
        ReentrantLock02 r1 = new ReentrantLock02();
        new Thread(r1::m1).start();
        try{
            TimeUnit.SECONDS.sleep(1);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        new Thread(r1::m2).start();
        System.in.readNBytes(4);
    }
}
