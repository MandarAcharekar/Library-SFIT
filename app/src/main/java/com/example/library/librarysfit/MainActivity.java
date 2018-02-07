package com.example.library.librarysfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.librarysfit.MainScreenFragments.DashFragment;
import com.example.library.librarysfit.MainScreenFragments.HomeFragment;
import com.example.library.librarysfit.MainScreenFragments.NavFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  public final int GET_LOGIN_DATA = 56;
  public static final String PREFS_NAME = "MyPrefsFile";
  public String pid = "Empty";
  public String password = "Empty";
  List<View> viewList;
  ViewPager viewPager;
  private BottomNavigationView navigation;

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    initBottomNavigation();
  }

  private void initBottomNavigation(){
    View home = getLayoutInflater().inflate(R.layout.page_home, null);
    View dash = getLayoutInflater().inflate(R.layout.page_dash, null);
    View nav = getLayoutInflater().inflate(R.layout.page_navigation, null);

    viewList = new ArrayList<>();
    viewList.add(home);
    viewList.add(dash);
    viewList.add(nav);


    viewPager = findViewById(R.id.activity_main_view_pager);

    viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    viewPager.addOnPageChangeListener(pageChangeListener);
    viewPager.setPageTransformer(
            true,
            new BottomNavigationPageTransformer());

    viewPager.setOffscreenPageLimit(3);

    navigation = findViewById(R.id.activity_main_bottom_navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

  }

  private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageScrollStateChanged(int state) {}

    @Override
    public void onPageSelected(int position) {
      switch (position) {
        case 0:
          navigation.setSelectedItemId(R.id.navigation_home);
          break;
        case 1:
          navigation.setSelectedItemId(R.id.navigation_dashboard);
          break;
        case 2:
          navigation.setSelectedItemId(R.id.navigation_notifications);
          break;
      }
    }

  };


  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          viewPager.setCurrentItem(0);
          return true;
        case R.id.navigation_dashboard:
          viewPager.setCurrentItem(1);
          return true;
        case R.id.navigation_notifications:
          viewPager.setCurrentItem(2);
          return true;
      }
      return false;
    }
  };

  private class MyPagerAdapter extends FragmentPagerAdapter{

    public MyPagerAdapter(FragmentManager fm){
      super(fm);
    }

    @Override
    public Fragment getItem(int position) {
      switch(position){
        case 0: return new HomeFragment();
        case 1: return new DashFragment();
        case 2: return new NavFragment();
      }
      return new HomeFragment();
    }

    @Override
    public int getCount() {
      return 3;
    }
  }

}
