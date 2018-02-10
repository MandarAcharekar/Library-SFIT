package com.example.library.librarysfit;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by vinay on 04-02-2018.
 */

public class BottomNavigationPageTransformer implements ViewPager.PageTransformer{
  @Override
  public void transformPage(View page, float position) {
    if(position < 0 ){
      page.setAlpha(position+1);
    }
    else if (position < 1){
      page.setTranslationX(page.getMeasuredWidth() * -position);
    }
  }
}