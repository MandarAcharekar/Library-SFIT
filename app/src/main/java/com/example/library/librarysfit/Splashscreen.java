package com.example.library.librarysfit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Mandar Acharekar on 19-02-2018.
 */

public class Splashscreen extends AppCompatActivity {
    private static  int SPLASH_TIME_OUT =3000;

    @Override
    protected  void  onCreate(Bundle savedInstantstate){
        super.onCreate(savedInstantstate);
        setContentView(R.layout.splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i =new Intent(Splashscreen.this,MainActivity.class);
                Splashscreen.this.startActivity(i);
                Splashscreen.this.finish();

            }
        },SPLASH_TIME_OUT);
    }

}
