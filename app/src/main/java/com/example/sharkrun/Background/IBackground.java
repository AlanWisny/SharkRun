package com.example.sharkrun.Background;

import android.graphics.Canvas;

public interface IBackground {
    void update();
    void draw(Canvas canvas);
    void UpSpeed();
}
