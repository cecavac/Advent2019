package com.celecavac.advent2019;

public class Robot2 extends Robot {
    public Robot2(String program) {
        super(program);
    }

    protected void program() throws InterruptedException {
        sendInstruction("NOT E T\n");
        sendInstruction("NOT T T\n");
        sendInstruction("OR H T\n");

        sendInstruction("NOT C J\n");
        sendInstruction("NOT J J\n");
        sendInstruction("AND B J\n");
        sendInstruction("NOT J J\n");
        sendInstruction("AND T J\n");

        sendInstruction("NOT A T\n");
        sendInstruction("OR T J\n");

        sendInstruction("AND D J\n");

        sendInstruction("RUN\n");
    }
}
