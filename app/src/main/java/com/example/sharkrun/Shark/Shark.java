package com.example.sharkrun.Shark;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import static com.example.sharkrun.Background.MainThread.canvas;

public class Shark implements IObject {
    private Bitmap image;
    public float x, y;
    private float width, height;

    public Shark(Bitmap bm) {
        image = bm;
    }

    public void update(){}

    public void update(float xx, float yy) {
        //als motionevents dan update
        x = xx;
        y = yy;
        canvas.drawBitmap(image, x, y, null);
    }

    public void draw(Canvas canvas) {
        x = (canvas.getWidth() - image.getWidth()) / 2;
        y = canvas.getHeight() - image.getHeight() - 100;
        canvas.drawBitmap(image, x, y, null);
    }



//    @Override
//    public Rect getRectangle() {
//        return new Rect(x, y, x + width, y + height);
//    }
}
