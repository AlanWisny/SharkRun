package com.example.sharkrun.Background;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import com.example.sharkrun.Objects.Player;
import com.example.sharkrun.R;
import com.example.sharkrun.Shark.Shark;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 1200;
    public static final int speed = -5;
    private MainThread thread;
    private Background bg;
    private Shark sharky;
    private Player player;
    private float PlayerX;

    public GamePanel(Context context) {
        super(context);

        //this.setOnTouchListener();
        //add callback to the surfaceholder to intercept events
        getHolder().addCallback(this);
        thread = new MainThread(getHolder(), this);

        //set gamePanel focusable so it can handle events
        setFocusable(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.sas));
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.sharky), 0, 0, 0);

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }


    // ik moet niet proberen een nieuwe imagen te drawen, maar het huidige plaatje te verplaatsen
//    @Override
//    public boolean onTouch(View v, MotionEvent e) {
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                x1 = e.getX();
//                y1 = e.getY();
//
//                break;
//            case MotionEvent.ACTION_UP:
//                x2 = e.getX();
//                y2 = e.getY();
//
//                float deltaX = x2 - x1;
//                float deltaY = y2 - y1;
//
//                if (deltaX > MIN_DISTANCE)
//                {
//                    // Left to Right swipe
//                    //sharky.update("right");
//                    Toast.makeText(getContext(), "left2right swipe", Toast.LENGTH_SHORT).show ();
//                }
//                else if( Math.abs(deltaX) > MIN_DISTANCE)
//                {
//                    // Right to Left swipe
//                    //sharky.update("left");
//                    //sharky.drawLeft();
//                    Toast.makeText(getContext(), "Right to Left swipe", Toast.LENGTH_SHORT).show ();
//                }
//                else if(deltaY > MIN_DISTANCE){
//                    // Top to Bott swipe
//                    //gp.update("bot");
//                    Toast.makeText(getContext(), "Top 2 Bottom", Toast.LENGTH_SHORT).show ();
//                }
//                else if( Math.abs(deltaY) > MIN_DISTANCE){
//                    // Bott to Top swipe
//                    //gp.update("top");
//                    Toast.makeText(getContext(), "Bottom 2 Top swipe", Toast.LENGTH_SHORT).show ();
//                }
//                break;
//        }
//        return true;
//    }


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
            //player.MoveTo(PlayerX);
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
