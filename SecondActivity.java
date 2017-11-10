package com.example.ramak.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zy on 2017/10/14.
 */

public class SecondActivity extends Activity {
    private ImageButton log_out;
    public String json;
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.succ_login);
        Bundle param=getIntent().getExtras();
        String user=(String) param.get("user");

        try {

            //funcions per a cridar el string amb JSON i convertir-lo de nou a JSON
            JSONArray jsas = new JSONArray(user);
            for (int i =0; i < jsas.length(); i++)
            {
                JSONObject message = jsas.getJSONObject(i);
                if (message.getString("title").equals("userId")){
                    json = message.getString("value");
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        TextView tv = ((TextView)findViewById(R.id.textView2));
        tv.setText(json);
        log_out = (ImageButton)findViewById(R.id.logout);
        log_out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
    }
}
