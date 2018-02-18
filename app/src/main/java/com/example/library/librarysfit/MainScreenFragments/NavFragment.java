package com.example.library.librarysfit.MainScreenFragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.library.librarysfit.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * Created by vinay on 04-02-2018.
 */

public class NavFragment extends Fragment implements View.OnClickListener{

  Intent intent;
  Button btn_AboutLibrary;
  public static final String htmlStringKey = "htmlKey";
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.page_navigation_temp, container, false);

    btn_AboutLibrary = view.findViewById(R.id.btn_AboutLibrary);
    btn_AboutLibrary.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.btn_AboutLibrary:
          getHtmlString("http://www.sfitengg.org/library_about.php");
        break;
    }

  }

  private void getHtmlString(final String pageUrl){
    new Thread(new Runnable() {
      @Override
      public void run() {

        StringBuilder builder = new StringBuilder();

        try {
          //Document doc = Jsoup.connect("http://www.sfitengg.org/library_about.php").get();
          Document doc = Jsoup.connect(pageUrl).get();
          String title = doc.title();
          Elements links = doc.select("div.inner_the");

          for (Element link : links) {
            builder.append(link.outerHtml());
          }
        } catch (IOException e) {
          builder.append("Error : ").append(e.getMessage()).append("\n");
        }

        String s = new String(builder);
        s = s.replaceAll("src=\"", "src=\"http://www.sfitengg.org/");



        intent = new Intent(getContext(), Nav_Screen.class);
        intent.putExtra(htmlStringKey, new String(s));
        startActivity(intent);
      }// run end
    }//new runnable end
    ).start();//new thread end


  }

}
