package com.picard.lecture5;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class InterruptTest {
    @Test
    public void test() throws InterruptedException {
        Thread sleepThread = new Thread(new SleepRunner(),"sleep");
        sleepThread.setDaemon(true);
        Thread busyThread = new Thread(new BusyRunner(),"BusyRunner");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        //
        TimeUnit.SECONDS.sleep(3);
        sleepThread.interrupt();
        busyThread.interrupt();
        TimeUnit.SECONDS.sleep(5);
        System.out.println("sleepThread interrupt is "+sleepThread.isInterrupted());
        System.out.println("busyThread interrupt is "+busyThread.isInterrupted());
        TimeUnit.SECONDS.sleep(1);
    }
    class SleepRunner implements Runnable{
        @Override
        public void run() {
            while(true){
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    class BusyRunner implements Runnable{
        @Override
        public void run() {
            while(true){
            }
        }
    }
}
