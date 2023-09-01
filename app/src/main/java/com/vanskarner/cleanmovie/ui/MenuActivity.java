package com.vanskarner.cleanmovie.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.vanskarner.cleanmovie.R;

import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_activity);
        BottomNavigationView bottomNavigation = findViewById(R.id.bottomMenu);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_container);
        NavController navController = Objects.requireNonNull(navHostFragment).getNavController();
        NavigationUI.setupWithNavController(bottomNavigation, navController);
    }

}