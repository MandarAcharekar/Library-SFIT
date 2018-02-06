package com.example.library.librarysfit.MainScreenFragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.library.librarysfit.R;

/**
 * Created by vinay on 04-02-2018.
 */

public class HomeFragment extends Fragment {

  Button homeLogin;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.page_home, container, false);

    view.setBackgroundColor(Color.RED);

    homeLogin = view.findViewById(R.id.btn_login_home);
    homeLogin.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Toast.makeText(getActivity(), "button Press",
                Toast.LENGTH_SHORT).show();

        
      }
    });


    return view;
  }

}
