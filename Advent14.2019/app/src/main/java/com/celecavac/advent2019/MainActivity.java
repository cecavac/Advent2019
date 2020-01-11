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

        Rafinery rafinery = new Rafinery(Inputs.Input1);
        rafinery.reverseProduction(1);
        textView1.setText(Long.toString(rafinery.getResult()));
        rafinery.findTrilionOutput();
        textView2.setText(Long.toString(rafinery.getResult2()));
    }
}
