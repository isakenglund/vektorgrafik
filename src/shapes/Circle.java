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
    Graphics2D g2 = (Graphics2D) g;

    double cx = getPosition().getX() + getWidth() / 2.0;
    double cy = getPosition().getY() + getHeight() / 2.0;

    var old = g2.getTransform();
    g2.rotate(getRotationRadians(), cx, cy);
    g2.drawOval((int) getPosition().getX(), (int) getPosition().getY(), (int) getWidth(), (int) getHeight());
    g2.setTransform(old);
  }
}