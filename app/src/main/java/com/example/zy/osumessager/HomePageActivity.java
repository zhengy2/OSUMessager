package com.example.zy.osumessager;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

import android.support.v4.app.FragmentActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by zy on 2017/10/14.
 */

public class HomePageActivity extends FragmentActivity implements OnMapReadyCallback {

    private TabHost tabHost;

    private GoogleMap mMap;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        tabHost = (TabHost) findViewById(android.R.id.tabhost);
        tabHost.setup();
        LayoutInflater inflater = getLayoutInflater().from(this);
        inflater.inflate(R.layout.tab1, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab2, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab3, tabHost.getTabContentView());
        inflater.inflate(R.layout.tab4, tabHost.getTabContentView());

        tabHost.addTab(tabHost.newTabSpec("tab01")
                .setIndicator("", this.getResources().getDrawable(R.drawable.home))
                .setContent(R.id.linearLayout01));

        tabHost.addTab(tabHost.newTabSpec("tab02")
                .setIndicator("", this.getResources().getDrawable(R.drawable.search))
                .setContent(R.id.linearLayout02));

        tabHost.addTab(tabHost.newTabSpec("tab03")
                .setIndicator("", this.getResources().getDrawable(R.drawable.information))
                .setContent(R.id.linearLayout03));

        tabHost.addTab(tabHost.newTabSpec("tab04")
                .setIndicator("", this.getResources().getDrawable(R.drawable.setting))
                .setContent(R.id.linearLayout04));

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Corvallis, US, and move the camera.
        LatLng Corvallis = new LatLng(44.564568, -123.262047);
        mMap.addMarker(new MarkerOptions().position(Corvallis).title("Marker in Corvallis"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(Corvallis));
    }
}
