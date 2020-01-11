package com.celecavac.advent1;

public class OutputFinder {
    private String result;
    private String input;
    private int target;

    public OutputFinder(String input, String target) {
        this.input = input;
        this.target = Integer.parseInt(target);
    }

    public void find() {
        for (int i = 0; i <= 99; i++)
            for (int j = 0; j <= 99; j++){
                Executer executer;
                executer = new Executer(input, i, j);
                executer.run();

                if (executer.isErrorEncountered())
                    continue;

                if (executer.getIntResult() == target) {
                    result = "" + i + j;
                    return;
                }
            }
    }

    public String getResult() {
        return result;
    }
}
