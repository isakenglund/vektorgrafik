package states;

import main.ShapeContainer;
import main.ShapeDecorator;
import shapes.CompositeShape;
import shapes.Point;
import main.ShapeApp;
import shapes.Rectangle;
import shapes.Shape;
import shapes.style.StyleFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;



public class StateMerge extends State {
    Point pointDown;
    List<Shape> shapesOnCanvas;

    private Point startPoint;
    private Shape tempShape;

    public StateMerge(ShapeApp app) {
        super(app);
        shapesOnCanvas = app.getShapeContainer().getShapes();
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

            if (tempShape != null) {shapes.removeShape(tempShape);}

            double width = point.getX() - startPoint.getX();
            double height = point.getY() - startPoint.getY();

            // Här anropar vi den abstrakta metoden
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
        List<Shape> shapesInMerge = mergeShapes(shapesOnCanvas,pointDown,point);

        double x = Math.min(pointDown.getX(), point.getX());
        double y = Math.min(pointDown.getY(), point.getY());
        double width = Math.abs(pointDown.getX() - point.getX());
        double height = Math.abs(pointDown.getY() - point.getY());

        app.getShapeContainer().addShape(new CompositeShape(new Point(x-width/2,y-height/2), width, height, shapesInMerge, StyleFactory.getInstance().getStyle(Color.BLACK, 1)));
        app.getShapeContainer().repaint();
    }

    private Shape createShape(Point start, double width, double height) {
        return new Rectangle(start, width, height, StyleFactory.getInstance().getStyle(Color.BLACK, 1));
    }

    private List<Shape> mergeShapes(List<Shape> list, Point pointDown, Point pointUp) {

        List<Shape> shapesInMerge = new ArrayList<>();
        List<Shape> shapesToRemove = new ArrayList<>();

        list.forEach(shape -> {
            if(shape.getPosition().getX()>pointDown.getX()&&
                    shape.getPosition().getX()<pointUp.getX()&&
                    shape.getPosition().getY()>pointDown.getY()&&
                    shape.getPosition().getY()<pointUp.getY()
            )
            {
                shapesInMerge.add(new ShapeDecorator(shape));
                shapesToRemove.add(shape);
            }
        });

        app.getShapeContainer().getShapes().removeAll(shapesToRemove);

        return shapesInMerge;
    }
}
