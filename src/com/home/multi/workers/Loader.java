package com.home.multi.workers;

import com.home.multi.Sand;

import java.util.ArrayList;
import java.util.List;

public class Loader extends Worker {
    private final Sand sand;

    public Loader(int productivity, Sand sand) {
        super();
        this.productivity = productivity;
        this.sand = sand;
        super.name = this.getClass().getName();
    }

    @Override
    public synchronized List<String> doTask() throws InterruptedException {
        String threadName = Thread.currentThread().getName();
        System.out.println(threadName + " has started");
        threadName = threadName+" : ";
        ArrayList<String> sandWorked = new ArrayList<String>();
        int lastAction = capacity/productivity;
        while (lastAction > 0) {
            int lastDependsOnProductivity = productivity;
            while (lastDependsOnProductivity > 0) {
                String sandPoll = sand.getSandHeap().poll();
                if (sandPoll == null) {
                    System.out.println(threadName+"SandHeap is EMPTY! Loader cannot find more sand! Return count of sand = "+sandWorked.size());
                    return sandWorked;
                }
                System.out.format(threadName+"Loader peek sand %s after that in sandHeap left %d pack of sands", sandPoll, sand.getSandHeap().size());

                sandWorked.add(sandPoll);
//            System.out.format("Loader add piece of sand %s bucket", sandPeek);
                System.out.println();
                lastDependsOnProductivity--;
            }
            lastAction--;
            Thread.sleep(1000);
        }
        System.out.format(threadName+"***Loader complete with their velocity %d! And return %d pack of sand to trolley", productivity, sandWorked.size());
        System.out.println();
        return sandWorked;
    }

    @Override
    public void run() {

        try {

            synchronized (sand) {
                while (sand.getSandHeap().size() > 0) {
                    if (!sand.isCanLoading() && sand.getTransporterBag().size() > 0)
                        sand.wait();
                    if (!sand.isCanMoving() && sand.isCanLoading()) {
                        sand.setCanMoving(false);
                        List<String> sandWorked = doTask();
                        sand.setTransporterBag(new ArrayList<String>());
                        sand.getTransporterBag().addAll(sandWorked);
                        sand.setCanLoading(false);
                        sand.setCanUnLoading(true);
                        sand.setCanMoving(true);

                    }

                    sand.notifyAll();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " has stopped!!!!!!!!!!!!!!!!!!!!!!!!");

    }
}
