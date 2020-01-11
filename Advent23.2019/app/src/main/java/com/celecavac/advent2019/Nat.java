package com.celecavac.advent2019;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Nat extends Thread {
    private boolean networkIdle;
    private Packet natPacket;
    private Packet oldNatPacket;
    private Semaphore lock = new Semaphore(1);
    private BlockingQueue defaultOutput;
    private Router router;

    public Nat(BlockingQueue defaultOutput, Router router) {
        this.defaultOutput = defaultOutput;
        this.router = router;
    }

    public void ping() throws InterruptedException {
        lock.acquire();
        networkIdle = false;
        lock.release();
    }

    public void setPacket(Packet packet) throws InterruptedException {
        lock.acquire();
        natPacket = packet;
        natPacket.setAddress(0);
        lock.release();
    }

    public void run() {
        while (!Executer.systemHalt) {
            if (networkIdle && natPacket != null) {
                try {
                    router.sendPacket(natPacket);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (oldNatPacket != null &&
                        oldNatPacket.getY() == natPacket.getY()) {
                    Executer.systemHalt = true;
                    Executer.setResult2(natPacket.getY());
                }

                oldNatPacket = natPacket;
            }

            try {
                lock.acquire();
                networkIdle = true;
                lock.release();

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
