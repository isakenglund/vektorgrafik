package shapes;

import java.awt.*;

public class Line extends Shape {

    public Line(Point p1, double width, double height) {
        super(p1, width, height);
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        double cx = super.getPosition().getX() + super.getWidth() / 2.0;
        double cy = super.getPosition().getY() + super.getHeight() / 2.0;

        var old = g2.getTransform();
        g2.rotate(getRotationRadians(), cx, cy);
        g2.drawLine((int) super.getPosition().getX(), (int) super.getPosition().getY(),
                (int) (super.getPosition().getX() + super.getWidth()), (int) (super.getPosition().getY() + super.getHeight()));
        g2.setTransform(old);
    }
}
