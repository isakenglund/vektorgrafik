package shapes;

import java.awt.*;
import shapes.style.Style;

public class Rectangle extends Shape {

    public Rectangle(double x, double y, double width, double height, Style style) {
        super(x,y,width,height, style);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        int drawX = (int) (getWidth() < 0 ? getPosition().getX() + getWidth() : getPosition().getX());
        int drawY = (int) (getHeight() < 0 ? getPosition().getY() + getHeight() : getPosition().getY());
        int drawWidth  = (int) Math.abs(getWidth());
        int drawHeight = (int) Math.abs(getHeight());

        g.drawRect(drawX, drawY, drawWidth, drawHeight);
    }
}
