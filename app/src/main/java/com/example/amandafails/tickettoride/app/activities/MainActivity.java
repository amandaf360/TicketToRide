package com.example.amandafails.tickettoride.app.activities;

//import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.amandafails.tickettoride.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        if(getIntent().hasExtra("logout")) {
//            FragmentManager manager = getSupportFragmentManager();
//            LoginFragment loginFragment = new LoginFragment();
//            manager.beginTransaction()
//                    .replace(R.id.frame, loginFragment)
//                    .commit();
//        }
//        else if(getIntent().hasExtra("resync")) {
//            FragmentManager manager = getSupportFragmentManager();
//            MapFragment mapFragment = new MapFragment();
//            manager.beginTransaction()
//                    .replace(R.id.frame, mapFragment)
//                    .commit();
//        }
//        if (savedInstanceState == null) {
//            LoginFragment loginFragment = new LoginFragment();
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.frame, loginFragment)
//                    .commit();
//        }
    }

//    public void switchFragments() {
//        FragmentManager manager = getSupportFragmentManager();
//        MapFragment mapFragment = new MapFragment();
//        manager.beginTransaction()
//                .replace(R.id.frame, mapFragment)
//                .commit();
//    }

}
