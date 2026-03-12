package model.command;

import view.ShapeContainer;
import model.shapes.Shape;

public class CommandRemoveShape implements Command{
    private final ShapeContainer container;
    private final Shape shape;

    public CommandRemoveShape(ShapeContainer container, Shape shape) {
        this.container = container;
        this.shape = shape;
    }

    @Override
    public void execute() {
        container.removeShape(shape);
    }

    @Override
    public void undo() {
        container.addShape(shape);
    }
}
