package com.example.sharkrun.Menu;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import com.example.sharkrun.Game.GameActivity;
import com.example.sharkrun.R;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MenuActivity extends AppCompatActivity implements IMenu.MvpView{
    private MenuPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        mPresenter = new MenuPresenter(this);
        createStartButton();
        createQuitButton();
        //mPresenter.handleStartMenuButton();
    }

    // Start Game
    @Override
    public void StartMenuButton() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MenuActivity.this, GameActivity.class);
                startActivity(i);
                finish();
            }
        }, 1000);
    }

    public void createStartButton(){
        Button startButton = (Button) findViewById(R.id.startButton);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mPresenter.handleStartMenuButton();
            }
        };
        startButton.setOnClickListener(listener);
    }

    // Quit Application
    @Override
    public void QuitMenuButton() {
        finish();
    }

    public void createQuitButton(){
        Button quitButton = (Button) findViewById(R.id.quitButton);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v){
                final AlertDialog.Builder builder = new AlertDialog.Builder(MenuActivity.this);
                builder.setTitle("Are you sure you want to exit?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.handleQuitMenuButton();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        };
        quitButton.setOnClickListener(listener);
    }
}
