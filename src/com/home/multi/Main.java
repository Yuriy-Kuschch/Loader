package com.home.multi;

import com.home.multi.workers.Loader;
import com.home.multi.workers.Transporter;
import com.home.multi.workers.UnLoader;
import com.home.multi.workers.Worker;


import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

    public static void main(String[] args) throws InterruptedException {
	// write your code here
//        Queue<String> sandHeap = fillSandHeap(10);
        Sand sand = new Sand();
        Worker loader = new Loader( 3, sand);
        Worker transporter = new Transporter(6, 3, sand);
        UnLoader unLoader = new UnLoader(2, sand);

        new Thread(loader, "loader").start();
        new Thread(transporter, "transporter").start();
        new Thread(unLoader,"unLoader").start();
    }


}
