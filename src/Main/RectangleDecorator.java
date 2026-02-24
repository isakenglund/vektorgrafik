package Main;


import Shapes.Point;
import Shapes.Shape;

import java.awt.*;

public class RectangleDecorator extends ShapeDecorator{


    public RectangleDecorator(Shape decoratee) {
        super(decoratee);
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        Point position = super.getPosition();
        int x = (int)(position.getX()-super.getWidth()/2.0-1);
        int y = (int)(position.getY()-super.getHeight()/2.0-1);
        g.drawRect(x,y,(int)(super.getWidth()+2),(int)(super.getHeight()+2));
    }
}
