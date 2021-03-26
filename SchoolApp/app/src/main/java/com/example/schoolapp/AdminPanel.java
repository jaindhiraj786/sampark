package com.example.schoolapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.schoolapp.Activity.MainActivity;
import com.example.schoolapp.AddUser.AddUser;
import com.example.schoolapp.Homework.AddHomework;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class AdminPanel extends AppCompatActivity {

    GridView gridAdminPanel;
    ArrayList<AdminMenu> adminMenus = new ArrayList<AdminMenu>();
    AdminAdapter adminAdapter = null;
    Button buttonLogout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_panel);

        gridAdminPanel = findViewById(R.id.gridAdminPanel);
        buttonLogout=findViewById(R.id.buttonLogout);

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(AdminPanel.this, MainActivity.class);
                startActivity(intent);
            }
        });

        AdminMenu am1 = new AdminMenu(R.drawable.adduser, "Add User");
        AdminMenu am2 = new AdminMenu(R.drawable.adduser, "Add Teacher");
        AdminMenu am3 = new AdminMenu(R.drawable.adduser, "Attendance");
        AdminMenu am4 = new AdminMenu(R.drawable.adduser,"Add Homework");


        adminMenus.add(am1);
        adminMenus.add(am2);
        adminMenus.add(am3);
        adminMenus.add(am4);

        adminAdapter = new AdminAdapter();
        gridAdminPanel.setAdapter(adminAdapter);

        gridAdminPanel.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdminMenu add = adminMenus.get(position);


                if (position == 1) {
                    Toast.makeText(AdminPanel.this,""+position,Toast.LENGTH_SHORT).show();
                    Intent intentAddUser = new Intent(AdminPanel.this, AddTeacher.class);
                    startActivity(intentAddUser);

                } if (position == 2) {
                    Toast.makeText(AdminPanel.this,""+position,Toast.LENGTH_SHORT).show();
                    Intent intentAddUser = new Intent(AdminPanel.this, Attendance.class);
                    startActivity(intentAddUser);

                }
                if (position==3){
                    Toast.makeText(AdminPanel.this,""+position,Toast.LENGTH_SHORT).show();
                    Intent intentHomework = new Intent(AdminPanel.this, AddHomework.class);
                    startActivity(intentHomework);

                }
                else {
                    Toast.makeText(AdminPanel.this,""+position,Toast.LENGTH_SHORT).show();
                    Intent intentAttendance = new Intent(AdminPanel.this, AddUser.class);
                    startActivity(intentAttendance);



                }
                adminAdapter.notifyDataSetChanged();
            }

        });

    }

    class AdminAdapter extends ArrayAdapter {

        public AdminAdapter() {
            super(AdminPanel.this, R.layout.admin_grid_view, adminMenus);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater = getLayoutInflater();
            View adminGrid = inflater.inflate(R.layout.admin_grid_view, null);
            ImageView imageAdd = adminGrid.findViewById(R.id.imageAdd);
            TextView textAdd = adminGrid.findViewById(R.id.textAdd);

            AdminMenu a1 = adminMenus.get(position);
            imageAdd.setImageResource(a1.getImage());
            textAdd.setText(a1.getName());

            return adminGrid;

        }
    }
}
