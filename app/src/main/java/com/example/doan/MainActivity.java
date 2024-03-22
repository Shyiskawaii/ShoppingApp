package com.example.doan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton IBSearch;
    private BottomNavigationView bottomNav;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.nav_view);
        // Display the default fragment
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.fragment_container, new HomeFragment())
//                .commit();

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation item clicks here
                switch (item.getItemId()) {
                    case R.id.nav_Panel:
                        // Handle home action
                        break;
                    case R.id.nav_User:
                        // Handle gallery action
                        break;
                    case R.id.nav_Customer:
                        // Handle slideshow action
                        break;
                }
                // Close the drawer after handling click
                drawerLayout.closeDrawers();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_sp,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private BottomNavigationView.OnItemSelectedListener navListener =
            new BottomNavigationView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//                    Fragment selectedFragment = null;
//
//                    switch (item.getItemId()) {
//                        case R.id.nav_home:
//                            selectedFragment = new HomeFragment();
//                            break;
//                        case R.id.nav_category:
//                            selectedFragment = new CategoryFragment();
//                            break;
//                        case R.id.nav_search:
//                            selectedFragment = new SearchFragment();
//                            break;
//                        case R.id.nav_cart:
//                            selectedFragment = new CartFragment();
//                            break;
//                        case R.id.nav_user:
//                            selectedFragment = new UserFragment();
//                            break;
//                    }
//
//                    getSupportFragmentManager().beginTransaction()
//                            .replace(R.id.fragment_container, selectedFragment)
//                            .commit();

                    return true;
                }
            };


    public void checkLoginStatus() {
        // Check shared preferences to see if there's a logged-in user
        SharedPreferences preferences = getSharedPreferences("user_session", Context.MODE_PRIVATE);
        String loggedInUser = preferences.getString("logged_in_user", null);
        if (loggedInUser != null) {
            // User is already logged in, customize app experience accordingly
            //customizeAppForLoggedInUser(loggedInUser);
        } else {
            // No user is logged in, show login screen
            //navigateToLoginScreen();
        }
    }
}