package model.shapes;

import model.shapes.style.Style;

import java.awt.*;

public class Triangle extends Shape {


    public Triangle(Point p, double width, double height, Style style) {
        super(p, width, height, style);
    }

    public Triangle(Triangle triangle) {
        super(triangle);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int rightX = (int) (getPosition().getX() + getWidth() / 2);
        int middleX = (int) (getPosition().getX());
        int leftX = (int) (getPosition().getX() - getWidth() / 2);

        int topY = (int) (getPosition().getY() - getHeight() / 2);
        int bottomY = (int) (getPosition().getY() + getHeight() / 2);

        g.drawPolygon(new int[]{leftX, middleX, rightX}, new int[]{bottomY, topY, bottomY}, 3);
    }

    @Override
    public Shape clone() {
        return new Triangle(this);
    }
}