package com.picard;

public class Instacnce {
    /**
     * 单例模式的最佳实践
     * */
    private static volatile Instacnce INSTANCE;
    private Instacnce(){}
    public static Instacnce getInstance(){
        if(INSTANCE == null){
            synchronized (Instacnce.class){
                if(INSTANCE == null){
                    INSTANCE = new Instacnce();
                }
            }
        }
        return INSTANCE;
    }
}
