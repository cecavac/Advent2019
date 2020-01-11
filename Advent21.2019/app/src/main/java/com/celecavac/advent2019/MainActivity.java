package com.celecavac.advent2019;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView1;
    private TextView textView2;
    private Robot robot;
    private Robot2 robot2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) findViewById(R.id.textView1);
        textView2 = (TextView) findViewById(R.id.textView2);

        robot = new Robot(Inputs.Input1);
        robot.run();
        textView1.setText(Long.toString(robot.getResult()));

        robot2 = new Robot2(Inputs.Input1);
        robot2.run();
        textView2.setText(Long.toString(robot2.getResult()));
    }
}
