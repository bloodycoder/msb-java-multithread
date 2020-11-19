package com.picard.lecture5;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class TwinsLock implements Lock {
    /*
    * 一个熟悉AQS的测试类
    * */
    private final Sync sync = new Sync(2);
    private static final class Sync extends AbstractQueuedSynchronizer{
        Sync(int count){
            if(count <=0){
                throw new IllegalArgumentException("count must > 0");
            }
            setState(count);
        }
        public int tryAcquireShared(int reduceCount){
            for(;;){
                int current = getState();
                int newCount = current - reduceCount;
                if(newCount<0 || compareAndSetState(current,newCount)){
                    return newCount;
                }
            }
        }
        public boolean tryReleaseShared(int returnCount){
            for(;;){
                int current = getState();
                int newCount = current + returnCount;
                if(compareAndSetState(current,newCount)){
                    return true;
                }
            }
        }
    }
    @Override
    public void lock() {
        sync.acquireShared(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public void unlock() {
        sync.releaseShared(1);
    }

    @Override
    public Condition newCondition() {
        return null;
    }
    @Test
    public void test() throws IOException, InterruptedException {
        // test code
        final Lock lock = new TwinsLock();
        Runnable myrun = new Runnable() {
            @Override
            public void run() {
                while(true){
                    lock.lock();
                    try{
                        TimeUnit.SECONDS.sleep(1);
                        System.out.println(Thread.currentThread().getName());
                        TimeUnit.SECONDS.sleep(1);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                        return;
                    }finally {
                        lock.unlock();
                    }
                }
            }
        };
        Thread[] mythreads = new Thread[10];
        for(int i=0;i<10;i++){
            mythreads[i]= new Thread(myrun);
            mythreads[i].setDaemon(true);
            mythreads[i].start();
        }
        for(int i=0;i<10;i++){
            TimeUnit.SECONDS.sleep(1);
            mythreads[i].interrupt();
        }
        System.in.read();
    }
}