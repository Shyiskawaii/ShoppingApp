package com.example.doan;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.fragment.app.Fragment;

import com.example.doan.home.CartFragment;
import com.example.doan.home.CategoryFragment;
import com.example.doan.home.HomeFragment;
import com.example.doan.home.SearchFragment;
import com.example.doan.home.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    ImageButton IBSearch;

    private BottomNavigationView bottomNav;

    private SharedPreferences sharedPreferences;

    String KEY_SECTION = "section",
            KEY_USERNAME= "username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sectionPrefs = getSharedPreferences(KEY_SECTION, Context.MODE_PRIVATE);
        String username = sectionPrefs.getString(KEY_USERNAME, null);
        if (username == null) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(navListener);
        Fragment selectedFragment = new HomeFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, selectedFragment)
                .commit();



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
                    Fragment selectedFragment = null;

                    int id = item.getItemId();

                    if (id == R.id.nav_home) {
                        selectedFragment = new HomeFragment();
                    } else if (id == R.id.nav_category) {
                        selectedFragment = new CategoryFragment();
                    } else if (id == R.id.nav_search) {
                        selectedFragment = new SearchFragment();
                    } else if (id == R.id.nav_cart) {
                        selectedFragment = new CartFragment();
                    } else if (id == R.id.nav_user) {
                        selectedFragment = new UserFragment();
                    }else {
                           selectedFragment = new HomeFragment();
                    }


                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer, selectedFragment)
                            .commit();

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
