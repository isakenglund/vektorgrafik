package shapes;

import java.awt.*;

public class Triangle extends Shape {

    public Triangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Triangle(Point p, double width, double height) {
        this(p.getX(), p.getY(), width, height);
    }


    @Override
    public void draw(Graphics g) {

        int rightX = (int) (super.getPosition().getX() + super.getWidth());
        int middleX = (int) (super.getPosition().getX() + super.getWidth() / 2);
        int leftX = (int) (super.getPosition().getX());

        int topY = (int) (super.getPosition().getY());
        int bottomY = (int) (super.getPosition().getY() + super.getHeight());

        g.drawPolygon(new int[]{leftX, middleX, rightX},
                new int[]{bottomY, topY, bottomY}, 3);

    }
}