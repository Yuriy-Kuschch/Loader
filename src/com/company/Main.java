package com.company;

import com.company.workers.Loader;
import com.company.workers.Transporter;
import com.company.workers.UnLoader;
import com.company.workers.Worker;


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
