package com.example.sharkrun.Objects;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import com.example.sharkrun.Background.GamePanel;


public class Player extends GameObject {
    private Bitmap bm;
    private int score;
    private float acc;
    private boolean moving;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    public Player(Bitmap res, int w, int h, int numframes) {
        // TODO ervoor zorgen dat player op de juiste locatie spawned, binnen het canvas
        x = GamePanel.WIDTH / 2;
        y = 900;
        score = 0;
        height = h;
        width = w;
        bm = res;
        playing = true;
        acc = 0;

        Bitmap[] image = new Bitmap[numframes];
        for (int i = 0; i < image.length; i++){
            // een array met bitmap images, gemaakt door width van huidige img te delen door het aantal frames = i = img.length
            image[i] = Bitmap.createBitmap(bm, i*width, 0, width, height);
            //image[i] = Bitmap.createBitmap(bm, i*width, 0, (int) x - (bm.getWidth() / 2), y - (bm.getHeight() / 2));
        }

        animation.setFrames(image);
        animation.setDelay(1000);
        startTime = System.nanoTime();
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
        animation.update();

        if (moving) {
                if (goTo > x) {
                    acc += 8f;
                } else {
                    acc -= 8f;
                }

            if (x < goTo -8 || x > goTo +8) {
                x += acc * 2;
                if (acc > 8) {acc = 8;}
                if (acc < -8) {acc = -8;}
            } else {
            }
            acc = 0;
        }
    }

    // de x/y van de image gedeelt door het aantal images genomen vanuit original image,
    // gedeeld door 2 zodat img op de plek van de muis drawed
    public void draw(Canvas canvas) {
        // collision werkt niet omdat x en y van player anders zijn dan van de img TODO collision
        int newx = (int) x - (bm.getWidth() / 2);
        int newy = (int) y - (bm.getHeight() / 2);
        canvas.drawBitmap(animation.getImage(), newx, newy, null);
        //canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    @Override
    public Rect getRectangle() {
        int newx = (int) x + (bm.getWidth() / 2);
        int newy = (int) y + (bm.getHeight() / 2);
        return new Rect(newx, newy, newx + width, newy + height);
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
