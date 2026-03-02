package shapes;

import java.awt.*;

public abstract class Shape
  {

    private Point topLeft;
    private double width, height;
    private boolean marked;

    public Shape(double x, double y, double width, double height) {
      this.topLeft = new Point(x,y);
      this.width = width;
      this.height = height;
      this.marked = false;
    }
    public Shape(Point p, double width, double height) {
      this(p.getX(),p.getY(),width,height);
      this.marked = false;
    }

    public abstract void draw(Graphics g);

    public Point getPosition() {
      return this.topLeft;
    }

    public double getWidth() {
      return this.width;
    }

    public double getHeight() {
      return this.height;
    }

    public boolean intersects(Point point) {
      double minX = Math.min(topLeft.getX(), topLeft.getX() + width);
      double maxX = Math.max(topLeft.getX(), topLeft.getX() + width);

      double minY = Math.min(topLeft.getY(), topLeft.getY() + height);
      double maxY = Math.max(topLeft.getY(), topLeft.getY() + height);

      return point.getX() >= minX && point.getX() <= maxX &&
              point.getY() >= minY && point.getY() <= maxY;
    }

    public void moveTo(Point point) {
      topLeft.moveTo(point);
    }

    public void move(double dx, double dy) {
      topLeft.move(dx, dy);
    }

    public void resizeTo(Point point) {
      this.width = point.getX() - topLeft.getX();
      this.height = point.getY() - topLeft.getY();;
    }

    public Shape peel() {
      return this;
    }

    public void setMarked(boolean marked) {
      this.marked = marked;
    }

    public boolean isMarked() {
      return marked;
    }

    public void rotateTo(Point point) {
      double dx = point.getX() - topLeft.getX();
      double dy = point.getY() - topLeft.getY();

      double theta = Math.atan2(dy, dx); // radianer

      double w = this.width;
      double h = this.height;

      double cos = Math.abs(Math.cos(theta));
      double sin = Math.abs(Math.sin(theta));

      this.width  = w * cos + h * sin;
      this.height = w * sin + h * cos;
    }
  }
