package com.nghiatbp_toanum.musicapp.Activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.nghiatbp_toanum.musicapp.Adapter.MainViewPagerAdapter;
import com.nghiatbp_toanum.musicapp.Fragment.TimKiemFragment;
import com.nghiatbp_toanum.musicapp.Fragment.TrangChuFragment;
import com.nghiatbp_toanum.musicapp.R;

public class HomePageActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        anhxa();
        init();
    }

    private void init() {
        MainViewPagerAdapter mainViewPagerAdapter = new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new TrangChuFragment(), "Trang Chủ");
        mainViewPagerAdapter.addFragment(new TimKiemFragment(), "Tìm Kiếm");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_ontrangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_ontimkiem_2);
    }

    private void anhxa() {
        tabLayout = findViewById(R.id.myTabLayout);
        viewPager = findViewById(R.id.myViewPager);
    }
}
