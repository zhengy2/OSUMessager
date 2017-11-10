package com.example.ramak.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;


public class MainActivity extends Activity {

    private Button submit_button;//login button
    private Button register_button;//register button
    private EditText editTextUserId;
    private EditText editTextPassword;
    private ProgressBar progressBar;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;
    private TextView textView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello);
        initComponent();

        submit_button = (Button)findViewById(R.id.submit_button);
        register_button = (Button)findViewById(R.id.register_button);
        editTextUserId = (EditText)findViewById(R.id.editTextUserId);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                valUser();

            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
    public void valUser() {

        String username = getUserNmae();
        if (username.isEmpty()){
            showError("hello");
        }
        String userId = editTextUserId.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        //validating the inputs
        if (TextUtils.isEmpty(userId)) {
            editTextUserId.setError("Please enter name");
            editTextUserId.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter real name");
            editTextPassword.requestFocus();
            return;
        }
        //if validation passes

        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("password", password);

        //Calling the create hero API
        PerformNetworkRequest request = new PerformNetworkRequest(Api.URL_VALIDATE_USER, params, CODE_POST_REQUEST);
        request.execute();
    }

    private void initComponent(){
        textView = (TextView) findViewById(R.id.welcome);
    }


    public String getUserNmae() {
        return "hello";
    }


    public void showError(String s) {
        editTextUserId.setError(s);
    }

    //inner class to perform network request extending an AsyncTask
    private class PerformNetworkRequest extends AsyncTask<Void, Void, String> {

        //the url where we need to send the request
        String url;

        //the parameters
        HashMap<String, String> params;

        //the request code to define whether it is a GET or POST
        int requestCode;

        //constructor to initialize values
        PerformNetworkRequest(String url, HashMap<String, String> params, int requestCode) {
            this.url = url;
            this.params = params;
            this.requestCode = requestCode;
        }

        //when the task started displaying a progressbar
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }


        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(GONE);
            try {
                JSONObject object = new JSONObject(s);
                Log.d("here1",object.toString());
                /*if (!object.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    //refreshing the herolist after every operation
                    //so we get an updated list
                    //we will create this method right now it is commented
                    //because we haven't created it yet
                    //refreshHeroList(object.getJSONArray("heroes"));
                }*/

                if (object.names().get(0).equals("success")){
                    Toast.makeText(getApplicationContext(),"SUCCESS", Toast.LENGTH_SHORT).show();
                    Log.d("output",object.getString("success"));
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class).putExtra("user",object.getString("success")));
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR"+object.getString("error"),Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        //the network operation will be performed in background
        @Override
        protected String doInBackground(Void... voids) {
            RequestHandler requestHandler = new RequestHandler();

            if (requestCode == CODE_POST_REQUEST)
                return requestHandler.sendPostRequest(url, params);


            if (requestCode == CODE_GET_REQUEST)
                return requestHandler.sendGetRequest(url);

            return null;
        }
    }
}