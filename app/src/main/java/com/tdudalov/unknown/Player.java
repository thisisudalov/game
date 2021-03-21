package com.tdudalov.unknown;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private int x;
    private int y;
    private final Bitmap bitmap;
    private final RouteResolver routeResolver = new RouteResolver();
    private List<Point> moveRoute;
    private static Player instance;
    private Point touchPlace;

    private Player() {
        this.x = 50;
        this.y = 50;
        bitmap = BitmapHandler.getPlayer();
    }

    public static Player getInstance() {
        if (instance == null) {
            instance = new Player();
        }
        return instance;
    }

    public Bitmap getView() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTouchPlace(Point touchPlace) {
        this.touchPlace = touchPlace;
    }

    public void update() {
        if (touchPlace != null) {
            moveRoute = routeResolver.resolveDiag(x, y,
                    touchPlace.x - bitmap.getWidth()/2, touchPlace.y - bitmap.getHeight()/2);
        }
        makeMove();
    }

    private void makeMove() {
        if (moveRoute == null || moveRoute.isEmpty()) {
            return;
        }

        x = moveRoute.get(0).x;
        y = moveRoute.get(0).y;

        moveRoute.remove(0);
    }

    private static class RouteResolver {
        List<Point> resolveDiag(int x0, int y0, int x1, int y1) {
            List<Point> result = new ArrayList<>();
            int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

            dx = x1 - x0;
            dy = y1 - y0;

            incx = dx > 0 ? 1 : -1;
            incy = dy > 0 ? 1 : -1;

            if (dx < 0) dx = -dx;
            if (dy < 0) dy = -dy;
            if (dx > dy) {
                pdx = incx;
                pdy = 0;
                es = dy;
                el = dx;
            } else {
                pdx = 0;
                pdy = incy;
                es = dx;
                el = dy;
            }

            x = x0;
            y = y0;
            err = el / 2;
            for (int t = 0; t < el; t++) {
                err -= es;
                if (err < 0) {
                    err += el;
                    x += incx;
                    y += incy;
                } else {
                    x += pdx;
                    y += pdy;
                }
                result.add(new Point(x, y));
            }
            return result;
        }
    }
}
