package Shapes;

import java.awt.*;
import java.util.List;

public class CompositeShape implements Shape {

    private Point center;
    private double width, height;
    private boolean marked;
    private List<Shape> shapesList;

    public CompositeShape(double x, double y, double width, double height, List<Shape> shapesList)
    {
        center = new Point(x,y);
        this.width = width;
        this.height = height;
        this.marked = false;
        this.shapesList = shapesList;

    }

    public CompositeShape(Point point, double width, double height, List<Shape> shapesList)
    {
        this(point.getX(), point.getY(),width,height, shapesList);
        this.marked = false;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.black);
        shapesList.forEach(shape -> shape.draw(g));
        g.drawRect((int)(center.getX()), (int)(center.getY()), (int)width, (int)height);
    }

    @Override
    public Point getPosition()
    {
        return center;
    }

    @Override
    public double getWidth()
    {
        return this.width;
    }

    @Override
    public double getHeight()
    {
        return this.height;
    }

    @Override
    public boolean intersects(Point point)
    {
        return center.distanceTo(point) < width/2.0;
    }

    @Override
    public void moveTo(Point point)
    {
        double dx = point.getX() - center.getX();
        double dy = point.getY() - center.getY();
        for(Shape shape : shapesList) shape.move(dx, dy);
        center.moveTo(point);
    }

    @Override
    public void move(double dx, double dy)
    {
        for(Shape shape : shapesList) shape.move(dx, dy);
        center.move(dx, dy);
    }

    @Override
    public void resizeTo(Point point)
    {
        this.width = Math.abs(point.getX() - center.getX()) * 2.0;
        this.height = Math.abs(point.getY() - center.getY()) * 2.0;
    }

    @Override
    public Shape peel()
    {
        return this;
    }

    @Override
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public boolean isMarked() {
        return this.marked;
    }
}
