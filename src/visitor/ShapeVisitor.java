package visitor;

import shapes.Shape;

public interface ShapeVisitor {
    void visit(Shape shape);
}
