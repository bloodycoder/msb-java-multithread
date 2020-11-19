package com.picard.lecture4;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class AQSReentrant {
    int a = 0;
    ReentrantLock lock = new ReentrantLock();
    @Test
    public void test(){
        lock.lock();
        try{
            a++;
        }finally {
            lock.unlock();
        }
    }
}
