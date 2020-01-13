package com.example.sharkrun.Game;
import android.os.Bundle;
import android.view.WindowManager;
import com.example.sharkrun.Background.GamePanel;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity implements IGame.MvpView {
    private GamePresenter gPresenter;
    private GamePanel gp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.game);
        GamePanel gp = new GamePanel(this);
        setContentView(gp);
    }

    @Override
    public void Move() {
        // doe iets
    }
}
