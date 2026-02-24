package Main;

import Shapes.Point;
import Shapes.Shape;

import java.awt.*;

public abstract class ShapeDecorator implements Shape
  {
  private final Shape decoratee;

  public ShapeDecorator(Shape decoratee)
    {
    this.decoratee = decoratee;
    }
  @Override
  public void draw(Graphics g)
    {
    decoratee.draw(g);
    g.setColor(Color.RED);

    }
  @Override
  public Point getPosition()
    {
    return decoratee.getPosition();
    }
  @Override
  public double getWidth()
    {
    return decoratee.getWidth();
    }
  @Override
  public double getHeight()
    {
    return decoratee.getHeight();
    }
  @Override
  public boolean intersects(Point point)
    {
    return decoratee.intersects(point);
    }
  @Override
  public void moveTo(Point point)
    {
    decoratee.moveTo(point);
    }
  @Override
  public void move(double dx, double dy)
    {
    decoratee.move(dx, dy);
    }
  @Override
  public void resizeTo(Point point)
    {
    decoratee.resizeTo(point);
    }
  @Override
  public Shape peel()
    {
    return decoratee;
    }

    @Override
    public void setMarked(boolean marked) {
      this.decoratee.setMarked(marked);
    }

    @Override
    public boolean isMarked() {
      return decoratee.isMarked();
    }


  }
