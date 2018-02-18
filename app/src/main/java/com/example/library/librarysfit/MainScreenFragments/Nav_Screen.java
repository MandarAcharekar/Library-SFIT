package com.example.library.librarysfit.MainScreenFragments;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.example.library.librarysfit.R;

public class Nav_Screen extends AppCompatActivity {

  WebView webView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_nav__screen);

    String htmlContent = getIntent().getStringExtra(NavFragment.htmlStringKey);
    webView = findViewById(R.id.webView);

    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null);
  }
}
