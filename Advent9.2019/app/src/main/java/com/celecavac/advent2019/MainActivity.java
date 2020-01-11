package com.celecavac.advent2019;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        BlockingQueue inputQ = new ArrayBlockingQueue<Long>(10000);
        inputQ.add((long) 1);
        BlockingQueue outputQ = new ArrayBlockingQueue<Long>(10000);
        Executer executer = new Executer(Inputs.Input1, inputQ, outputQ);
        executer.run();
        textView1.setText(Long.toString(executer.getResult()));

        inputQ = new ArrayBlockingQueue<Long>(10000);
        inputQ.add((long) 2);
        outputQ = new ArrayBlockingQueue<Long>(10000);
        executer = new Executer(Inputs.Input1, inputQ, outputQ);
        executer.run();
        textView2.setText(Long.toString(executer.getResult()));
    }
}
