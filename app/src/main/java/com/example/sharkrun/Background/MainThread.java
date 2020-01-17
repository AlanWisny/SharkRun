package com.example.sharkrun.Background;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MainThread extends Thread {

    private int FPS = 30;
    private double averageFPS;
    private SurfaceHolder sh;
    private GamePanel gPanel;
    private boolean running;
    public static Canvas canvas;

    public MainThread(SurfaceHolder sh, GamePanel gPanel) {
        super();
        this.sh = sh;
        this.gPanel = gPanel;
    }

    @Override
    public void run() {
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;
        long frameCount = 0;
        long targetTime = 1000 / FPS;

        while (running) {
            startTime = System.nanoTime();
            canvas = null;

            //try locking the canvas for pixel editing
            try {
                canvas = this.sh.lockCanvas();
                synchronized (sh) {
                    this.gPanel.update();
                    this.gPanel.draw(canvas);
                }
            } catch (Exception e) {
            }
            finally {
                if(canvas != null){
                    try{
                        sh.unlockCanvasAndPost(canvas);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis = (System.nanoTime() - startTime) / 1000000;
            waitTime = targetTime - timeMillis;

            try {
                this.sleep(waitTime);
            } catch (Exception e) {}

            totalTime += System.nanoTime() - startTime;
            frameCount++;
            if(frameCount == FPS){
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount = 0;
                totalTime = 0;
                System.out.println("The averageFPS =" + averageFPS);
            }
        }
    }

    public void setRunning(boolean b){
        running = b;
    }
}
