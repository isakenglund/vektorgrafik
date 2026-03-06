package main;

import shapes.Shape;

public class ShapeController {

    public void updateShapes(ShapeApp shapeApp) {
        for(Shape shape: shapeApp.getShapeContainer().getShapes()) {
            Shape actualShape = shape.peel();

            if(actualShape.isMarked()) {
                actualShape.setStyle(shapeApp.getCurrentStyle());
                System.out.println(actualShape.getStyle().getColor());
                System.out.println("Shape marked");
            }
        }
        shapeApp.getShapeContainer().repaint();
    }
}
