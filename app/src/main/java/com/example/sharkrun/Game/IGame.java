package com.example.sharkrun.Game;

public interface IGame {

    interface MvpView{
        void Move();
    }

    interface Presenter{
        void handleMove();
    }
}
