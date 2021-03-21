package com.tdudalov.unknown;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapHandler {
    private static BitmapHandler singleton;

    private static Bitmap PLAYER_VIEW;
    private static Bitmap CLEVER;
    private BitmapHandler(Context context) {
        PLAYER_VIEW = BitmapFactory.decodeResource(context.getResources(), R.drawable.player);
        CLEVER = BitmapFactory.decodeResource(context.getResources(), R.drawable.clever);
    }

    public static BitmapHandler instance(Context context) {
        if (singleton == null) {
            singleton = new BitmapHandler(context);
        }
        return singleton;
    }

    public static Bitmap getPlayer() {
        return PLAYER_VIEW;
    }

    public static Bitmap getClever() {
        return CLEVER;
    }
}
