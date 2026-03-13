package model.shapes;

import model.shapes.style.Style;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CompositeShape extends model.shapes.Shape implements model.shapes.Composite {

    private List<model.shapes.Shape> shapesList;

    public CompositeShape(model.shapes.Point p, double width, double height, List<model.shapes.Shape> shapesList, Style style) {
        super(p, width, height, style);
        this.shapesList = new ArrayList<>(shapesList);
    }

    public CompositeShape(CompositeShape compositeShape) {
        super(compositeShape);
        this.shapesList = compositeShape.getChildren().stream().map(model.shapes.Shape::clone).toList();
    }


    @Override
    public void draw(Graphics g) {
        shapesList.forEach(shape -> {
            shape.draw(g);
        });
    }

    @Override
    public void setStyle(Style style) {
        shapesList.forEach(shape -> {
            shape.setStyle(style);
        });
    }


    @Override
    public void moveTo(model.shapes.Point point) {
        double dx = point.getX() - getPosition().getX();
        double dy = point.getY() - getPosition().getY();
        super.moveTo(point);
        for (model.shapes.Shape shape : shapesList) {
            shape.move(dx, dy);
        };
    }

    @Override
    public void move(double dx, double dy) {
        for (model.shapes.Shape shape : shapesList) shape.move(dx, dy);
        getPosition().move(dx, dy);
    }

    @Override
    public void resizeTo(model.shapes.Point point) {
        double oldWidth = getWidth();
        double oldHeight = getHeight();

        super.resizeTo(point);

        if (oldWidth == 0 || oldHeight == 0) {
            return;
        }

        double scaleX = getWidth() / oldWidth;
        double scaleY = getHeight() / oldHeight;

        for (model.shapes.Shape shape : shapesList) {

            double relativeX = (shape.getPosition().getX() - getPosition().getX()) * scaleX;
            double relativeY = (shape.getPosition().getY() - getPosition().getY()) * scaleY;

            model.shapes.Point newCenter = new model.shapes.Point(
                    getPosition().getX() + relativeX,
                    getPosition().getY() + relativeY
            );
            shape.moveTo(newCenter);

            double newHalfWidth = (shape.getWidth() * scaleX) / 2.0;
            double newHalfHeight = (shape.getHeight() * scaleY) / 2.0;

            model.shapes.Point newCorner = new model.shapes.Point(
                    shape.getPosition().getX() + newHalfWidth,
                    shape.getPosition().getY() + newHalfHeight
            );

            shape.resizeTo(newCorner);
        }
    }

    public model.shapes.Shape peel() {
        return this;
    }

    @Override
    public model.shapes.Shape clone() {
        return new CompositeShape(this);
    }

    @Override
    public String toCSV() {
        StringBuilder sb = new StringBuilder();

        sb.append(getClass().getSimpleName()).append(",")
                .append(getPosition().getX()).append(",")
                .append(getPosition().getY()).append(",")
                .append(getWidth()).append(",")
                .append(getHeight()).append(",")
                .append(getStyle().getColor().getRed()).append(",")
                .append(getStyle().getColor().getGreen()).append(",")
                .append(getStyle().getColor().getBlue()).append(",")
                .append(getStyle().getLineWidth()).append(",")
                .append(getNumberOfChildren());

        for (Shape shape : shapesList) {
            sb.append("\n").append(shape.toCSV());
        }

        sb.append("\n").append("end");

        return sb.toString();
    }

    @Override
    public List<model.shapes.Shape> getChildren() {
        return shapesList;
    }

    @Override
    public boolean intersects(Point point) {
        return shapesList.stream().anyMatch(shape -> shape.intersects(point));
    }

    public int getNumberOfChildren() {
        return shapesList.size();
    }

    public void unMarkAll(){
        shapesList = shapesList.forEach(peel());
    }


}
