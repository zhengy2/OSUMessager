package com.example.ramak.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.view.View.GONE;

/**
 * Created by zy on 2017/10/14.
 */

public class RegisterActivity extends Activity {

    private Button cancel_button;//cancel button
    private Button submit_button;//submit button
    private EditText editTextUserId;
    private EditText editTextPassword;
    private EditText editTextPasswordVer;
    private EditText editTextEmail;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextSkills;
    private EditText editTextPhone;
    private EditText editTextMajor;
    private EditText editTextYear;
    private ProgressBar progressBar;
    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        editTextUserId = (EditText)findViewById(R.id.editTextUserId);
        editTextPassword = (EditText)findViewById(R.id.editTextPassword);
        editTextPasswordVer = (EditText)findViewById(R.id.editTextPasswordVer);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextFirstName = (EditText)findViewById(R.id.editTextFirstName);
        editTextLastName = (EditText)findViewById(R.id.editTextLastName);
        editTextSkills = (EditText)findViewById(R.id.editTextSkills);
        editTextPhone =(EditText)findViewById(R.id.editTextPhone);
        editTextMajor = (EditText)findViewById(R.id.editTextMajor);
        editTextYear = (EditText)findViewById(R.id.editTextYear);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        submit_button = (Button) findViewById(R.id.submit_button);
        cancel_button = (Button) findViewById(R.id.cancel_button);

        cancel_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                regUser();
            }
        });

    }
    private void regUser() {
        String userId = editTextUserId.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String passwordVer = editTextPasswordVer.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String major = editTextMajor.getText().toString().trim();
        String year = editTextYear.getText().toString().trim();
        String skills = editTextSkills.getText().toString().trim();

        //validating the inputs
        if (TextUtils.isEmpty(userId)) {
            editTextUserId.setError("Please enter userId");
            editTextUserId.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return;
        }


        if (password.equals(passwordVer)){}
        else{
            editTextPasswordVer.setError("Please Confirm again");
            editTextPasswordVer.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(email)) {
            editTextEmail.setError("Please enter email");
            editTextEmail.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(firstName)) {
            editTextFirstName.setError("Please enter first name");
            editTextFirstName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(lastName)) {
            editTextLastName.setError("Please enter last name");
            editTextLastName.requestFocus();
            return;
        }
        //if validation passes

        HashMap<String, String> params = new HashMap<>();
        params.put("userId", userId);
        params.put("userPassword", password);
        params.put("userFirstName", firstName);
        params.put("userLastName", lastName);
        params.put("userEmail", email);
        params.put("userSkills", skills);
        params.put("userMajor", major);
        params.put("userPhone", phone);
        params.put("studyYear", year);

        //Calling the create hero API
        RegisterActivity.PerformNetworkRequest request = new RegisterActivity.PerformNetworkRequest(Api.URL_REGISTER_USER, params, CODE_POST_REQUEST);
        request.execute();
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
                if (object.names().get(0).equals("success")){
                    Toast.makeText(getApplicationContext(),"SUCCESS "+object.getString("success"), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
                else{
                    Toast.makeText(getApplicationContext(),"ERROR "+object.getString("error"),Toast.LENGTH_SHORT).show();
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

