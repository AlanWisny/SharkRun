package com.example.sharkrun.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.example.sharkrun.Background.GamePanel;

public class Player extends GameObject {
    private Bitmap bm;
    private int score;
    private float acc;
    private boolean moving;
    private boolean playing;
    //private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numframes) {
        // TODO ervoor zorgen dat player op de juiste locatie spawned, binnen het canvas
        x = 600;
        y = 1400;
        dy = 0;
        dx = 0;
        score = 0;
        height = h;
        width = w;
        bm = res;
        playing = true;
        acc = 0;

//        Bitmap[] image = new Bitmap[numframes];
//        for (int i = 0; i < image.length; i++){
//            image[i] = Bitmap.createBitmap(bm, i*width, 0, width, height);
//        }
    }

    public void setMoving(boolean b) {
        moving = b;
    }

    public void update() {
        long elapsed = (System.nanoTime() - startTime / 1000000);
        if (elapsed > 100) {
            score++;
            startTime = System.nanoTime();
        }
        //update the animation
        //animation.update();


        if (moving) {
                if (goTo > x) {
                    acc += 4f;
                } else {
                    acc -= 4f;
                }

            if (x < goTo -8 || x > goTo +8) {
                x += acc * 2;
                if (acc > 8) {acc = 8;}
                if (acc < -8) {acc = -8;}
            } else {
                acc = 0;
            }
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bm, x - (bm.getWidth() / 2), y - (bm.getHeight() / 2), null);
    }

    public int getScore() {
        return score;
    }

    public boolean getPlaying() {
        return playing;
    }

    public void setPlaying(boolean b) {
        playing = b;
    }

    public void resetACC() {
        acc = 0;
    }

    public void resetScore() {
        score = 0;
    }
}
