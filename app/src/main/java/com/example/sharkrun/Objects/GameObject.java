package com.example.sharkrun.Objects;

import android.graphics.Rect;

public abstract class GameObject {
    protected float x;
    protected float y;
    protected float dx;
    protected int dy;
    protected int width;
    protected int height;
    protected float goTo;

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getGoTo(){return goTo;}

    public void setGoTo(float goTo) {
        this.goTo = goTo;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float getDx() {
        return dx;
    }

    public void setDx(float dx) {
        this.dx = dx;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rect getRectangle() {
        return new Rect((int)x, (int)y, (int)x + width, (int)y + height);
    }
}
