package com.example.sharkrun.GameOverPopup;
import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.example.sharkrun.R;

public class GameOverPopup extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // shows the Game Over Popup screen
        setContentView(R.layout.gameoverpop);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = (int)(dm.widthPixels * 0.8);
        int height = (int)(dm.heightPixels * 0.8);

        getWindow().setLayout(width, height);
    }
}
