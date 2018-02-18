package com.example.library.librarysfit.MainScreenFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.librarysfit.R;

import java.util.ArrayList;
import java.util.List;


public class DashFragment extends Fragment {

  RecyclerView recyclerView;
  BookAdapter bookAdapter;
  List<Book> bookList;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.page_dash, container, false);

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


    return view;
  }
}
