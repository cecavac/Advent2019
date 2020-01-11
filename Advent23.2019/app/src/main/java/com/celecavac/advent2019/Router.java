package com.celecavac.advent2019;

import android.util.Log;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Router {
    private BlockingQueue []outputQueues;
    private Semaphore []locks;
    private Nat nat;

    public Router (BlockingQueue []outputQueues) {
        this.outputQueues = outputQueues;
        locks = new Semaphore[outputQueues.length];

        for (int i = 0; i < outputQueues.length; i++)
            locks[i] = new Semaphore(1);

        nat = new Nat(outputQueues[0], this);
    }

    public void startNat() {
        nat.start();
    }

    public void pingNat() throws InterruptedException {
        nat.ping();
    }

    public void sendPacket(Packet packet) throws InterruptedException {
        long address = packet.getAddress();

        if (address >= 0 && address < outputQueues.length) {
            locks[(int) address].acquire();
            outputQueues[(int) address].put(packet.getX());
            outputQueues[(int) address].put(packet.getY());
            locks[(int) address].release();
        } else if (address == 255L ) {
            nat.setPacket(packet);
        } else {
            Log.e("Router", "Bad address: " + address);
        }
    }
}
