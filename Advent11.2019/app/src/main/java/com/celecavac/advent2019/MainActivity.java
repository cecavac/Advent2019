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

        Robot robot = new Robot(0);
        try {
            robot.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textView1.setText(Long.toString(robot.getResult()));

        robot = new Robot(1);
        try {
            robot.run();
            robot.print();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textView2.setText("In logcat");
    }
}
