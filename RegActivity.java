package com.example.ramak.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by ramak on 10/17/2017.
 */

public class RegActivity extends Activity {

    private Button cancel_button;//cancel button
    private Button submit_button;//submit button


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);



        submit_button = (Button) findViewById(R.id.submit_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });

    }
}
