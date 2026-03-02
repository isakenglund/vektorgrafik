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
        Graphics2D g2 = (Graphics2D) g;

        int drawX = (int) (getWidth() < 0 ? getPosition().getX() + getWidth() : getPosition().getX());
        int drawY = (int) (getHeight() < 0 ? getPosition().getY() + getHeight() : getPosition().getY());
        int drawWidth  = (int) Math.abs(getWidth());
        int drawHeight = (int) Math.abs(getHeight());

        double cx = drawX + drawWidth / 2.0;
        double cy = drawY + drawHeight / 2.0;

        var old = g2.getTransform();
        g2.rotate(getRotationRadians(), cx, cy);
        g2.drawRect(drawX, drawY, drawWidth, drawHeight);
        g2.setTransform(old);
    }
}
