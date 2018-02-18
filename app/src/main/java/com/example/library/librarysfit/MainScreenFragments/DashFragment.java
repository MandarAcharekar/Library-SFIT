package com.example.library.librarysfit.MainScreenFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.librarysfit.R;

public class DashFragment extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.page_dash, container, false);

    return view;
  }
}
