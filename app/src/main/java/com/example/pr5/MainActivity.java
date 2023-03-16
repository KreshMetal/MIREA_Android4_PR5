package com.example.pr5;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.Navigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarMenu;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity
{
    private View navHost;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navHost = findViewById(R.id.nav_host_fragment);
        BottomNavigationView navBar = (BottomNavigationView) findViewById(R.id.nav_bar);
        navBar.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                if (item.getItemId() == R.id.bottom_nav_menu_home)
                {
                    Navigation.findNavController(navHost).navigate(R.id.action_global_home);
                }
                else
                {
                    Navigation.findNavController(navHost).navigate(R.id.action_global_profile);
                }
                return true;
            }
        });
    }
}