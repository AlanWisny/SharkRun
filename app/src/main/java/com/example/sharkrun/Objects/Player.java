package com.example.sharkrun.Objects;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends GameObject {
    private Bitmap bm;
    private int score;
    private float acc;
    private boolean moving;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;
    private static int screenW;
    private static int screenH;
    private Bitmap[] image;
    private int frames;

    public Player(Bitmap res, int w, int h, int numframes) {
        x = getScreenWidth() / 2;
        y = getScreenHeight() - 300;

        score = 0;
        height = h;
        width = w;
//        bm = res;
        playing = true;
        acc = 0;
        frames = numframes;

        image = new Bitmap[10];
        bm = Bitmap.createScaledBitmap(res, 1000, 180, true);

        for (int i = 0; i < 5; i++) {
            // een array met bitmap images, gemaakt door width van huidige img te delen door het aantal frames = i = img.length
            image[i] = Bitmap.createBitmap(bm, i * width, 0, width, height);
            //image[i] = Bitmap.createBitmap(bm, i*width, 0, (int) x - (bm.getWidth() / 2), y - (bm.getHeight() / 2));
        }

        int j = 5;
        for (int i = 4; i >= 0; i--) {
            // een array met bitmap images, gemaakt door width van huidige img te delen door het aantal frames = i = img.length
            image[j] = Bitmap.createBitmap(bm, i * width, 0, width, height);
            j++;
            //image[i] = Bitmap.createBitmap(bm, i*width, 0, (int) x - (bm.getWidth() / 2), y - (bm.getHeight() / 2));
        }

        animation.setFrames(image);
        animation.setDelay(150);
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

            if (x < goTo - 8 || x > goTo + 8) {
                x += acc * 2;
                if (acc > 8) {
                    acc = 8;
                }
                if (acc < -8) {
                    acc = -8;
                }
            } else {
            }
            acc = 0;
        }
    }

    // de x/y van de image gedeelt door het aantal images genomen vanuit original image,
    // gedeeld door 2 zodat img op de plek van de muis drawed
    public void draw(Canvas canvas) {
        int newx = (int) x - ((bm.getWidth() / frames) / 2);
        int newy = (int) y - (bm.getHeight() / frames);
        canvas.drawBitmap(animation.getImage(), newx, newy, null);
        //canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    @Override
    public Rect getRectangle() {
        int newx = (int) x - ((bm.getWidth() / frames) / 2);
        int newy = (int) y - (bm.getHeight() / frames);
        return new Rect(newx, newy, newx + width, newy + height);
    }

    public static int getScreenWidth() {
        screenW = Resources.getSystem().getDisplayMetrics().widthPixels;
        return screenW;
    }

    public static int getScreenHeight() {
        screenH = Resources.getSystem().getDisplayMetrics().heightPixels;
        return screenH;
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
