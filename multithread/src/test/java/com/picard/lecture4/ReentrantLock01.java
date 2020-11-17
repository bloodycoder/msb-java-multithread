package com.picard.lecture4;

import org.junit.Test;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class ReentrantLock01 {
    synchronized void m1(){
        for(int i=0;i<10;i++){
            try{
                TimeUnit.SECONDS.sleep(1);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            System.out.println(i);
        }
    }
    synchronized void m2(){
        System.out.println("m2 .....");
    }
    @Test
    public void test() throws IOException {
        ReentrantLock01 r1 = new ReentrantLock01();
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
