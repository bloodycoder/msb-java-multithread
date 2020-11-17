package com.picard.lecture4;

import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
    @Test
    public void test() throws IOException {
        Semaphore s = new Semaphore(2,true);
        new Thread(()->{
           try{
               s.acquire();
               System.out.println("T1 running ...");
               Thread.sleep(200);
               System.out.println("T1 running ...");
           }catch (InterruptedException e){
               e.printStackTrace();
           }finally {
               s.release();
           }
        }).start();
        new Thread(()->{
            try{
                s.acquire();
                System.out.println("T2 running ...");
                Thread.sleep(200);
                System.out.println("T2 running ...");
            }catch (InterruptedException e){
                e.printStackTrace();
            }finally {
                s.release();
            }
        }).start();
        System.in.read();
    }
}
