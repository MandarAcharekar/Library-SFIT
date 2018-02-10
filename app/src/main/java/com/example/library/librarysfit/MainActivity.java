package com.example.library.librarysfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
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
import android.widget.Toolbar;

import com.example.library.librarysfit.MainScreenFragments.DashFragment;
import com.example.library.librarysfit.MainScreenFragments.HomeFragment;
import com.example.library.librarysfit.MainScreenFragments.NavFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  final int GET_LOGIN_DATA = 56;
  public static final String PREFS_NAME = "MyPrefsFile";
  String pid;
  String password;
  List<View> viewList;
  ViewPager viewPager;
  private BottomNavigationView navigation;

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    Window window = getWindow();
    window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    window.setStatusBarColor(Color.argb(33, 0, 0, 0));

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

    // viewPager.setAdapter(pagerAdapter);
    viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    viewPager.addOnPageChangeListener(pageChangeListener);
    viewPager.setPageTransformer(
            true,
            new BottomNavigationPageTransformer());

    navigation = findViewById(R.id.activity_main_bottom_navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    // If BottomNavigationView has more than 3 items, using reflection to disable shift mode
    BottomNavigationViewHelper.disableShiftMode(navigation);
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
          //mTextMessage.setText(R.string.title_home);
          viewPager.setCurrentItem(0);
          //switchToFragmentHome();
          return true;
        case R.id.navigation_dashboard:
          //mTextMessage.setText(R.string.title_dashboard);
          viewPager.setCurrentItem(1);
          //switchToFragmentDash();
          return true;
        case R.id.navigation_notifications:
          //mTextMessage.setText(R.string.title_notifications);
          viewPager.setCurrentItem(2);
          //switchToFragmentNav();
          return true;
      }
      return false;
    }
  };



  private PagerAdapter pagerAdapter = new PagerAdapter() {
    @Override
    public int getCount() {
      return viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
      return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
      container.removeView(viewList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
      container.addView(viewList.get(position));
      return viewList.get(position);
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


  public void onActivityResult(int requestCode, int resultCode, Intent data){
    if(requestCode == GET_LOGIN_DATA){
      if(resultCode == RESULT_OK){
        // Get the Bundle with pid, password
        Bundle bundle = data.getExtras();

        // Retrieving values from the Bundle
        pid = bundle.getString(LoginActivity.keyPID);
        password = bundle.getString(LoginActivity.keyPassword);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Storing the PID, Passwords
        editor.putString(LoginActivity.keyPID, pid);
        editor.putString(LoginActivity.keyPassword, password);

        // Commit the edits
        editor.commit();


        Toast.makeText(this, "Welcome! " + pid,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Password: " + password,
                Toast.LENGTH_SHORT).show();
      }
    }
  }

  public void switchToFragmentHome(){
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction().replace(R.id.page_home, new HomeFragment()).commit();
  }
  public void switchToFragmentDash(){
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction().replace(R.id.page_dash, new DashFragment()).commit();
  }
  public void switchToFragmentNav(){
    FragmentManager manager = getSupportFragmentManager();
    manager.beginTransaction().replace(R.id.page_nav, new NavFragment()).commit();
  }


}
