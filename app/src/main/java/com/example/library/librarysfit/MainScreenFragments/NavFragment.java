package com.example.library.librarysfit.MainScreenFragments;

import android.content.Intent;
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
  Button btn_Resources;
  Button btn_DigitalLibrary;
  Button btn_VirtualRefs;
  Button btn_Webopac;
  Button btn_Services;
  Button btn_prHelp;
  Button btn_Gallery;
  Button btn_QuickLinks;
  Button btn_QuestionPapers;
  Button btn_Notices;
  Button btn_News;

  public static final String htmlStringKey = "htmlKey";
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState){
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.page_navigation_temp, container, false);

    btn_AboutLibrary = view.findViewById(R.id.btn_AboutLibrary);
    btn_AboutLibrary.setOnClickListener(this);

    btn_Resources = view.findViewById(R.id.btn_Resources);
    btn_Resources.setOnClickListener(this);

    btn_DigitalLibrary = view.findViewById(R.id.btn_DigitalLibrary);
    btn_DigitalLibrary.setOnClickListener(this);

    btn_VirtualRefs = view.findViewById(R.id.btn_VirtualReferences);
    btn_VirtualRefs.setOnClickListener(this);

    btn_Webopac = view.findViewById(R.id.btn_Webopac);
    btn_Webopac.setOnClickListener(this);

    btn_Services = view.findViewById(R.id.btn_Services);
    btn_Services.setOnClickListener(this);

    btn_prHelp = view.findViewById(R.id.btn_PrHelp);
    btn_prHelp.setOnClickListener(this);

    btn_Gallery = view.findViewById(R.id.btn_Gallery);
    btn_Gallery.setOnClickListener(this);

    btn_QuickLinks = view.findViewById(R.id.btn_QuickLinks);
    btn_QuickLinks.setOnClickListener(this);

    btn_QuestionPapers = view.findViewById(R.id.btn_QuestionPapers);
    btn_QuestionPapers.setOnClickListener(this);

    btn_Notices = view.findViewById(R.id.btn_Notices);
    btn_Notices.setOnClickListener(this);

    btn_News = view.findViewById(R.id.btn_News);
    btn_News.setOnClickListener(this);

    return view;
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()){
      case R.id.btn_AboutLibrary:
        startHtmlStringActivity("http://www.sfitengg.org/library_about.php");
        break;
      case R.id.btn_Resources:
        startHtmlStringActivity("http://www.sfitengg.org/library_resources.php");
        break;
    }

  }

  private void startHtmlStringActivity(final String pageUrl){
    new Thread(new Runnable() {
      @Override
      public void run() {

        StringBuilder builder = new StringBuilder();

        try {
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
        intent.putExtra(htmlStringKey, s);
        startActivity(intent);
      }// run end
    }//new runnable end
    ).start();//new thread end


  }

}
