package com.example.library.librarysfit.MainScreenFragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.librarysfit.R;

/**
 * Created by vinay on 04-02-2018.
 */

public class DashFragment extends Fragment {
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.page_dash, container, false);

    view.setBackgroundColor(Color.GREEN);
    return view;
  }
}
