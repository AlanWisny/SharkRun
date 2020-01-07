package com.example.sharkrun.Splash;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sharkrun.Game.GameActivity;
import com.example.sharkrun.Menu.MenuActivity;
import com.example.sharkrun.R;

public class MainActivity extends AppCompatActivity implements ISplash.MvpView{
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
                Intent i = new Intent(MainActivity.this, GameActivity.class);
                startActivity(i);
                finish();
            }
        }, 500);
    }
}
