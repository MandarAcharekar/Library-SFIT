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

public class DashFragment extends Fragment {



  // Login Screen Variables



 // boolean userLoggedIn=true;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view;
    // Check if the user is logged,
    // Accordingly load the correct view
    //userLoggedIn = isUserLoggedIn();

    //if(userLoggedIn){
    //TODO Switch to the new fragment
    view = inflater.inflate(R.layout.page_dash, container, false);

    // }
    // else{
    //do nothing and keep showing current fragment



    return view;
  }
}
