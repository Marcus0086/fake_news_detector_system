package com.addictox.fndx.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.addictox.fndx.R;
import com.addictox.fndx.fragment.CheckNewsFragment;
import com.addictox.fndx.fragment.HomeFragment;
import com.addictox.fndx.fragment.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private MenuItem previousMenuItem;
    private SharedPreferences sharedPreferences;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setupToolbar();
        displayHome();
    }

    private void init() {
        toolbar = findViewById(R.id.toolbar);
        navigationView = findViewById(R.id.navigation);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("");
    }

    private void displayHome() {
        HomeFragment homeFragment = new HomeFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame, homeFragment);
        fragmentTransaction.commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle("News");
        navigationView.setSelectedItemId(R.id.action_home);
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (previousMenuItem != null) {
            previousMenuItem.setChecked(false);
        }
        item.setCheckable(true);
        item.setChecked(true);
        previousMenuItem = item;

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        switch (item.getItemId()){
            case R.id.action_home:{
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.frame, homeFragment);
                fragmentTransaction.commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("News");
                break;
            }
            case R.id.action_check:{
                CheckNewsFragment checkNewsFragment = new CheckNewsFragment();
                fragmentTransaction.replace(R.id.frame, checkNewsFragment);
                fragmentTransaction.commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Fake News Detector");
                break;
            }
            case R.id.action_profile:{
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.frame, profileFragment);
                fragmentTransaction.commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("AddictoX");
                break;
            }
        }
        return true;
    }
}