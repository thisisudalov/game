package com.tdudalov.unknown;

import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.List;

public class InventoryPanel {
    private final int MAX_CAPACITY = 5;
    private List<InventoryItem> items = new ArrayList<>(MAX_CAPACITY);
    public static final int SPACE_BETWEEN_ITEMS = 30;
    public static final int SPACE_FROM_FLOOR = 40;
    private static final int ITEM_RES = InventoryItem.PREVIEW_RES;
    private final int leftX;
    private final int upperY;
    private Integer selected;
    private static InventoryPanel instance;
    private final Rect panel;
    private List<Rect> itemsDisposition = new ArrayList<>(MAX_CAPACITY);

    private InventoryPanel(Point res) {
        leftX = res.x/2 - ITEM_RES/2 - SPACE_BETWEEN_ITEMS -
                ITEM_RES - SPACE_BETWEEN_ITEMS - ITEM_RES;
        upperY = res.y - SPACE_FROM_FLOOR - ITEM_RES;
        int itemDispX = leftX;
        for (int i = 0; i < MAX_CAPACITY; i++) {
            items.add(new InventoryItem());
            itemsDisposition.add(new Rect(itemDispX, upperY, itemDispX + ITEM_RES, upperY + ITEM_RES));
            itemDispX += ITEM_RES + SPACE_BETWEEN_ITEMS;
        }

        panel = new Rect(leftX, upperY,
                leftX + MAX_CAPACITY * ITEM_RES + (MAX_CAPACITY - 1) * SPACE_BETWEEN_ITEMS,
                res.y - SPACE_FROM_FLOOR);
    }

    public static void init(Point res) {
        instance = new InventoryPanel(res);
    }

    public static InventoryPanel getInstance() {
        return instance;
    }

    public void setItem(InventoryItem item, int index) {
        items.remove(index);
        items.add(index, item);
    }

    public Rect getPanel() {
        return panel;
    }

    public void setSelected(Point touchPlace) {
        for (int i = 0; i < MAX_CAPACITY; i++) {
            if (itemsDisposition.get(i).contains(touchPlace.x, touchPlace.y)) {
                if (selected != null && selected == i) {
                    unselect();
                } else {
                    selected = i;
                }
            }
        }
    }

    public void unselect() {
        selected = null;
    }

    public boolean touchedPanel(Point touchPlace) {
        return panel.contains(touchPlace.x, touchPlace.y);
    }

    public Integer getSelected() {
        return selected;
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getUpperY() {
        return upperY;
    }
}
