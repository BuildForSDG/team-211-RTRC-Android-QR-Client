package com.andela.buildsdgs.rtrc.revcollector;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.andela.buildsdgs.rtrc.revcollector.ui.main.adaptors.SectionsPagerAdaptor;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.fragments.TransactionFragment;
import com.andela.buildsdgs.rtrc.revcollector.ui.main.fragments.VehicleFragment;
import com.google.android.material.tabs.TabLayout;


public class MainActivity extends AppCompatActivity {

    SectionsPagerAdaptor sectionsPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //initialize objects
        sectionsPagerAdapter = new SectionsPagerAdaptor(getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        //add method for Setting up viewpager
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        sectionsPagerAdapter = new SectionsPagerAdaptor(getSupportFragmentManager());
        sectionsPagerAdapter.addFragment(new TransactionFragment(), "Transactions");
        sectionsPagerAdapter.addFragment(new VehicleFragment(),"Vehicles");
        viewPager.setAdapter(sectionsPagerAdapter);
    }


}
