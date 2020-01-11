package com.celecavac.advent2019;

import java.util.concurrent.BlockingQueue;

public class Node extends Executer {
    private enum OutputState {
        ADDRESS,
        X,
        Y
    }

    private Router router;
    private Packet packet;
    private OutputState outputState = OutputState.ADDRESS;

    public Node (String program, BlockingQueue userInput, Router router) {
        super(program, userInput, null);
        this.router = router;
    }

    protected void input(int parameter1) throws InterruptedException {
        if (userInput.size() == 0) {
            positions[parameter1] = -1L;
        } else {
            positions[parameter1] = (Long) userInput.take();
            router.pingNat();
        }
    }

    protected void output(int parameter1) throws InterruptedException {
        switch (outputState) {
            case ADDRESS:
                packet = new Packet(positions[parameter1]);
                outputState = OutputState.X;
                break;
            case X:
                packet.setX(positions[parameter1]);
                outputState = OutputState.Y;
                break;
            case Y:
                packet.setY(positions[parameter1]);
                outputState = OutputState.ADDRESS;

                if (packet.getAddress() == 255L && result == 0) {
                    //systemHalt = true;
                    result = positions[parameter1];
                }

                router.sendPacket(packet);

                break;
        }
    }
}
