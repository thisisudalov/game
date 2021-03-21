package com.tdudalov.unknown;

public class MapHolder {
    private InventoryItem[][] map = new InventoryItem [5][5];
    private static MapHolder instance;

    public static MapHolder getInstance() {
        if (instance == null) {
            instance = new MapHolder();
        }
        return instance;
    }

    public void setItem(int a, int b, InventoryItem item) {
        map[a][b] = item;
    }

    public InventoryItem[][] getMap() {
        return map;
    }
}
