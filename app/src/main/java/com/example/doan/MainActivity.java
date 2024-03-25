package com.example.doan;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton IBSearch;
    private BottomNavigationView bottomNav;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);

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
