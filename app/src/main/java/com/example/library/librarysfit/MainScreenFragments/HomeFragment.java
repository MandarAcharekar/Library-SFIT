package com.example.library.librarysfit.MainScreenFragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.library.librarysfit.LoginActivity;
import com.example.library.librarysfit.MainActivity;
import com.example.library.librarysfit.R;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;
import static com.example.library.librarysfit.MainActivity.PREFS_NAME;

public class HomeFragment extends Fragment {

  MainActivity mainActivity = (MainActivity) getActivity();
  boolean userLoggedIn=true;

  // Login Screen Variables
  TextView tv_pid;
  TextView tv_pwd;
  Button homeLogin;

  // Issued books screen
  RecyclerView recyclerView;
  BookAdapter bookAdapter;
  List<Book> bookList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment
    View view;
    // Check if the user is logged,
    // Accordingly load the correct view
    userLoggedIn = isUserLoggedIn();
    if(userLoggedIn){
      //TODO Switch to the new fragment
      view = inflater.inflate(R.layout.issued_books, container, false);

      bookList = new ArrayList<>();

      recyclerView = view.findViewById(R.id.recyclerView);
      recyclerView.setHasFixedSize(true);

      //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity() ));
      LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
      recyclerView.setLayoutManager(linearLayoutManager);

      bookList.add(new Book("Book1", R.drawable.ic_notifications_black_24dp));
      bookList.add(new Book("Book2", R.drawable.ic_dashboard_black_24dp));
      bookList.add(new Book("Book3", R.drawable.ic_home_black_24dp));

      bookAdapter = new BookAdapter(getActivity(), bookList);
      recyclerView.setAdapter(bookAdapter);



    }
    else{
      //do nothing and keep showing current fragment
      view = inflater.inflate(R.layout.page_home, container, false);

      mainActivity = (MainActivity) getActivity();
      tv_pid = view.findViewById(R.id.tv_pid);
      tv_pwd = view.findViewById(R.id.tv_pwd);
      homeLogin = view.findViewById(R.id.btn_login_home);
      homeLogin.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          Toast.makeText(getActivity(), "button Press",
                  Toast.LENGTH_SHORT).show();
          //startLoginProcess();

          // Creating intent to get login details from user
          Intent getLoginIntent = new Intent("com.example.library.librarysfit.LoginActivity");
          startActivityForResult(getLoginIntent, mainActivity.GET_LOGIN_DATA);
        }
      });

    }

    return view;
  }

  public boolean isUserLoggedIn(){
    // Need to do it again here, because reasons.
    mainActivity = (MainActivity)getActivity();
    SharedPreferences settings = mainActivity.getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
    mainActivity.pid = settings.getString(LoginActivity.keyPID, null);
    mainActivity.password = settings.getString(LoginActivity.keyPassword, null);
    if(mainActivity.pid == null || mainActivity.password == null) {
      return false;
    }
    else
      return true;
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

        // After getting login details
        // Recreate current fragment
        mainActivity.viewPager.getAdapter().notifyDataSetChanged();

      }
    }
  }



}
