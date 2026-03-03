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
        Graphics2D g2 = (Graphics2D) g;

        int rightX = (int) (getPosition().getX() + getWidth());
        int middleX = (int) (getPosition().getX() + getWidth() / 2);
        int leftX = (int) (getPosition().getX());

        int topY = (int) (getPosition().getY());
        int bottomY = (int) (getPosition().getY() + getHeight());

        double cx = leftX + getWidth() / 2.0;
        double cy = bottomY - getHeight() / 3.0;

        var old = g2.getTransform();
        g2.rotate(getRotationRadians(),cx,cy);

        g2.drawPolygon(new int[]{leftX, middleX, rightX},
                new int[]{bottomY, topY, bottomY}, 3);
        g2.setTransform(old);
    }
}