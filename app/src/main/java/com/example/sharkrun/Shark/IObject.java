package com.example.sharkrun.Shark;

import android.graphics.Canvas;
import android.graphics.Rect;

public interface IObject {
    void draw(Canvas canvas);
    void update();
    void update(String direciton);

    //Rect getRectangle();
}
