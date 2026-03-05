package shapes;

import shapes.style.Style;

import java.awt.*;

public class Triangle extends Shape {

    public Triangle(double x, double y, double width, double height, Style style) {
        super(x, y, width, height, style);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int rightX = (int) (getPosition().getX() + getWidth());
        int middleX = (int) (getPosition().getX() + getWidth() / 2);
        int leftX = (int) (getPosition().getX());

        int topY = (int) (getPosition().getY());
        int bottomY = (int) (getPosition().getY() + getHeight());

        g.drawPolygon(new int[]{leftX, middleX, rightX},
                new int[]{bottomY, topY, bottomY}, 3);
    }
}