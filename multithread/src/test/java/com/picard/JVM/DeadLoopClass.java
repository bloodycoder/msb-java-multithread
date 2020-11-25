package com.picard.JVM;
class DeadLoopTest{
    static{
        if(true){
            System.out.println(Thread.currentThread()+" init DeadLoop");
            while(true){

            }
        }
    }
}
public class DeadLoopClass {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread()+" start");
                DeadLoopTest deadLoopTest = new DeadLoopTest();
                System.out.println(Thread.currentThread()+" end");
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
    }


}
