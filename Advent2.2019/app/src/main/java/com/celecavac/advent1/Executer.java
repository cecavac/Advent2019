package com.celecavac.advent1;

public class Executer {
    private int result;
    private int []positions;
    private boolean errorEncountered = false;

    public Executer(String input, int spice1, int spice2) {
        String elements[] = input.split(",");
        positions = new int[elements.length];

        for (int i = 0; i < elements.length; i++)
            positions[i] = Integer.parseInt(elements[i]);

        positions [1] = spice1;
        positions [2] = spice2;
    }

    public void run() {
        try {
            program:
            for (int ip = 0; ; ip += 4) {
                int leftOperand = positions[ip + 1];
                int rightOperand = positions[ip + 2];
                int result = positions[ip + 3];

                switch (positions[ip]) {
                    case 1:
                        positions[result] = positions[leftOperand] + positions[rightOperand];
                        break;

                    case 2:
                        positions[result] = positions[leftOperand] * positions[rightOperand];
                        break;

                    case 99:
                        break program;
                    default:
                        /* Error */
                        positions[Integer.MAX_VALUE] = 0;
                }
            }
        } catch (Throwable t) {
            errorEncountered = true;
        }

        result = positions[0];
    }

    public int getIntResult() {
        return result;
    }

    public String getResult() {
        return Integer.toString(result);
    }

    public boolean isErrorEncountered() {
        return errorEncountered;
    }
}
