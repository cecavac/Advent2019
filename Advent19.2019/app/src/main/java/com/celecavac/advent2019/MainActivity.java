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

        Beamer beamer = new Beamer();
        try {
            beamer.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        textView1.setText(Long.toString(beamer.getResult()));
        textView2.setText(Long.toString(beamer.getResult2()));
    }
}
