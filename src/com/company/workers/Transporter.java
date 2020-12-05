package com.company.workers;

import com.company.Sand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Transporter extends Worker {
    private final Sand sand;

    public Transporter(int capacity, int velocity, Sand sand) {
        super();
        this.capacity = capacity;
        this.velocity = velocity;
        this.sand = sand;
        super.name = this.getClass().getName();
    }

    @Override
    public synchronized List<String> doTask() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " has started");
        threadName = threadName+" : ";

        System.out.format(threadName+"Transporter start moving trolley! (Trolley has %d of sand)", sand.getTransporterBag().size());
        System.out.println();

        Thread.sleep(5000);

        System.out.println(threadName+"***Transporter stop moving trolley! (UnLoader Can unload -- TODO notify only UnLoader)");
        /*synchronized (sand) {
            while (sand.getTransporterBag() != null && sand.getTransporterBag().size() > 0) {
                System.out.println(threadName+"Transporter is waiting! Transporter has count of sand = "+sand.getTransporterBag().size());
                sand.wait(1000);
            }
        }*/
        /*
        System.out.format(threadName+"Transporter start moving BACK! (Trolley has %d of sand)", sand.getTransporterBag().size());
        System.out.println();
        Thread.sleep(5000);
        System.out.println(threadName+"***Transporter come BACK!");*/
        return Collections.EMPTY_LIST;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " has started");
        try {
            synchronized (sand) {
                while (sand.getSandHeap().size() > 0) {
                    if (!sand.isCanMoving())
                        sand.wait();
                    doTask();
                    sand.setCanMoving(false);
                    sand.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " has stopped !!!!!!!!!!!!!!!!!!!!!!!");

    }
}
