package states;

import main.ShapeApp;
import main.ShapeContainer;
import main.ShapeDecorator;
import shapes.CompositeShape;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.style.StyleFactory;

import java.awt.*;
import java.util.List;

public class StateMark extends State{
    private Point pointDown;
    private Point startPoint;
    private Shape tempShape;

    public StateMark(ShapeApp app){
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        this.pointDown=point;
        this.startPoint = point;
    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        if (pointerDown && startPoint != null) {
            ShapeContainer shapes = app.getShapeContainer();

            if (tempShape != null) {
                shapes.removeShape(tempShape);
            }

            double width = point.getX() - startPoint.getX();
            double height = point.getY() - startPoint.getY();

            tempShape = createShape(startPoint, width, height);

            shapes.addShape(tempShape);
            shapes.repaint();
        }
    }

    @Override
    public void pointerUp(Point point) {
        // hämta vilka shapes som är inom markeringen
        // skapa en ny compositeShape
        // ta bort shapes som sätts in i compositeshape
        app.getShapeContainer().removeShape(tempShape);

        double x = Math.min(pointDown.getX(), point.getX());
        double y = Math.min(pointDown.getY(), point.getY());
        double width = Math.abs(pointDown.getX() - point.getX());
        double height = Math.abs(pointDown.getY() - point.getY());

        app.getShapeContainer().repaint();
    }

    private Shape createShape(Point start, double width, double height) {
        return new Rectangle(start, width, height, StyleFactory.getInstance().getStyle(Color.BLACK, 1));
    }



    /*
    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
        Shape selected = app.getShapeContainer().getSelected();
        ShapeContainer shapeContainer = app.getShapeContainer();

        if(selected != null && !selected.isMarked())
        {
            Shape markedShape = new ShapeDecorator(selected);
            markedShape.setMarked(true);
            shapeContainer.removeShape(selected);
            shapeContainer.addShape(markedShape);
            app.getShapeContainer().repaint();
        }
    }

     */

}
