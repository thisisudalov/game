package com.tdudalov.unknown;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {
    private Thread gameThread;
    private Canvas canvas;
    private Paint paint;
    private SurfaceHolder surfaceHolder;
    private volatile boolean playing;
    private InventoryPanel inventoryPanel;
    private MapHolder mapHolder;

    private int maxX;
    private int maxY;

    private final Player player;

    public GameView(Context context, Point size) {
        super(context);
        maxX = size.x;
        maxY = size.y;
        surfaceHolder = getHolder();
        paint = new Paint();
        player = Player.getInstance();
        mapHolder = MapHolder.getInstance();
        inventoryPanel = InventoryPanel.getInstance();
    }

    @Override
    public void run() {
        while (playing) {
            update();
            draw();
            control();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TouchEventResolver.resolveTouch(event);
        return true;
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            canvas = surfaceHolder.lockCanvas();
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            drawMap();
            canvas.drawBitmap(player.getView(), player.getX(), player.getY(), paint);
            drawInventory();
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void drawMap() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                InventoryItem item = mapHolder.getMap()[i][j];
                if (item != null) {
                    canvas.drawBitmap(item.getView(), i * 80, i * 80, paint);
                }
            }
        }
    }

    private void drawHUD() {
        paint.setColor(Color.RED);
        paint.setAlpha(125);
        canvas.drawRect(inventoryPanel.getPanel(), paint);
        paint.setAlpha(255);
    }

    private void drawInventory() {
        drawHUD();
        int x = inventoryPanel.getLeftX();
        int y = inventoryPanel.getUpperY();
        Integer selected = inventoryPanel.getSelected();
        for (int i = 0; i < inventoryPanel.getItems().size(); i++) {
            canvas.drawBitmap(inventoryPanel.getItems().get(i).getPreview(), x, y, paint);
            if (selected != null && selected == i) {
                paint.setColor(Color.BLACK);
                canvas.drawLine(x, y, x + InventoryItem.PREVIEW_RES, y, paint);
                canvas.drawLine(x, y, x, y + InventoryItem.PREVIEW_RES, paint);
                canvas.drawLine(x + InventoryItem.PREVIEW_RES, y, x + InventoryItem.PREVIEW_RES, y + InventoryItem.PREVIEW_RES, paint);
                canvas.drawLine(x, y + InventoryItem.PREVIEW_RES, x + InventoryItem.PREVIEW_RES, y + InventoryItem.PREVIEW_RES, paint);
            }
            x += InventoryItem.PREVIEW_RES + InventoryPanel.SPACE_BETWEEN_ITEMS;
        }
    }

    private void update() {
        player.update();
    }

    private void control() {
        try {
            gameThread.sleep(17);
        } catch (InterruptedException e) {}
    }


    public void pause() {
        playing = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {}
    }

    public void resume() {
        playing = true;
        gameThread = new Thread(this);
        gameThread.start();
    }
}
