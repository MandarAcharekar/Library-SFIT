package com.example.library.librarysfit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

  Button btn_login;
  Button btn_cancel;
  EditText et_pid;
  EditText et_pwd;

  public static String keyPID = "pid";
  public static String keyPassword = "password";


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.loginbox);

    //getting width height in displayMetrics
    DisplayMetrics displayMetrics = new DisplayMetrics();
    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

    //setting width of dialog box
    // 80% of screen
    int width = (int)(displayMetrics.widthPixels * 0.80);
    getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);

    // Set onClick listener
    btn_login = findViewById(R.id.btn_login);
    btn_cancel = findViewById(R.id.btn_cancel);
    et_pid = findViewById(R.id.ed_PID);
    et_pwd = findViewById(R.id.ed_pwd);

    //Login Button
    //Verify the login details
    btn_login.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {

        Intent sendLoginIntent = new Intent();

        Bundle bundle_pid_pwd = new Bundle();

        // Setting the login data inside the bundle
        bundle_pid_pwd.putString(keyPID, et_pid.getText().toString());
        bundle_pid_pwd.putString(keyPassword, et_pwd.getText().toString());

        // Attaching the Bundle object to the Intent object
        sendLoginIntent.putExtras(bundle_pid_pwd);

        // Setting result as OK
        setResult(RESULT_OK, sendLoginIntent);
        // End current activity
        finish();

      }
    });

    //Cancel Button
    //Go back to Main
    btn_cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        finish();
      }
    });


  }



}
