package shapes;

import java.awt.*;
import shapes.style.Style;

public class Rectangle extends Shape {

    public Rectangle(Point p, double width, double height, Style style) {
        super(p,width,height, style);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);


        int rX = (int) (getPosition().getX() + getWidth()/2);
        int lX = (int) (getPosition().getX() - getWidth()/2);

        int tY = (int) (getPosition().getY() - getHeight()/2);
        int bY = (int) (getPosition().getY() + getHeight()/2);

        g.drawPolygon(new int[]{lX, lX, rX,rX}, new int[]{tY,bY,bY,tY}, 4);

        //g.drawRect((int) (getPosition().getX()-getWidth()/2), (int) (getPosition().getY()-getHeight()/2), (int) getWidth(), (int) getHeight());
    }
}
