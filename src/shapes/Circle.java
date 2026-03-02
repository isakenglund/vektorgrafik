package shapes;

import java.awt.*;

public class Circle extends Shape {


  public Circle(double x, double y, double width, double height) {
    super(x, y, width, height);

  }

  public Circle(Point point, double width, double height) {
    this(point.getX(), point.getY(), width, height);
  }

  @Override
  public void draw(Graphics g) {
    g.drawOval((int) super.getPosition().getX(), (int) super.getPosition().getY(), (int) super.getWidth(), (int) super.getHeight());
  }
}