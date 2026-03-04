package shapes;

import main.LineWidth;

import java.awt.*;

public abstract class Shape implements Cloneable
  {

    private Point topLeft;
    private double width, height;
    private boolean marked;
    private double rotationRadians = 0.0;
    private double currentRotationRadians = 0.0;

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

    public void draw(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setStroke(new BasicStroke(LineWidth.getInstance().getWidth()));
    };

    public double getRotationRadians() { return rotationRadians; }

    protected double centerX() { return topLeft.getX() + width / 2.0; }
    protected double centerY() { return topLeft.getY() + height / 2.0; }

    public Point getPosition() {
      return this.topLeft;
    }

    public double getWidth() {
      return this.width;
    }

    public void setWidth(double width) {
      this.width = width;
    }

    public void setHeight(double height) {
      this.height = height;
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

      double cx = centerX();
      double cy = centerY();

      double dx = point.getX() - cx;
      double dy = point.getY() - cy;

      double cos = Math.cos(currentRotationRadians);
      double sin = Math.sin(currentRotationRadians);

      double localX =  cos * dx + sin * dy;
      double localY = -sin * dx + cos * dy;

      this.width  = localX;
      this.height = localY;
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
      double dx = point.getX() - centerX();
      double dy = point.getY() - centerY();

      rotationRadians = Math.atan2(dy, dx); // radianer
      this.currentRotationRadians = rotationRadians;
    }

      @Override
      public Shape clone() {
          try {
              Shape clone = (Shape) super.clone();
              clone.topLeft = new Point(topLeft);
              clone.width = width;
              clone.height = height;
              clone.marked = marked;
              clone.rotationRadians = rotationRadians;
              clone.currentRotationRadians = currentRotationRadians;
              // TODO: copy mutable state here, so the clone can't change the internals of the original
              return clone;
          } catch (CloneNotSupportedException e) {
              throw new AssertionError();
          }
      }
  }
