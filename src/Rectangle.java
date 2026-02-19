import java.awt.*;

public class Rectangle implements Shape{

    private Point center;
    private double width, height;

    public Rectangle(double x, double y, double width, double height) {
        this.center = new Point(x,y);
        this.width = width;
        this.height = height;
    }
    public Rectangle(Point p, double width, double height) {
        this.center = p;
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(Graphics g) {
        g.drawRect((int)(center.getX()-width/2.0), (int)(center.getY()-height/2.0), (int)width, (int)height);
    }

    @Override
    public Point getPosition() {
        return this.center;
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
        return center.distanceTo(point) < width/2.0;
    }

    @Override
    public void moveTo(Point point) {
        center.moveTo(point);
    }

    @Override
    public void move(double dx, double dy) {
        center.move(dx, dy);
    }

    @Override
    public void resizeTo(Point point) {
        double distance = center.distanceTo(point);
        width = distance * 2;
        height = distance * 2;
    }

    @Override
    public Shape peel() {
        return this;
    }
}
