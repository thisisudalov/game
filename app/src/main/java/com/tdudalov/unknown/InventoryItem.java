package com.tdudalov.unknown;

import android.graphics.Bitmap;

public class InventoryItem {
    private Bitmap view;
    private Bitmap preview;
    public static final int PREVIEW_RES = 50;

    InventoryItem() {
        view = BitmapHandler.getClever();
        preview = Bitmap.createScaledBitmap(BitmapHandler.getClever(), PREVIEW_RES, PREVIEW_RES, false);
    }

    public Bitmap getPreview() {
        return preview;
    }

    public Bitmap getView() {
        return view;
    }
}
