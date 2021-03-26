package com.example.schoolapp;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class LoginAdapter extends FragmentPagerAdapter {

    private Context context;
    int totalPages;

    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totalPages) {
        super(fm);
        this.context = context;
        this.totalPages = totalPages;
    }

    @Override
    public int getCount() {
        return totalPages;
    }

    public Fragment getItem(int position){
        switch (position){
            case 0:
                AdminTabFragment adminTabFragment = new AdminTabFragment();
                return adminTabFragment;
            case 1:
                ParentsLogInTab parentsLogInTab = new ParentsLogInTab();
                return parentsLogInTab;
            default:
                return null;

        }
     }
}
