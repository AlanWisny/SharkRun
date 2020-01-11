package com.example.sharkrun.Background;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.CountDownTimer;

public class Background implements IBackground {

    private Bitmap image;
    private int x, y, dx;
    private int imgheight = 5498;
    //private int imgheight = 10521;


    public Background(Bitmap res) {
        image = res;
        dx = GamePanel.speed;
        //UpSpeed();
    }

    public void update() {
        y += dx;
        if (y < -imgheight) {
            y = 0;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);

        if (y < 0) {
            canvas.drawBitmap(image, x, y + imgheight, null);
        }
    }

    public void UpSpeed() {
        CountDownTimer timer = new CountDownTimer(1000000000, 5000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //interval, elke zo veel seconden moet er wat gebeuren
                dx = dx - 5;
                //bg.setVector(speed);
                //Toast.makeText(getContext(), "De speed gaat nu omhoog!!", Toast.LENGTH_SHORT).show();
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
