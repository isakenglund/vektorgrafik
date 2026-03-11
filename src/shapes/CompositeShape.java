package shapes;

import shapes.style.Style;
import visitor.ShapeVisitor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeShape extends Shape implements Composite {

    private List<Shape> shapesList;

    public CompositeShape(Point p, double width, double height, List<Shape> shapesList, Style style)
    {
        super(p,width,height, style);
        this.shapesList = new ArrayList<>(shapesList);
    }

    public CompositeShape(CompositeShape compositeShape) {
        super(compositeShape);
        this.shapesList = compositeShape.getChildren().stream().map(Shape::clone).toList();
    }


    @Override
    public void draw(Graphics g)
    {
        shapesList.forEach(shape -> {shape.draw(g);});
    }

    @Override
    public void setStyle(Style style){
        shapesList.forEach(shape -> {shape.setStyle(style);});
    }


    @Override
    public void moveTo(Point point)
    {
        double dx = point.getX() - getPosition().getX();
        double dy = point.getY() - getPosition().getY();
        super.moveTo(point);
        for(Shape shape : shapesList){
            shape.move(dx, dy);
        };
    }

    @Override
    public void move(double dx, double dy)
    {
        for(Shape shape : shapesList) shape.move(dx, dy);
        getPosition().move(dx, dy);
    }

    @Override
    public void resizeTo(Point point) {
        double oldWidth = getWidth();
        double oldHeight = getHeight();

        super.resizeTo(point);

        if (oldWidth == 0 || oldHeight == 0) {
            return;
        }

        double scaleX = getWidth() / oldWidth;
        double scaleY = getHeight() / oldHeight;

        for (Shape shape : shapesList) {

            double relativeX = (shape.getPosition().getX() - getPosition().getX()) * scaleX;
            double relativeY = (shape.getPosition().getY() - getPosition().getY()) * scaleY;

            Point newCenter = new Point(
                    getPosition().getX() + relativeX,
                    getPosition().getY() + relativeY
            );
            shape.moveTo(newCenter);

            double newHalfWidth = (shape.getWidth() * scaleX) / 2.0;
            double newHalfHeight = (shape.getHeight() * scaleY) / 2.0;

            Point newCorner = new Point(
                    shape.getPosition().getX() + newHalfWidth,
                    shape.getPosition().getY() + newHalfHeight
            );

            shape.resizeTo(newCorner);
        }
    }

    public Shape peel()
    {
        return this;
    }

    @Override
    public Shape clone() {
        return new CompositeShape(this);
    }

    @Override
    public String toCSV() {
        return "";
    }

    @Override
    public List<Shape> getChildren() {
        return shapesList;
    }

    @Override
    public boolean intersects(Point point) {
        return shapesList.stream().anyMatch(shape -> shape.intersects(point));
    }

    /*
    @Override
    public void rotateTo(Point point) {
        for(Shape shape : shapesList) shape.rotateTo(point);
        super.rotateTo(point);
    }

     */
}
