package com.home.multi;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Sand {
    private static List<String> transporterBag = new ArrayList<String>(6);
    private static Queue<String> sandHeapDestination = new ArrayDeque<String>();
    private static Queue<String> sandHeap = fillSandHeap(10);
    private boolean canLoading = true;
    private boolean canUnLoading;
    private boolean isCanMoving;

    public boolean isCanMoving() {
        return isCanMoving;
    }

    public void setCanMoving(boolean canMoving) {
        isCanMoving = canMoving;
    }

    public boolean isCanLoading() {
        return canLoading;
    }

    public void setCanLoading(boolean canLoading) {
        this.canLoading = canLoading;
    }

    public boolean isCanUnLoading() {
        return canUnLoading;
    }

    public void setCanUnLoading(boolean canUnLoading) {
        this.canUnLoading = canUnLoading;
    }

    public Queue<String> getSandHeap() {
        return sandHeap;
    }

    public Queue<String> getSandHeapDestination() {
        return sandHeapDestination;
    }
    //You cannot SET! Only ADD to collection a new Element!
    private void setSandHeapDestination(Queue<String> sandHeapDestination) {
        this.sandHeapDestination = sandHeapDestination;
    }
    //You cannot SET! Only ADD to collection a new Element!
    public void setSandHeap(Queue<String> sandHeap) {
        this.sandHeap = sandHeap;
    }


    public List<String> getTransporterBag() {
        return transporterBag;
    }

    public void setTransporterBag(List<String> transporterBag) {
        this.transporterBag = transporterBag;
    }

    private static Queue<String> fillSandHeap(int capacity) {
        Queue<String> queue = new ArrayDeque<String>(capacity);
        for (int i = 0; i < capacity; i++) {
            queue.add("SandPackage_" + i);
        }

        return queue;
    }
}
