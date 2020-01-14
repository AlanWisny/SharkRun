package com.example.sharkrun.Objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

public class Barrel extends GameObject {
    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;

    public Barrel(Bitmap res, int x, int y, int w, int h, int s, int numFrames) {
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        this.score = s;

        this.speed = 7 + (int) (rand.nextDouble() * score / 30);
        // capp the speed
        if (speed > 40) {
            speed = 40;
        }

        Bitmap[] image = new Bitmap[numFrames];
        this.spritesheet = res;

        for (int i = 0; i < image.length; i++) {
            image[i] = Bitmap.createBitmap(spritesheet, i * width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100 - speed);
    }

    public void update() {
        y += speed;
        animation.update();
    }

    public void draw(Canvas canvas) {
        try {
            canvas.drawBitmap(animation.getImage(), x, y, null);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

//    public Rect getRectangle() {
//        return new Rect((int)x, (int)y, (int)x + width, (int)y + height);
//    }

    @Override
    public int getHeight() {
        // offset for better collision detection
        return height-10;
    }
}
