package com.example.library.librarysfit.MainScreenFragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.librarysfit.LoginActivity;
import com.example.library.librarysfit.MainActivity;
import com.example.library.librarysfit.R;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.library.librarysfit.MainActivity.PREFS_NAME;

/**
 * Created by vinay on 04-02-2018.
 */

public class HomeFragment extends Fragment {

  TextView tv_pid;
  TextView tv_pwd;
  Button homeLogin;
  MainActivity mainActivity;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.page_home, container, false);

    mainActivity = (MainActivity) getActivity();
    tv_pid = view.findViewById(R.id.tv_pid);
    tv_pwd = view.findViewById(R.id.tv_pwd);
    homeLogin = view.findViewById(R.id.btn_login_home);
    homeLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getActivity(), "button Press",
                Toast.LENGTH_SHORT).show();
        startLoginProcess();
      }
    });


    return view;
  }

  public void startLoginProcess(){
    SharedPreferences settings = mainActivity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    mainActivity.pid = settings.getString(LoginActivity.keyPID, null);
    mainActivity.password = settings.getString(LoginActivity.keyPassword, null);

    if(mainActivity.pid == null || mainActivity.password == null){
      Toast.makeText(mainActivity, "Login Details not found",
              Toast.LENGTH_SHORT).show();

      // Creating intent to get login details from user
      Intent getLoginIntent = new Intent("com.example.library.librarysfit.LoginActivity");
      startActivityForResult(getLoginIntent, mainActivity.GET_LOGIN_DATA);
    }
    else{
      Toast.makeText(mainActivity, "Already Logged in!",
              Toast.LENGTH_SHORT).show();

      tv_pid.setText(mainActivity.pid);
      tv_pwd.setText(mainActivity.password);

    }
  }

  public void onActivityResult(int requestCode, int resultCode, Intent data){
    if(requestCode == mainActivity.GET_LOGIN_DATA){
      if(resultCode == RESULT_OK){
        // Get the Bundle with pid, password
        Bundle bundle = data.getExtras();

        // Retrieving values from the Bundle
        mainActivity.pid = bundle.getString(LoginActivity.keyPID);
        mainActivity.password = bundle.getString(LoginActivity.keyPassword);


        SharedPreferences settings = mainActivity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        // Storing the PID, Passwords
        editor.putString(LoginActivity.keyPID, mainActivity.pid);
        editor.putString(LoginActivity.keyPassword, mainActivity.password);

        // Commit the edits
        editor.commit();


        Toast.makeText(mainActivity, "Welcome! " + mainActivity.pid,
                Toast.LENGTH_SHORT).show();
        Toast.makeText(mainActivity, "Password: " + mainActivity.password,
                Toast.LENGTH_SHORT).show();
      }
    }
  }



}
