package shapes;

import java.awt.*;
import java.util.List;

public class CompositeShape implements Shape, Composite {

    private Point topLeft;
    private double width, height;
    private boolean marked;
    private List<Shape> shapesList;

    public CompositeShape(double x, double y, double width, double height, List<Shape> shapesList)
    {
        topLeft = new Point(x,y);
        this.width = width;
        this.height = height;
        this.marked = false;
        this.shapesList = shapesList;

    }

    public CompositeShape(Point point, double width, double height, List<Shape> shapesList)
    {
        this(point.getX(), point.getY(),width,height, shapesList);
        this.marked = false;
    }

    @Override
    public void draw(Graphics g)
    {
        g.setColor(Color.black);
        shapesList.forEach(shape -> shape.draw(g));

        int drawX = (int) (width < 0 ? topLeft.getX() + width : topLeft.getX());
        int drawY = (int) (height < 0 ? topLeft.getY() + height : topLeft.getY());
        int drawWidth = (int) Math.abs(width);
        int drawHeight = (int) Math.abs(height);

        g.drawRect(drawX, drawY, drawWidth, drawHeight);
    }

    @Override
    public Point getPosition()
    {
        return topLeft;
    }

    @Override
    public double getWidth()
    {
        return this.width;
    }

    @Override
    public double getHeight()
    {
        return this.height;
    }

    @Override
    public boolean intersects(Point point)
    {
        double minX = Math.min(topLeft.getX(), topLeft.getX() + width);
        double maxX = Math.max(topLeft.getX(), topLeft.getX() + width);

        double minY = Math.min(topLeft.getY(), topLeft.getY() + height);
        double maxY = Math.max(topLeft.getY(), topLeft.getY() + height);

        return point.getX() >= minX && point.getX() <= maxX &&
                point.getY() >= minY && point.getY() <= maxY;
    }

    @Override
    public void moveTo(Point point)
    {
        double dx = point.getX() - topLeft.getX();
        double dy = point.getY() - topLeft.getY();
        for(Shape shape : shapesList) shape.move(dx, dy);
        topLeft.moveTo(point);
    }

    @Override
    public void move(double dx, double dy)
    {
        for(Shape shape : shapesList) shape.move(dx, dy);
        topLeft.move(dx, dy);
    }

    @Override
    public void resizeTo(Point point) {
        double oldWidth = this.width;
        double oldHeight = this.height;

        this.width = point.getX() - topLeft.getX();
        this.height = point.getY() - topLeft.getY();

        if (oldWidth == 0 || oldHeight == 0) {
            return;
        }

        double scaleX = this.width / oldWidth;
        double scaleY = this.height / oldHeight;

        for (Shape shape : shapesList) {
            double relativeX = (shape.getPosition().getX() - topLeft.getX()) * scaleX;
            double relativeY = (shape.getPosition().getY() - topLeft.getY()) * scaleY;

            shape.moveTo(new Point(topLeft.getX() + relativeX, topLeft.getY() + relativeY));

            double newShapeWidth = shape.getWidth() * scaleX;
            double newShapeHeight = shape.getHeight() * scaleY;

            Point newEndCorner = new Point(
                    shape.getPosition().getX() + newShapeWidth,
                    shape.getPosition().getY() + newShapeHeight
            );

            shape.resizeTo(newEndCorner);
        }
    }

    @Override
    public Shape peel()
    {
        return new CompositeShape(topLeft, width, height, shapesList);
    }

    @Override
    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    @Override
    public boolean isMarked() {
        return this.marked;
    }

    @Override
    public List<Shape> getChildren() {
        return shapesList;
    }
}
