package com.celecavac.advent2019;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Network {
    private Node []nodes;
    private Router router;

    public Network(String program, int nodeCount) throws InterruptedException {
        BlockingQueue<Long> []inputQueues = new BlockingQueue[nodeCount];
        nodes = new Node[nodeCount];

        for (int i = 0; i < inputQueues.length; i++) {
            inputQueues[i] = new ArrayBlockingQueue<Long>(10000);
            // Device address
            inputQueues[i].put((long) i);
        }

        router = new Router(inputQueues);

        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node(program, inputQueues[i], router);
    }

    public void run() throws InterruptedException {
        for (int i = 0; i < nodes.length; i++)
            nodes[i].start();

        router.startNat();

        for (int i = 0; i < nodes.length; i++)
            nodes[i].join();
    }
}
