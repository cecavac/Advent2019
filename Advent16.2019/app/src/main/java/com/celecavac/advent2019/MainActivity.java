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

        Decoder decoder = new Decoder(Inputs.Input1);
        decoder.run();
        textView1.setText(decoder.getResult());
        Decoder decoder2 = new Decoder(Inputs.Input1);
        decoder2.runReal();
        textView2.setText(decoder2.getResult());
    }
}
