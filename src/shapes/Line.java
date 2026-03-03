package shapes;

import java.awt.*;

public class Line extends Shape {

    public Line(Point p1, double width, double height) {
        super(p1, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        double cx = getPosition().getX() + getWidth() / 2.0;
        double cy = getPosition().getY() + getHeight() / 2.0;

        var old = g2.getTransform();
        g2.rotate(getRotationRadians(), cx, cy);
        g2.drawLine((int) getPosition().getX(), (int) getPosition().getY(),
                (int) (getPosition().getX() + getWidth()), (int) (getPosition().getY() + getHeight()));
        g2.setTransform(old);
    }
}
