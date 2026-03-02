package shapes;

import java.awt.*;

public class Line extends Shape {

    public Line(Point p1, double width, double height) {
        super(p1, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawLine((int) super.getPosition().getX(), (int) super.getPosition().getY(),
                (int) (super.getPosition().getX() + super.getWidth()), (int) (super.getPosition().getY() + super.getHeight()));

    }
}
