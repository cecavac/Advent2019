package com.celecavac.advent2019;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        Bugs bugs = new Bugs(Inputs.Input1);
        bugs.run();
        textView1.setText(Long.toString(bugs.getResult()));

        RecursiveBugs recursiveBugs = new RecursiveBugs(Inputs.Input1);
        recursiveBugs.run();
        textView2.setText(Long.toString(recursiveBugs.getResult()));
    }
}
