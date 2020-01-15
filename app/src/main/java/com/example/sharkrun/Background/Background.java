package com.example.sharkrun.Background;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.CountDownTimer;

public class Background implements IBackground {

    private Bitmap image;
    private static int WIDTH, HEIGHT;
    private Bitmap bm;
    private int x, y, dx;
    private int imgheight = 5498;
    //private int imgheight = 10521;


    public Background(Bitmap res) {
        image = res;
        x = 0;
        dx = GamePanel.speed;
        //Bitmap bm = Bitmap.createScaledBitmap(res, 600, imgheight, true);
        Bitmap bm = Bitmap.createScaledBitmap(res, res.getWidth(), res.getHeight(), true);
        //UpSpeed();
    }

    public void update() {
        y += dx;
        if (y < -imgheight) {
            y = 0;
        }
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(bm, x, y, null);

        if (y < 0) {
            canvas.drawBitmap(bm, x , y + imgheight, null);
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

    public static int getScreenWidth() {
        WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
        return WIDTH;
    }

    public static int getScreenHeight() {
        HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
        return HEIGHT;
    }
}
