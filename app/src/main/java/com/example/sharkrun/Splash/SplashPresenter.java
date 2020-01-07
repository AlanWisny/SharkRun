package com.example.sharkrun.Splash;

public class SplashPresenter implements ISplash.Presenter {
    private ISplash.MvpView sView;

    SplashPresenter(ISplash.MvpView view){
        sView = view;
    }

    @Override
    public void handleShowSplash() {
        sView.showSplash();
    }
}
