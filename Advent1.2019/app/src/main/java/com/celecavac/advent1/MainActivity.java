package com.celecavac.advent1;

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

        FuelCounter fuelCounter = new FuelCounter(Inputs.input1);
        textView1.setText(fuelCounter.getTotalFuel());

        AdjustedFuelCounter adjustedFuelCounter = new AdjustedFuelCounter(Inputs.input1);
        textView2.setText(adjustedFuelCounter.getTotalFuel());
    }
}
