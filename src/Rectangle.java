import java.awt.*;

public class Rectangle implements Shape{

    private Point center;
    private double width, height;
    private boolean marked;

    public Rectangle(double x, double y, double width, double height) {
        this.center = new Point(x,y);
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

        this.width = Math.abs(point.getX() - center.getX()) * 2.0;
        this.height = Math.abs(point.getY() - center.getY()) * 2.0;
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
