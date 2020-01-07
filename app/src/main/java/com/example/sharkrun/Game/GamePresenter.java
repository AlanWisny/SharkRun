package com.example.sharkrun.Game;

public class GamePresenter implements IGame.Presenter {
    private IGame.MvpView gView;

    GamePresenter(IGame.MvpView view){
        gView = view;
    }

    @Override
    public void handleMove() {
        gView.Move();
    }
}
