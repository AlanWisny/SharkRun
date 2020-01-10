package com.example.sharkrun.Shark;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import static com.example.sharkrun.Background.MainThread.canvas;

public class Shark implements IObject {
    private Bitmap image;
    private float x, y;
    private float width, height;

    public Shark(Bitmap bm) {
        image = bm;
    }

    public void update(){}

//    public void update(String dir){
//        switch(dir){
//            case "top":
//                //draw(canvas);
//                break;
//            case "bot":
//                //draw(canvas);
//                break;
//            case "left":
////                 x = (canvas.getWidth() - image.getWidth()) / 3;
////                 y = canvas.getHeight() - image.getHeight() - 100;
//                break;
//            case "right":
////                x = (canvas.getWidth() - image.getWidth()) ;
////                y = canvas.getHeight() - image.getHeight() - 100;
//                break;
//            default:
//                //draw(canvas);
//        }
//    }

    public void update(String dir) {
        //als motionevents dan update
        switch(dir){
            case "top":
                drawUp(canvas);
                break;
            case "bot":
                drawDown(canvas);
                break;
            case "left":
                drawLeft(canvas);
                break;
            case "right":
                drawRight(canvas);
                break;
            default:
                draw(canvas);
        }
    }

    public void draw(Canvas canvas) {
        float x = (canvas.getWidth() - image.getWidth()) / 2;
        float y = canvas.getHeight() - image.getHeight() - 100;
        canvas.drawBitmap(image, x, y, null);
    }

    public void drawLeft(Canvas canvas) {
        float x = (canvas.getWidth() - image.getWidth()) / 4;
        float y = canvas.getHeight() - image.getHeight() - 100;
        canvas.drawBitmap(image, x, y, null);
    }

    public void drawRight(Canvas canvas) {
        float x = (canvas.getWidth() - image.getWidth()) ;
        float y = canvas.getHeight() - image.getHeight() - 100;
        canvas.drawBitmap(image, x, y, null);
    }

    public void drawUp(Canvas canvas) {
        float x = (canvas.getWidth() - image.getWidth()) / 2;
        float y = canvas.getHeight() - image.getHeight() - 100;
        canvas.drawBitmap(image, x, y, null);
    }

    public void drawDown(Canvas canvas) {
        float x = (canvas.getWidth() - image.getWidth()) / 2;
        float y = canvas.getHeight() - image.getHeight() - 100;
        canvas.drawBitmap(image, x, y, null);
    }

//    @Override
//    public Rect getRectangle() {
//        return new Rect(x, y, x + width, y + height);
//    }
}
