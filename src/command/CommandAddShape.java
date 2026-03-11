package command;

import main.ShapeContainer;
import shapes.Shape;

public class CommandAddShape implements Command {
    private final ShapeContainer container;
    private final Shape shape;

    public CommandAddShape(ShapeContainer container, Shape shape) {
        this.container = container;
        this.shape = shape;
    }

    @Override
    public void execute() {
        container.addShape(shape);
    }

    @Override
    public void undo() {
        container.removeShape(shape);
    }
}
