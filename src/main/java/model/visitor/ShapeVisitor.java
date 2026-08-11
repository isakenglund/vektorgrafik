package model.visitor;

import model.shapes.Shape;

public interface ShapeVisitor {
    void visit(Shape shape);
}
