package com.example.sharkrun.Menu;

public interface IMenu {

    interface MvpView{
        void StartMenuButton();
        void QuitMenuButton();
    }

    interface Presenter{
        void handleStartMenuButton();
        void handleQuitMenuButton();
    }
}
