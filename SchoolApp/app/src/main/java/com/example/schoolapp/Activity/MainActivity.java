package com.example.schoolapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.schoolapp.LoginAdapter;
import com.example.schoolapp.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tab_layout;
    ViewPager viewPager;
    float v = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        setContentView(R.layout.first);

        tab_layout=findViewById(R.id.tab_layout);
        viewPager=findViewById(R.id.view_pager);

        tab_layout.addTab(tab_layout.newTab().setText("Admin Login"));
        tab_layout.addTab(tab_layout.newTab().setText("Parents/Student Login"));
        tab_layout.setTabGravity(TabLayout.GRAVITY_FILL);

        final LoginAdapter adapter = new LoginAdapter(getSupportFragmentManager(),this,tab_layout.getTabCount());
        viewPager.setAdapter(adapter);

        // add this line of code to change the tab
        tab_layout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab_layout));

         tab_layout.setTranslationY(300);

         tab_layout.setAlpha(v);

         tab_layout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(800).start();



    }
}