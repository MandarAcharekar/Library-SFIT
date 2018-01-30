package com.example.library.librarysfit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

  private TextView mTextMessage;
  final int GET_LOGIN_DATA = 56;
  public static final String PREFS_NAME = "MyPrefsFile";
  String pid;
  String password;

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

    // Restore preferences from the last time
    SharedPreferences settings = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    pid = settings.getString(LoginActivity.keyPID, null);
    password = settings.getString(LoginActivity.keyPassword, null);

    if(pid == null || password == null){
      Toast.makeText(this, "Login Details not found",
              Toast.LENGTH_SHORT).show();

      // Creating intent to get login details from user
      Intent getLoginIntent = new Intent("com.example.library.librarysfit.LoginActivity");
      startActivityForResult(getLoginIntent, GET_LOGIN_DATA);
    }
    else{
      Toast.makeText(this, "Already Logged in!",
              Toast.LENGTH_SHORT).show();
    }

    // Following block doesn't wait for the result of the LoginActivity
    // If there isn't any data, then it will show null, and then
    // Activity will get over.
    TextView e1 = findViewById(R.id.tv_pid);
    e1.setText("PID: " + pid);
    TextView e2 = findViewById(R.id.tv_pwd);
    e2.setText("Password: " + password);


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


}
