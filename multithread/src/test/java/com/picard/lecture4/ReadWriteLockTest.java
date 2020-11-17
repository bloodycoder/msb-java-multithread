package com.picard.lecture4;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {
    static Lock lock = new ReentrantLock();
    private static int value;

    static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static Lock readLock = readWriteLock.readLock();
    static Lock writeLock = readWriteLock.writeLock();

    public static void read(Lock lock){
        try{
            lock.lock();
            Thread.sleep(1000);
            System.out.println("read over");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    public static void write(Lock lock,int v){
        try{
            lock.lock();
            Thread.sleep(1000);
            value = v;
            System.out.println("write over");
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
    @Test
    public void test1() throws IOException {
        /*
        Runnable readR = ()->read(lock);
        Runnable writeR = ()->write(lock,new Random().nextInt());
        */
        Runnable readR = ()->read(readLock);
        Runnable writeR = ()->write(writeLock,new Random().nextInt());
        for(int i=0;i<10;i++){
            new Thread(readR).start();
        }
        for(int i=0;i<2;i++){
            new Thread(writeR).start();
        }
    }
}
