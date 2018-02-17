package com.example.library.librarysfit.MainScreenFragments;

import android.widget.ImageView;

/**
 * Created by Bhatnagar Rishabh on 2/12/2018.
 */

public class Book {
    private String Title;
    private int imageView;

    public Book(String title, int imageView) {
        Title = title;
        this.imageView = imageView;
    }

    public String getTitle() {
        return Title;
    }

    public int getImageView() {
        return imageView;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }
}
