package com.example.sharkrun.Splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sharkrun.Menu.MenuActivity;
import com.example.sharkrun.R;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity implements ISplash.MvpView{
    private SplashPresenter sPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        sPresenter = new SplashPresenter(this);
        sPresenter.handleShowSplash();
    }

    @Override
    public void showSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MenuActivity.class);
                startActivity(i);
                finish();
            }
        }, 2000);
    }
}
