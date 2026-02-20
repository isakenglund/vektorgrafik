import java.awt.*;

public class CircleDecorator extends ShapeDecorator{


    public CircleDecorator(Shape decoratee) {
        super(decoratee);
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        Point position = super.getPosition();
        int x = (int)(position.getX()-super.getWidth()/2.0+0.5) + 1;
        int y = (int)(position.getY()-super.getHeight()/2.0+0.5) + 1;
        g.drawOval(x,y,(int)(super.getWidth()+0.5),(int)(super.getHeight()+0.5));
    }
}
