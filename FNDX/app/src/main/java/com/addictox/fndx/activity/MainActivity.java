package com.addictox.fndx.activity;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.addictox.fndx.R;
import com.addictox.fndx.fragment.CheckNewsFragment;
import com.addictox.fndx.fragment.HomeFragment;
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
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, homeFragment).commit();
        Objects.requireNonNull(getSupportActionBar()).setTitle("News");
        navigationView.setSelectedItemId(R.id.action_home);
        navigationView.setOnNavigationItemSelectedListener(this);
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

        switch (item.getItemId()) {
            case R.id.action_home: {
                HomeFragment homeFragment = new HomeFragment();
                fragmentTransaction.replace(R.id.frame, homeFragment).commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("News");
                break;
            }
            case R.id.action_check: {
                CheckNewsFragment checkNewsFragment = new CheckNewsFragment();
                fragmentTransaction.replace(R.id.frame, checkNewsFragment).commit();
                Objects.requireNonNull(getSupportActionBar()).setTitle("Check Authenticity");
                break;
            }
        }
        return true;
    }
}