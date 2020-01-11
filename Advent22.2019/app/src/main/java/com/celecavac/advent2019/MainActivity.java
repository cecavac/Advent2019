package com.celecavac.advent2019;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

        Dealer deler = new Dealer(10007, Inputs.Input1, 2019);
        textView1.setText(Long.toString(deler.getResult()));

        ReverseDealer reverseDealer1 = new ReverseDealer(119315717514047L, Inputs.Input1, 2020);
        ReverseDealer reverseDealer2 = new ReverseDealer(119315717514047L, Inputs.Input1, 2021);
        Resulter resulter = new Resulter(reverseDealer1.getResult(), reverseDealer2.getResult(),
                119315717514047L, 101741582076661L, 2020L);
        textView2.setText(Long.toString(resulter.getResult()));
    }
}
