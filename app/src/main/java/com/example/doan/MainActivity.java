package com.example.doan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.widget.Toolbar;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar tbHome;
    ViewFlipper vfHome;
    RecyclerView recHome;
    NavigationView navHome;
    ListView lvHome;
    DrawerLayout dlHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        ActionBar();
        ActionViewFlipper();
    }

    private void ActionViewFlipper() {
        List<String>quangcao=new ArrayList<>();
        quangcao.add("https://www.thegioididong.com/dtdd/realme-note-50");
        quangcao.add("https://www.thegioididong.com/dtdd/vivo-y36");
        quangcao.add("https://www.thegioididong.com/dtdd/oppo-reno11-f-5g");
        quangcao.add("https://www.thegioididong.com/dtdd/samsung-galaxy-a55-5g");
        quangcao.add("https://www.thegioididong.com/dtdd/xiaomi-14");
        quangcao.add("https://fptshop.com.vn/dien-thoai/iphone-15-pro-max");
        for(int i= 0;i<quangcao.size();i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(quangcao.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vfHome.addView(imageView);
        }
        vfHome.setFlipInterval(3000);
        vfHome.setAutoStart(true);
        Animation slide_in= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide);
        Animation slide_out= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out);
        vfHome.setInAnimation(slide_in);
        vfHome.setOutAnimation(slide_out);
    }

    private void ActionBar (){
        setSupportActionBar(tbHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tbHome.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        tbHome.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlHome.openDrawer(GravityCompat.START);

            }
        });

    }

    private void addControls() {
        tbHome=findViewById(R.id.tbHome);
        vfHome=findViewById(R.id.vfHome);
        recHome=findViewById(R.id.recHome);
       navHome=findViewById(R.id.navHome);
        lvHome=findViewById(R.id.lvHome);
        dlHome=findViewById(R.id.dlHome);
    }


}