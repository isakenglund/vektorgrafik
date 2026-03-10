package main;

import shapes.Shape;

public class ShapeController {

    public void updateShapes(ShapeApp shapeApp) {
        for(Shape shape: shapeApp.getShapeContainer().getShapes()) {
            Shape actualShape = shape.peel();

            if(actualShape.isMarked()) {
                actualShape.setStyle(shapeApp.getCurrentStyle());
            }
        }
        shapeApp.getShapeContainer().repaint();
    }
}
