package com.celecavac.advent2019;

import android.util.Log;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Robot extends Thread {
    private Executer executer;
    BlockingQueue<Long> input;
    BlockingQueue<Long> output;
    HashMap<String, Cell> map = new HashMap<String, Cell>();
    LinkedList<String> backpack = new LinkedList<String>();
    LinkedList<Character> moves = new LinkedList<Character>();
    LinkedList<Character> securityPointMoves = new LinkedList<Character>();

    private int i = 0, j = 0;

    public Robot(String program) {
        input = new ArrayBlockingQueue<Long>(10000);
        output = new ArrayBlockingQueue<Long>(10000);
        executer = new Executer(program, input, output);
    }

    public void run() {
        executer.start();

        try {
            // explore all
            readInput(true, true);

            goToSecurityCheckPoint();
            breakIn();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Cell parseCell(String console) throws InterruptedException {
        String []lines = console.split("\\n");
        boolean readDoors = false, readItems = false;
        boolean north = false, south = false, west = false, east = false;
        String name = "", item = "";

        for(String line : lines) {
            if (line.contains("== ") && name.equals("")) {
                name = line;
            } else if (line.contains("Doors here lead:")){
                readDoors = true;
            } else if (line.contains("Items here:")){
                readItems = true;
            } else if (line.contains("- ")){
                if (readDoors) {
                    if (line.contains("north")) {
                        north = true;
                    } else if (line.contains("south")) {
                        south = true;
                    } else if (line.contains("west")) {
                        west = true;
                    } else if (line.contains("east")) {
                        east = true;
                    }
                }

                if (readItems) {
                    String []itemParse = line.split("- ");
                    item = itemParse[1];
                }
            } else {
                readDoors = false;
                readItems = false;
            }
        }

        return new Cell(name, north, south, west, east, item);
    }

    public String readInput(boolean processInput, boolean print) throws InterruptedException {
        String console = "";

        while (true) {
            long element = 0;
            try {
                element = output.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            char c = (char) element;
            if (console.endsWith("Command?") ||
                console.endsWith("the main airlock.\"")) {
                break;
            } else {
                console += c;
            }
        }

        if (processInput) {
            Cell cell = parseCell(console);

            if (!map.containsKey(cell.getName())) {
                map.put(cell.getName(), cell);
                if (!cell.getItem().equals(""))
                    take(cell.getItem());

                if (print) {
                    Log.e("#####", i + ", " + j + " " + console);
                }

                if (cell.getName().equals("== Security Checkpoint ==")) {
                    securityPointMoves.addAll(moves);
                } else {
                    exploreDirections(cell);
                }
            }
        } else {
            if (print) {
                Log.e("#####", i + ", " + j + " " + console);
            }
        }

        return console;
    }

    public void exploreDirections(Cell cell) throws InterruptedException {
        if (cell.getNorth()) {
            north();
            readInput(true, true);
            south();
            readInput(false, false);
        }

        if (cell.getSouth()) {
            south();
            readInput(true, true);
            north();
            readInput(false, false);
        }

        if (cell.getWest()) {
            west();
            readInput(true, true);
            east();
            readInput(false, false);
        }

        if (cell.getEast()) {
            east();
            readInput(true, true);
            west();
            readInput(false, false);
        }
    }

    public void north() throws InterruptedException {
        i--;

        input.put((long) 'n');
        input.put((long) 'o');
        input.put((long) 'r');
        input.put((long) 't');
        input.put((long) 'h');
        input.put((long) '\n');

        moves.add('n');
    }

    public void south() throws InterruptedException {
        i++;

        input.put((long) 's');
        input.put((long) 'o');
        input.put((long) 'u');
        input.put((long) 't');
        input.put((long) 'h');
        input.put((long) '\n');

        moves.add('s');
    }

    public void east() throws InterruptedException {
        j++;

        input.put((long) 'e');
        input.put((long) 'a');
        input.put((long) 's');
        input.put((long) 't');
        input.put((long) '\n');

        moves.add('e');
    }

    public void west() throws InterruptedException {
        j--;

        input.put((long) 'w');
        input.put((long) 'e');
        input.put((long) 's');
        input.put((long) 't');
        input.put((long) '\n');

        moves.add('w');
    }

    public void take(String item) throws InterruptedException {
        if (item.equals("infinite loop") ||
            item.equals("photons") ||
            item.equals("giant electromagnet") ||
            item.equals("molten lava") ||
            item.equals("escape pod"))
            return;

        input.put((long) 't');
        input.put((long) 'a');
        input.put((long) 'k');
        input.put((long) 'e');
        input.put((long) ' ');

        for (char c: item.toCharArray())
            input.put((long) c);

        input.put((long) '\n');
        backpack.add(item);

        readInput(false, false);
    }

    public void drop(String item) throws InterruptedException {
        input.put((long) 'd');
        input.put((long) 'r');
        input.put((long) 'o');
        input.put((long) 'p');
        input.put((long) ' ');

        for (char c: item.toCharArray())
            input.put((long) c);

        input.put((long) '\n');
        readInput(false, false);
    }

    public void goToSecurityCheckPoint() throws InterruptedException {
        for (Character c: securityPointMoves) {
            switch (c) {
                case 'n':
                    north();
                    break;
                case 's':
                    south();
                    break;
                case 'w':
                    west();
                    break;
                case 'e':
                    east();
                    break;
            }

            readInput(false, false);
        }
    }

    public void breakIn() throws InterruptedException {
        int combinations = 1 << backpack.size();
        for (int i = 0; i < combinations; i++) {
            dropAll();
            take(i);
            north();

            String output = readInput(false, false);
            if (!output.contains("heavier than") && !output.contains("lighter than") ) {
                Log.e("Password", output);
                String []split1 = output.split(" typing ");
                String []split2 = split1[1].split(" ");
                Executer.setResult(Long.parseLong(split2[0]));
                Executer.systemHalt = true;
                break;
            }
        }
    }

    public void dropAll() throws InterruptedException {
        for (String item : backpack)
            drop(item);
    }

    public void take(int items) throws InterruptedException {
        int index = 0;
        while (items != 0) {
            if ((items & 1) == 1) {
                take(backpack.get(index));
            }

            index++;
            items >>= 1;
        }
    }
}
