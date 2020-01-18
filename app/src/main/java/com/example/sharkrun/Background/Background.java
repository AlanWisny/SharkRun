package com.example.sharkrun.Background;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.CountDownTimer;

public class Background implements IBackground {

    private static int HEIGHT;
    private static int WIDTH;
    private Bitmap bm;
    private int x, y, dx;
    private int imgheight = 5498;

    public Background(Bitmap res) {
        dx = GamePanel.speed;
        WIDTH = getScreenWidth();
        HEIGHT = getScreenHeight();
        bm = Bitmap.createScaledBitmap(res, WIDTH, res.getHeight(), true);
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

    public Bitmap getResizedBitmap(Bitmap b, int newWidth, int newHeight){
        int width = b.getWidth();
        int height = b.getHeight();
        float scaleWidth = ((float) newWidth / width);
        float scaleHeight = ((float) newHeight / height);

        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap resizedBitmap = Bitmap.createBitmap(
                b, 0 ,0, width, height, matrix, false);
        b.recycle();

        return resizedBitmap;
    }

    public void setY(int y) {
        this.y = y;
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
