package states;

import shapes.CompositeShape;
import shapes.Point;
import main.ShapeApp;
import shapes.Shape;

import java.util.ArrayList;
import java.util.List;



public class StateMerge extends State {
    Point pointDown;
    List<Shape> shapesOnCanvas;

    public StateMerge(ShapeApp app) {
        super(app);
        shapesOnCanvas = app.getShapeContainer().getShapes();
    }

    @Override
    public void pointerDown(Point point) {
        this.pointDown=point;

    }


    @Override
    public void pointerMoved(Point point, boolean pointerDown) {

    }

    @Override
    public void pointerUp(Point point) {
        // hämta vilka shapes som är inom markeringen
        // skapa en ny compositeShape
        // ta bort shapes som sätts in i compositeshape
        List<Shape> shapesInMerge = mergeShapes(shapesOnCanvas,pointDown,point);

        double x = Math.min(pointDown.getX(), point.getX());
        double y = Math.min(pointDown.getY(), point.getY());
        double width = Math.abs(pointDown.getX() - point.getX());
        double height = Math.abs(pointDown.getY() - point.getY());

        app.getShapeContainer().addShape(new CompositeShape(x, y, width, height, shapesInMerge));
        System.out.println(app.getShapeContainer().getShapes().size());
        app.getShapeContainer().repaint();
    }

    private List<Shape> mergeShapes(List<Shape> list, Point pointDown, Point pointUp) {

        List<Shape> shapesInMerge = new ArrayList<>();

        list.forEach(shape -> {
            if(shape.getPosition().getX()>pointDown.getX()&&
                    shape.getPosition().getX()<pointUp.getX()&&
                    shape.getPosition().getY()>pointDown.getY()&&
                    shape.getPosition().getY()<pointUp.getY()
            )
            {
                shapesInMerge.add(shape);
                System.out.println("shape in merge");
            }
        });

        app.getShapeContainer().getShapes().removeAll(shapesInMerge);


        return shapesInMerge;
    }
}
