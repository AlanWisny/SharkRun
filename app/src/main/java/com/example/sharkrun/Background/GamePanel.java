package com.example.sharkrun.Background;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.sharkrun.R;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final float WIDTH = 600;
    public static final float HEIGHT = 1200;
    public int speed = -5;
    private MainThread thread;
    private Background bg;

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

        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
        UpSpeed();
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    public void update() {
        bg.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        final float scaleFactorX = getWidth() / WIDTH;
        final float scaleFactorY = getHeight() / HEIGHT;
        if (canvas != null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    public void UpSpeed() {
        CountDownTimer timer = new CountDownTimer(1000000000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //interval, elke zo veel seconden moet er wat gebeuren
                speed = speed - 5;
                bg.setVector(speed);
                Toast.makeText(getContext(), "De speed gaat nu omhoog!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFinish() {
                // einde timer, elke keer dat de timer finished moet er iets gebeuren
                // max timer is 16 minuten, timer loopen?
            }
        };
        timer.start();
    }
}
