package com.tdudalov.unknown;

import android.graphics.Point;
import android.view.MotionEvent;

public class TouchEventResolver {
    public static void resolveTouch(MotionEvent event) {
        Player player = Player.getInstance();
        MapHolder mapHolder = MapHolder.getInstance();
        InventoryPanel panel = InventoryPanel.getInstance();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_UP:
                player.setTouchPlace(null);
                break;
            case MotionEvent.ACTION_DOWN:
                Point touchPlace = new Point((int) event.getX(), (int) event.getY());
                if (panel.touchedPanel(touchPlace)) {
                    panel.setSelected(touchPlace);
                } else {
                    if (panel.getSelected() != null) {
                        mapHolder.setItem(0, 0, new InventoryItem());
                    }
                    player.setTouchPlace(touchPlace);
                }
                break;
        }
    }
}
