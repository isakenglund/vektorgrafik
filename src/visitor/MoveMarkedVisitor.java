package visitor;

import shapes.Shape;

public class MoveMarkedVisitor implements ShapeVisitor {
    private double dx;
    private double dy;

    public MoveMarkedVisitor(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    @Override
    public void visit(Shape shape) {
        if (shape.peel().isMarked()) {
            shape.move(dx, dy);
        }
    }
}