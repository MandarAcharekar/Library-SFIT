package com.example.library.librarysfit;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  final int GET_LOGIN_DATA = 56;

  private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
          = new BottomNavigationView.OnNavigationItemSelectedListener() {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
      switch (item.getItemId()) {
        case R.id.navigation_home:
          mTextMessage.setText(R.string.title_home);
          return true;
        case R.id.navigation_dashboard:
          mTextMessage.setText(R.string.title_dashboard);
          return true;
        case R.id.navigation_notifications:
          mTextMessage.setText(R.string.title_notifications);
          return true;
      }
      return false;
    }
  };

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    mTextMessage = (TextView) findViewById(R.id.message);
    BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
    navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    //popup now
   // callLoginDialog();
    Intent getLoginIntent = new Intent("com.example.library.librarysfit.LoginActivity");
    startActivityForResult(getLoginIntent, GET_LOGIN_DATA);

  }

  public void onActivityResult(int requestCode, int resultCode, Intent data){
    if(requestCode == GET_LOGIN_DATA){
      if(resultCode == RESULT_OK){
        Bundle bundle = data.getExtras();

        String pid = bundle.getString(LoginActivity.keyPID);
        String pwd = bundle.getString(LoginActivity.keyPassword);

        Toast.makeText(this, "Welcome! " + pid,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(this, "Password: " + pwd,
                Toast.LENGTH_SHORT).show();
      }
    }
  }


}
