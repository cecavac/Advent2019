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

        Game game = new Game(Inputs.Input1);
        game.run();
        textView1.setText(Long.toString(game.getResult()));

        game = new Game(Inputs.Input2);
        game.run2();
        textView2.setText(Long.toString(game.getResult2()));
    }
}
