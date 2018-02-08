package com.example.library.librarysfit.MainScreenFragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.library.librarysfit.Cards;
import com.example.library.librarysfit.R;

public class HomeFragment extends Fragment {


  private Button recycle_button;



  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment

    View view = inflater.inflate(R.layout.page_home, container, false);






    //Adding a click listener to test the Card Java and xml components.
    recycle_button = view.findViewById(R.id.button_recyclerview);
    recycle_button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Intent recyclerCards = new Intent(getActivity(), Cards.class);
            startActivity(recyclerCards);

        }
    });




    view.setBackgroundColor(Color.RED);
    return view;
  }

}
