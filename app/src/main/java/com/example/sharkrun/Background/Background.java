package com.example.sharkrun.Background;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dx;
    private int imgheight = 5498;
    //private int imgheight = 10521;


    protected Background(Bitmap res) {
        image = res;
    }

    protected void update() {
        y += dx;
        if (y < -imgheight) {
            y = 0;
        }
    }

    protected void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);

        if (y < 0) {
            canvas.drawBitmap(image, x, y+imgheight, null);
        }
    }

    protected void setVector(int dx) {
        this.dx = dx;
    }
}
