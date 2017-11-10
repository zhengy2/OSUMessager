package com.example.ramak.myapplication;

import android.app.TabActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import static android.view.View.GONE;

public class HomeActivity1 extends AppCompatActivity {
    private Button log_out;
    private Button scan;
    private String json;
    private TextView userId;

    private static final int CODE_GET_REQUEST = 1024;
    private static final int CODE_POST_REQUEST = 1025;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);

        Bundle param=getIntent().getExtras();
        final String user=(String) param.get("user");

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

        log_out = (Button)findViewById(R.id.logout);
        scan = (Button)findViewById(R.id.scan);
        userId = (TextView) findViewById(R.id.welcome);

        userId.setText("Welcome "+json);
        log_out.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                listLocations();
                //startActivity(new Intent(getApplicationContext(),MapLocationActivity.class).putExtra("user",user));
            }
        });
    }
    public void listLocations() {

        HashMap<String, String> params = new HashMap<>();
        params.put("userId", json);
        //Calling the create hero API
        HomeActivity1.PerformNetworkRequest request = new HomeActivity1.PerformNetworkRequest(Api.URL_LISTLOCATIONS_USER, params, CODE_POST_REQUEST);
        request.execute();
    }

    public void updateUserLocation(String latitude,String longitude) {


        HashMap<String, String> params = new HashMap<>();
        params.put("userId", json);
        params.put("latitude", latitude);
        params.put("longitude", longitude);

        params.put("userActive", "1");
        params.put("userStatus", "violet");
        //Calling the create hero API
        HomeActivity1.PerformNetworkRequest request = new HomeActivity1.PerformNetworkRequest(Api.URL_INSERTUSERLOCATION_USER, params, CODE_POST_REQUEST);
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
            //progressBar.setVisibility(View.VISIBLE);
        }


        //this method will give the response from the request
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //progressBar.setVisibility(GONE);
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
                    startActivity(new Intent(getApplicationContext(),MapLocationActivity.class).putExtra("user",json).putExtra("locations",object.getString("success")));
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

/*class homeActivity1 extends TabActivity{
    HomeActivity1 homeActivity1 = new HomeActivity1();

}*/




