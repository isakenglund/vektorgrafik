package shapes;

import java.awt.*;

public class Rectangle implements Shape {

    private Point topLeft;
    private double width, height;
    private boolean marked;

    public Rectangle(double x, double y, double width, double height) {
        this.topLeft = new Point(x,y);
        this.width = width;
        this.height = height;
        this.marked = false;
    }
    public Rectangle(Point p, double width, double height) {
        this(p.getX(),p.getY(),width,height);
        this.marked = false;
    }

    @Override
    public void draw(Graphics g) {

        g.setColor(Color.black);
        int drawX = (int) (width < 0 ? topLeft.getX() + width : topLeft.getX());
        int drawY = (int) (height < 0 ? topLeft.getY() + height : topLeft.getY());

        int drawWidth = (int) Math.abs(width);
        int drawHeight = (int) Math.abs(height);

        g.drawRect(drawX, drawY, drawWidth, drawHeight);
    }

    @Override
    public Point getPosition() {
        return this.topLeft;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public boolean intersects(Point point) {
        double minX = Math.min(topLeft.getX(), topLeft.getX() + width);
        double maxX = Math.max(topLeft.getX(), topLeft.getX() + width);

        double minY = Math.min(topLeft.getY(), topLeft.getY() + height);
        double maxY = Math.max(topLeft.getY(), topLeft.getY() + height);

        return point.getX() >= minX && point.getX() <= maxX &&
                point.getY() >= minY && point.getY() <= maxY;
    }

    @Override
    public void moveTo(Point point) {
        topLeft.moveTo(point);
    }

    @Override
    public void move(double dx, double dy) {
        topLeft.move(dx, dy);
    }

    @Override
    public void resizeTo(Point point) {
        this.width = point.getX() - topLeft.getX();
        this.height = point.getY() - topLeft.getY();;
    }

    @Override
    public Shape peel() {
        return this;
    }

    @Override
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public boolean isMarked() {
        return marked;
    }
}
