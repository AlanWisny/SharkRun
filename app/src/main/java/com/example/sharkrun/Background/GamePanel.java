package com.example.sharkrun.Background;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.sharkrun.Game.GameActivity;
import com.example.sharkrun.GameOverPopup.GameOverPopup;
import com.example.sharkrun.Objects.Barrel;
import com.example.sharkrun.Objects.GameObject;
import com.example.sharkrun.Objects.Player;
import com.example.sharkrun.R;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    public static int WIDTH = getScreenWidth();
    public static int HEIGHT = getScreenHeight();
    public static final int speed = -5;
    private MainThread thread;
    private Background bg;
    private Player player;
    private float PlayerX;
    private ArrayList<Barrel> barrels;
    private long barrelStartTime;
    private Random random = new Random();
    private boolean newGameCreated = true;
    private int best;

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
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.sharky2), 200, 180, 5);
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
                thread = null;
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
                            WIDTH / 2, 0, 100, 150, player.getScore(), 3));
                    // rest of the barrels spawns randomly along the x axis
                } else {
                    barrels.add(new Barrel(BitmapFactory.decodeResource(getResources(), R.drawable.barrelsheet),
                            barrelSpawnLocation, 0, 100, 150, player.getScore(), 3));
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
        } else {
            newGameCreated = false;
            if(!newGameCreated){
                NewGame();
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
            drawText(canvas);
            canvas.restoreToCount(savedState);
        }
    }

    // player gaat dood, reset de game
    public void NewGame(){
//        GameOverPopup gp = new GameOverPopup();
//        gp.setContentView(R.layout.gameoverpop);

        barrels.clear();

        player.resetACC();
        player.resetScore();
        player.setX(WIDTH / 2);

        bg.setY(0);

        //newGameCreated = true;

        if(player.getScore() > best){
            best = player.getScore();
        }
    }

    public void drawText(Canvas canvas){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextSize(30);

        canvas.drawText("DISTANCE: " + (player.getScore() * 3), 10, HEIGHT - 10, paint);
        canvas.drawText("BEST: " + best, WIDTH - 150, HEIGHT - 10, paint);

        if (!player.getPlaying() && newGameCreated){
            Paint newPaint = new Paint();
        }
    }

    public static int getScreenWidth() {
        WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        return WIDTH;
    }

    public static int getScreenHeight() {
        HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        return HEIGHT;
    }

    public boolean isNewGameCreated() {
        return newGameCreated;
    }

    public void setNewGameCreated(boolean newGameCreated) {
        this.newGameCreated = newGameCreated;
    }
}
