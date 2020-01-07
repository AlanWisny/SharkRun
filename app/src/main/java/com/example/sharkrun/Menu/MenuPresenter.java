package com.example.sharkrun.Menu;

public class MenuPresenter implements IMenu.Presenter{
    private IMenu.MvpView mView;

    MenuPresenter(IMenu.MvpView view){
        mView = view;
    }

    @Override
    public void handleStartMenuButton() {
        mView.StartMenuButton();
    }

    @Override
    public void handleQuitMenuButton() {
        mView.QuitMenuButton();
    }
}
