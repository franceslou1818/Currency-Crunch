package com.example.android.currencycrunch;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_phrases:
                    PhrasesFragment phrasesFragment = new PhrasesFragment();
                    FragmentManager phrasesFragmentManager = getSupportFragmentManager();
                    phrasesFragmentManager.beginTransaction().replace(R.id.fragment_content, phrasesFragment).commit();
                    return true;
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    FragmentManager homeFragmentManager = getSupportFragmentManager();
                    homeFragmentManager.beginTransaction().replace(R.id.fragment_content, homeFragment).commit();
                    return true;
                case R.id.navigation_convert:
                    ConvertFragment convertFragment = new ConvertFragment();
                    FragmentManager convertFragmentManager = getSupportFragmentManager();
                    convertFragmentManager.beginTransaction().replace(R.id.fragment_content, convertFragment).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Preferences pref = new Preferences(this);

        ImageView myImage = (ImageView) findViewById(R.id.imageViewBg);
        myImage.setAlpha(0.2f); //value: [0-255]. Where 0 is fully transparent and 255 is fully opaque.
    }

}



