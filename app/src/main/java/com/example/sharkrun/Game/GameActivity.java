package com.example.sharkrun.Game;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.sharkrun.Background.Background;
import com.example.sharkrun.Background.GamePanel;
import com.example.sharkrun.R;

import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements IGame.MvpView {
    private GamePresenter gPresenter;
    private Background bg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.game);
        setContentView(new GamePanel(this));
        gPresenter = new GamePresenter(this);
    }

    @Override
    public void Move() {
        // doe iets
    }
}
