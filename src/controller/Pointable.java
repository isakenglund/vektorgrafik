package controller;

import model.shapes.Point;

public interface Pointable {
    void pointerDown(Point point);

    void pointerUp(Point point);

    void pointerMoved(Point point, boolean pointerDown);
}
