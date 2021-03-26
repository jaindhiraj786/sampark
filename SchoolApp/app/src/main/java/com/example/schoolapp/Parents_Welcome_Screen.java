package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.schoolapp.Homework.HomeWork;
import com.example.schoolapp.Notice.Notes;
import com.example.schoolapp.Result.Result;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;

public class Parents_Welcome_Screen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    GridView gridMenu;
    ArrayList<ParentsInfo> parentsInfo = new ArrayList<ParentsInfo>();
    ParentsArrayAdapter parentsArrayAdapter = null;
    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.parents_welcome);

        Toolbar toolbarParent = findViewById(R.id.toolbarParent);
        setSupportActionBar(toolbarParent);

        gridMenu = findViewById(R.id.gridMenu);
        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.nav_view);

        // Action bar
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbarParent, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_Home);


        // dummy data
        ParentsInfo p1 = new ParentsInfo(R.drawable.elearning, "E-Learning");
        ParentsInfo p2 = new ParentsInfo(R.drawable.homework, "HomeWork");
        ParentsInfo p3 = new ParentsInfo(R.drawable.bus, "Bus Tracking");
        ParentsInfo p4 = new ParentsInfo(R.drawable.edit,"Notes");
        ParentsInfo p5 = new ParentsInfo(R.drawable.report,"Report-Card");
        ParentsInfo p6 = new ParentsInfo(R.drawable.immigration,"Attendance");

        parentsInfo.add(p1);
        parentsInfo.add(p2);
        parentsInfo.add(p3);
        parentsInfo.add(p4);
        parentsInfo.add(p5);
        parentsInfo.add(p6);


        parentsArrayAdapter = new ParentsArrayAdapter();
        gridMenu.setAdapter(parentsArrayAdapter);

        gridMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(Parents_Welcome_Screen.this, "Selected"+position, Toast.LENGTH_SHORT).show();

                if (position == 1) {
                    Intent intentHomeWork = new Intent(Parents_Welcome_Screen.this, HomeWork.class);
                    startActivity(intentHomeWork);
                }
                if (position==3){
                    Intent intentNotes = new Intent(Parents_Welcome_Screen.this, Notes.class);
                    startActivity(intentNotes);

                }
                if (position==4){
                    Intent intentReport = new Intent(Parents_Welcome_Screen.this, Result.class);
                    startActivity(intentReport);
                }

                parentsArrayAdapter.notifyDataSetChanged();
            }
        });

        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Fail";
                        }
                    }
                });


    }

    @Override
    public void onBackPressed() {

    if (drawerLayout.isDrawerOpen(GravityCompat.START)){
        drawerLayout.closeDrawer(GravityCompat.START);
    }else {
        super.onBackPressed();
    }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_Home:
                break;
            case R.id.nav_Homework:
                Intent intent = new Intent(Parents_Welcome_Screen.this,HomeWork.class);
                startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    class ParentsArrayAdapter extends ArrayAdapter {
        public ParentsArrayAdapter() {
            super(Parents_Welcome_Screen.this, R.layout.menu_page, parentsInfo);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Layout Inflater
            LayoutInflater inflater = getLayoutInflater();
            View menuPage = inflater.inflate(R.layout.menu_page, null);
            ImageView imageMenu = menuPage.findViewById(R.id.logo_menu);
            TextView textMenu = menuPage.findViewById(R.id.text_name);

            ParentsInfo pi = parentsInfo.get(position);
            imageMenu.setImageResource(pi.getImage());
            textMenu.setText(pi.getName());

            return menuPage;


        }
    }
}
