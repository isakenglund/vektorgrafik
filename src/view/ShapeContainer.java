package view;

import controller.MouseHandler;
import controller.Pointable;
import model.shapes.Point;
import model.shapes.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

import javax.swing.JPanel;

import controller.states.State;

public class ShapeContainer extends JPanel implements Pointable {
    private static final long serialVersionUID = 1L;
    private List<Shape> shapes = new LinkedList<>();
    private Shape selected;

    private final Stack<List<Shape>> undoStack = new Stack<>();
    private final Stack<List<Shape>> redoStack = new Stack<>();

    public ShapeContainer() {
        super();
        MouseHandler mouseHandler = new MouseHandler(this);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
        this.setBackground(Color.white);
    }

    public void addShape(Shape shape) {
        saveState();
        shapes.add(shape);
        repaint();
    }

    public void removeShape(Shape shape) {
        saveState();
        shapes.remove(shape);
        repaint();
    }

    public void addShapeTemp(Shape shape) {
        shapes.add(shape);
        repaint();
    }

    public void removeShapeTemp(Shape shape) {
        shapes.remove(shape);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Shape shape : shapes)
            shape.draw(g);
    }

    public void select(Point point) {
        for (Shape shape : shapes) {
            if (shape.intersects(point)) {
                selected = shape;
                return;
            }
        }
    }

    public Shape getSelected() {
        return selected;
    }

    public void setSelected(Shape shape) {
        selected = shape;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void pointerDown(Point point) {
        State.getCurrentState().pointerDown(point);
    }

    public void pointerUp(Point point) {
        State.getCurrentState().pointerUp(point);
    }

    public void pointerMoved(Point point, boolean pointerDown) {
        State.getCurrentState().pointerMoved(point, pointerDown);
    }

    public List<Shape> getIsMarked() {
        return shapes.stream().filter(Shape::isMarked).toList();
    }

    private List<Shape> deepCopyShapes(List<Shape> original) {
        List<Shape> copy = new LinkedList<>();
        for (Shape shape : original) {
            copy.add(shape.clone());
        }
        return copy;
    }

    public void saveState() {
        undoStack.push(deepCopyShapes(shapes));
        System.out.println(undoStack.size());
        redoStack.clear();
        repaint();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(deepCopyShapes(shapes));
            shapes = undoStack.pop();
            selected = null;
            repaint();
        } else {
            System.out.println("Undo stack is empty");
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(deepCopyShapes(shapes));
            shapes = redoStack.pop();
            selected = null;
            repaint();
        } else {
            System.out.println("Redo stack is empty");
        }
    }

    public String exportShapesToCSV() {
        StringBuilder csv = new StringBuilder();
        unMarkAll();
        for (Shape shape : shapes) {
            csv.append(shape.toCSV()).append("\n");
        }

        return csv.toString();
    }

    public void unMarkAll() {
        this.shapes = shapes.stream()
                .map(Shape::peel)
                .collect(Collectors.toCollection(LinkedList::new));
        repaint();
    }
}
