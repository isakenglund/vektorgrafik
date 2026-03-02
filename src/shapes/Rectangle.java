package shapes;

import java.awt.*;

public class Rectangle extends Shape {

    public Rectangle(double x, double y, double width, double height) {
        super(x,y,width,height);
    }
    public Rectangle(Point p, double width, double height) {
        this(p.getX(),p.getY(),width,height);
    }

    @Override
    public void draw(Graphics g) {

        int drawX = (int) (super.getWidth() < 0 ? super.getPosition().getX() + super.getWidth() : super.getPosition().getX());
        int drawY = (int) (super.getHeight() < 0 ? super.getPosition().getY() + super.getHeight() : super.getPosition().getY());

        int drawWidth = (int) Math.abs(super.getWidth());
        int drawHeight = (int) Math.abs(super.getHeight());

        g.drawRect(drawX, drawY, drawWidth, drawHeight);
    }
}
