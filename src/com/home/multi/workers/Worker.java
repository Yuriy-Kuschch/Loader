package com.home.multi.workers;

import com.home.multi.Sand;

import java.util.List;

public abstract class Worker implements Runnable {
    public Sand sand;
    public String name;
    //Default value! Can be overload in MAIN
    public int productivity;
    public int velocity;
    public int capacity = 6;

    public abstract List<String> doTask() throws InterruptedException;


}
