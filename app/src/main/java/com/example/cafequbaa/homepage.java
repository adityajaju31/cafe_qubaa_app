package com.example.cafequbaa;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import com.example.cafequbaa.ui.account.accountFragment;
import com.example.cafequbaa.ui.home.HomeFragment;
import com.example.cafequbaa.ui.menulist.MenulistFragment;
import com.example.cafequbaa.ui.tablebooking.TablebookingFragment;
import com.google.android.material.navigation.NavigationView;

public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public void aboutUs(View view) {
        Intent intent = new Intent(this, aboutUs.class);
        startActivity(intent);
    }

    DrawerLayout drawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        toolbar.setNavigationIcon(R.drawable.ic_format_align_justify_black_24dp);
        navigationView.setNavigationItemSelectedListener(this);
       /* ActionBarDrawerToggle toggle =
                new ActionBarDrawerToggle(
                        homepage.this,
                        drawer,
                        toolbar,
                        R.string.navigation_drawer_open,
                        R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();*/
        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                                                                        R.string.navigation_drawer_open,
                                                                        R.string.navigation_drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawer.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
       /* mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_menulist, R.id.nav_tablebooking,
                R.id.nav_account,R.id.nav_logout)
                .setDrawerLayout(drawer)
                .build();*/
       /* NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/
        HomeFragment        homeFragment = new HomeFragment();
        FragmentTransaction ft           = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frmid, homeFragment);
        ft.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.homepage, menu);
        return true;
    }

  /*  @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }*/

    @SuppressLint("WrongConstant")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.nav_home) {
            HomeFragment        homeFragment = new HomeFragment();
            FragmentTransaction ft           = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frmid, homeFragment);
            ft.addToBackStack("hello");
            ft.commit();
        } else if (menuItem.getItemId() == R.id.nav_menulist) {
            MenulistFragment    menulistFragment = new MenulistFragment();
            FragmentTransaction ft               = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frmid, menulistFragment);
            ft.addToBackStack("hello");
            ft.commit();
        } else if (menuItem.getItemId() == R.id.nav_tablebooking) {
            TablebookingFragment tablebookingFragment = new TablebookingFragment();
            FragmentTransaction  ft                   = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frmid, tablebookingFragment);
            ft.addToBackStack("hello");
            ft.commit();
        } else if (menuItem.getItemId() == R.id.nav_account) {
            Youraccount     fragment = new Youraccount();
            FragmentTransaction ft       = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frmid, fragment);
            ft.addToBackStack("hello");
            ft.commit();
        }
        else if(menuItem.getItemId()==R.id.nav_logout)
        {
            startActivity(new Intent(getApplicationContext(), signInpage.class));
            finish();
        }
        drawer.closeDrawers();
        return false;
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>0)
        {
            getSupportFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }
    }
}