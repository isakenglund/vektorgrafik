package model.shapes;

import model.shapes.style.Style;
import model.visitor.ShapeVisitor;

import java.awt.*;

public abstract class Shape
  {
    private Point center;
    private double width, height;
    private boolean marked;
    private Style style;

      public Shape(Shape shape) {
      this.center = new Point(shape.getPosition());
      this.width = shape.getWidth();
      this.height = shape.getHeight();
      this.marked = false;
      this.style = shape.getStyle();
    }

    public Shape(Point p, double width, double height, Style style) {
      this.center = new Point(p.getX()+width/2, p.getY()+height/2);
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
      return this.center;
    }

    public void setPosition(Point point){
        this.center = point;
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
      double halfWidth = Math.abs(width) / 2.0;
      double halfHeight = Math.abs(height) / 2.0;

      double minX = center.getX() - halfWidth;
      double maxX = center.getX() + halfWidth;

      double minY = center.getY() - halfHeight;
      double maxY = center.getY() + halfHeight;

      return point.getX() >= minX && point.getX() <= maxX &&
              point.getY() >= minY && point.getY() <= maxY;
    }

    public void moveTo(Point point) {
      center.moveTo(point);
    }

    public void move(double dx, double dy) {
      center.move(dx, dy);
    }

    public void resizeTo(Point point) {
      this.width = (point.getX() - center.getX())*2;
      this.height = (point.getY() - center.getY())*2;;
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

    public void setStyle(Style style) {
      this.style = style;
    }

    public abstract Shape clone();


    public void accept(ShapeVisitor visitor){
      visitor.visit(this);
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
