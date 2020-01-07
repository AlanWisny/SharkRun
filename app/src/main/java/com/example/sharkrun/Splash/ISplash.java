package com.example.sharkrun.Splash;

public interface ISplash {

    interface MvpView{
        void showSplash();
    }

    interface Presenter{
        void handleShowSplash();
    }
}
