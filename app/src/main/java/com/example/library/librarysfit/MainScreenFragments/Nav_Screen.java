package com.example.library.librarysfit.MainScreenFragments;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
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
    WebSettings settings = webView.getSettings();//.setJavaScriptEnabled(true);

    //getSupportActionBar().setTitle("TEst 1");

    /*
    // Use WideViewport and Zoom out if there is no viewport defined
    settings.setUseWideViewPort(true);
    settings.setLoadWithOverviewMode(true);
    settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
    */

    // Enable pinch to zoom without the zoom buttons
    settings.setBuiltInZoomControls(true);

    if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
      // Hide the zoom controls for HONEYCOMB+
      settings.setDisplayZoomControls(false);
    }

    webView.loadDataWithBaseURL(null, htmlContent, "text/html", "utf-8", null);
  }
}
