package main;

import shapes.Point;
import shapes.Shape;

import java.awt.*;

public class ShapeDecorator extends Shape
  {
  private final Shape decoratee;

  public ShapeDecorator(Shape decoratee)
    {
      super(decoratee.getPosition().getX(),decoratee.getPosition().getY(), decoratee.getWidth(), decoratee.getHeight(), decoratee.getStyle());
    this.decoratee = decoratee;
    }
    @Override
    public void draw(Graphics g)
    {
      super.draw(g);
      Point position = getPosition();
      double width = getWidth();
      double height = getHeight();

      int x = (int)(width < 0 ? position.getX() + width - 1 : position.getX() - 1);
      int y = (int)(height < 0 ? position.getY() + height - 1 : position.getY() - 1);
      int w = (int)(Math.abs(width) + 2);
      int h = (int)(Math.abs(height) + 2);

      g.drawRect(x, y, w, h);

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
