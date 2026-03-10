package main;

import shapes.CompositeShape;
import shapes.Point;
import shapes.Shape;

import java.awt.Color;
import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import states.State;

public class ShapeContainer extends JPanel implements Pointable
  {
  private static final long serialVersionUID = 1L;
  private List<Shape>       shapes           = new LinkedList<>();
  private Shape selected;


  public ShapeContainer()
    {
    super();
    MouseHandler mouseHandler = new MouseHandler(this);
    this.addMouseListener(mouseHandler);
    this.addMouseMotionListener(mouseHandler);
    this.setBackground(Color.white);
    }

  public void addShape(Shape shape)
    {
    shapes.add(shape);
    }

    public void removeShape(Shape shape) {
    shapes.remove(shape);
    }

    public void paintComponent(Graphics g) // anropas av Swing när det är dags att
    // rendera
    {
      super.paintComponent(g);

      for (Shape shape : shapes)
        shape.draw(g);
    }

  public void select(Point point)
    {
    for (Shape shape : shapes)
      {
      if (shape.intersects(point))
        {
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

  public void pointerDown(Point point) {State.getCurrentState().pointerDown(point);}

  public void pointerUp(Point point)
    {
      State.getCurrentState().pointerUp(point);
    }

  public void pointerMoved(Point point, boolean pointerDown) {State.getCurrentState().pointerMoved(point, pointerDown);}

    public List<Shape> getIsMarked() {
    return shapes.stream().filter(Shape::isMarked).toList();
    }
  }
