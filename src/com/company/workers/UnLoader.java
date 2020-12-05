package com.company.workers;

import com.company.Sand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UnLoader extends Worker {
    private final Sand sand;

    public UnLoader(int productivity, Sand sand) {
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
        int lastAction = capacity/productivity;
        while (lastAction > 0) {
            int lastDependsOnProductivity = productivity;
            while (lastDependsOnProductivity > 0) {
                if (sand.getTransporterBag() == null || sand.getTransporterBag().size() == 0)
                    return Collections.EMPTY_LIST;

                String sandPiece = sand.getTransporterBag().get(0);
                sand.getSandHeapDestination().add(sandPiece);
                System.out.println(threadName+"UnLoader unload sand = " + sandPiece);
                sand.getTransporterBag().remove(sandPiece);
                lastDependsOnProductivity--;
            }
            lastAction--;
            Thread.sleep(1000);
        }
        System.out.format(threadName+"***UnLoader complete with their velocity %d! ", productivity);
        System.out.println();
        return Collections.EMPTY_LIST;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(name + " has started");
        try {

            synchronized (sand) {
                while (sand.getSandHeap().size() > 0) {
                    if (!sand.isCanUnLoading() && sand.getTransporterBag().size() == 0)
                        sand.wait();
                    if (!sand.isCanMoving() && sand.isCanUnLoading()) {
                        sand.setCanMoving(false);
                        doTask();
                        sand.setTransporterBag(new ArrayList<String>());
                        sand.setCanUnLoading(false);
                        sand.setCanLoading(true);
                        sand.setCanMoving(true);
                    }

//                    sand.notifyAll();
                    sand.notifyAll();
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(name + " has stopped!!!!!!!!!!!!!!!!!!!!!");
    }
}
