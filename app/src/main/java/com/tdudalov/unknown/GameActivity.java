package com.tdudalov.unknown;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

import androidx.annotation.Nullable;

public class GameActivity extends Activity {

    private GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        initSingletons(size);
        gameView = new GameView(this, size);
        setContentView(gameView);
    }

    private void initSingletons(Point size) {
        BitmapHandler.instance(this);
        InventoryPanel.init(size);
    }


    @Override
    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
}
