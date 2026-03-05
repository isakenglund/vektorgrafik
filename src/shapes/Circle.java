package shapes;

import shapes.style.Style;

import java.awt.*;

public class Circle extends Shape {


  public Circle(double x, double y, double width, double height, Style style) {
    super(x, y, width, height, style);
  }


  @Override
  public void draw(Graphics g) {
    super.draw(g);

    g.drawOval((int) getPosition().getX(), (int) getPosition().getY(), (int) getWidth(), (int) getHeight());
  }
}