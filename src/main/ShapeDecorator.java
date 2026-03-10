package main;

import shapes.Point;
import shapes.Shape;

import java.awt.*;

public class ShapeDecorator extends Shape
  {
  private final Shape decoratee;

  public ShapeDecorator(Shape decoratee)
    {
      super(decoratee.getPosition(), decoratee.getWidth(), decoratee.getHeight(), decoratee.getStyle());
    this.decoratee = decoratee;
    }


    @Override
    public void draw(Graphics g)
    {
      Graphics2D g2 = (Graphics2D) g;
      int pX = (int) getPosition().getX();
      int pY = (int) getPosition().getY();
      final int markSize = 5;

      //marker
      g2.setColor(Color.RED);
      g2.setStroke(new BasicStroke());
      g2.drawLine(pX - markSize, pY - markSize, pX + markSize, pY + markSize);
      g2.drawLine(pX + markSize, pY - markSize, pX - markSize, pY + markSize);

      super.draw(g);
      decoratee.draw(g);
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

    @Override
    public Shape clone() {
      return new ShapeDecorator(this);
    }


  }
