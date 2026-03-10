package shapes;

import shapes.style.Style;

import java.awt.*;

public class Circle extends Shape {


  public Circle(Point p, double width, double height, Style style) {
    super(p, width, height, style);
  }

  public Circle(Circle circle) {
    super(circle);
  }


  @Override
  public void draw(Graphics g) {
    super.draw(g);

    g.drawOval((int) (getPosition().getX()-getWidth()/2), (int) (getPosition().getY()-getHeight()/2), (int) getWidth(), (int) getHeight());
  }

  @Override
  public Shape clone() {
    return new Circle(this);
  }
}