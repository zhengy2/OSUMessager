package com.example.ramak.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProfileViewActivity extends AppCompatActivity {
    private Button request;
    private Button accept;
    private Button favourite;
    private Button message;
    private String json;
    private TextView userId;
    private TextView userTitle;
    private String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);

        Bundle param=getIntent().getExtras();
        final String user=(String) param.get("user");
        title = (String) param.get("title");
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

        request = (Button)findViewById(R.id.request);
        accept = (Button)findViewById(R.id.accept);
        userId = (TextView) findViewById(R.id.welcome);
        userId.setText("Welcome "+json);

        userTitle = (TextView) findViewById(R.id.help);
        userTitle.setText("Welcome "+title);
        // add profile section
        request.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //database update
                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        accept.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //database update
                //startActivity(new Intent(getApplicationContext(),MapLocationActivity.class).putExtra("user",user));
            }
        });
    }
}
