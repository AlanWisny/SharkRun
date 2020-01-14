package com.example.sharkrun.Background;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sharkrun.Objects.Barrel;
import com.example.sharkrun.Objects.GameObject;
import com.example.sharkrun.Objects.Player;
import com.example.sharkrun.R;

import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 1200;
    public static final int W = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static final int H = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static final int speed = -5;
    private MainThread thread;
    private Background bg;
    private Player player;
    private float PlayerX;
    private ArrayList<Barrel> barrels;
    private long barrelStartTime;
    private Random random = new Random();

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
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.sharky), 187, 167, 3);
        barrels = new ArrayList<Barrel>();
        barrelStartTime = System.nanoTime();

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
        int counter = 0;
        while (retry && counter < 1000) {
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

            // spawn barrels on time
            long barrelsElapsed = (System.nanoTime() - barrelStartTime) / 1000000;
            int barrelSpawnLocation = (int) (random.nextDouble() * (WIDTH));

            if (barrelsElapsed > (2000 - player.getScore() / 4)) {
                // the first barrel goes through the middle
                if (barrels.size() == 0) {
                    barrels.add(new Barrel(BitmapFactory.decodeResource(getResources(), R.drawable.barrelsheet),
                            WIDTH / 2, 0, 37, 37, player.getScore(), 3));
                    // rest of the barrels spawns randomly along the x axis
                } else {
                    barrels.add(new Barrel(BitmapFactory.decodeResource(getResources(), R.drawable.barrelsheet),
                            barrelSpawnLocation, 0, 37, 37, player.getScore(), 3));
                }
                barrelStartTime = System.nanoTime();
            }


            // loop through every barrel
            for (int i = 0; i < barrels.size(); i++) {
                barrels.get(i).update();


                // if a barrel hits a player
                if (collision(barrels.get(i), player)) {
                    barrels.remove(i);
                    player.setPlaying(false);
                    break;
                }

                // als een barrel off-screen gaat
                if (barrels.get(i).getY() > HEIGHT) {
                    barrels.remove(i);
                }
            }
        }
    }

    public boolean collision(GameObject a, GameObject b) {
        if (Rect.intersects(a.getRectangle(), b.getRectangle())) {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN) {
            PlayerX = e.getX();
            player.setGoTo(PlayerX);
            player.setMoving(true);
            player.setPlaying(true);
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
            player.draw(canvas);

            for (Barrel b : barrels) {
                b.draw(canvas);
            }
            canvas.restoreToCount(savedState);
        }
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
