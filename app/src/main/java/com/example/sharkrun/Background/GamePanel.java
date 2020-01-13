package com.example.sharkrun.Background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.example.sharkrun.Objects.Player;
import com.example.sharkrun.R;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 1200;
    public static final int speed = -5;
    private MainThread thread;
    private Background bg;
    private Player player;
    private float PlayerX;

    public GamePanel(Context context) {
        super(context);

        //add callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        //set gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.sas));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.sharky), 187, 367, 3);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        int counter = 0;
        while (retry && counter<1000) {
            counter++;
            try {
                thread.setRunning(false);
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void update() {
        if (player.getPlaying()) {
            bg.update();
            player.update();
        }
    }

    // vervangen met on MotionEvent.MOVE om x axis te verplaatsen
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            PlayerX = e.getX();
            player.setGoTo(PlayerX);
            player.setMoving(true);
            return true;
        }
        if (e.getAction() == MotionEvent.ACTION_UP) {
            player.setMoving(false);
            return true;
        }
        if (e.getAction() == MotionEvent.ACTION_MOVE) {
            PlayerX = e.getX();
            player.setGoTo(PlayerX);
            return true;
        }
        return super.onTouchEvent(e);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        final float scaleFactorX = getWidth() / (WIDTH * 1.f);
        final float scaleFactorY = getHeight() / (HEIGHT * 1.f);

        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
            player.draw(canvas);
        }
    }
}
