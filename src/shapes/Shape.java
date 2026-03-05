package shapes;

import shapes.style.Style;

import java.awt.*;

public abstract class Shape
  {

    private Point topLeft;
    private double width, height;
    private boolean marked;
    private Style style;

    public Shape(double x, double y, double width, double height, Style style) {
      this.topLeft = new Point(x,y);
      this.width = width;
      this.height = height;
      this.marked = false;
      this.style = style;
    }

    public void draw(Graphics g) {
      Graphics2D g2 = (Graphics2D) g;
      g2.setColor(style.getColor());
      g2.setStroke(new BasicStroke(style.getLineWidth()));
    }


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

    public Style getStyle() {
      return style;
    }

    /*
    public void rotateTo(Point point) {
      double dx = point.getX() - centerX();
      double dy = point.getY() - centerY();

      rotationRadians = Math.atan2(dy, dx); // radianer
      this.currentRotationRadians = rotationRadians;
    }
    */

  }
