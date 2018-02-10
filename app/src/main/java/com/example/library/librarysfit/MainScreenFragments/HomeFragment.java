package com.example.library.librarysfit.MainScreenFragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bumptech.glide.Glide;
import com.example.library.librarysfit.R;

public class HomeFragment extends Fragment {


  private Button recycle_button;



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.page_home, container, false);
    view.setBackgroundColor(Color.RED);
    return view;
  }

}