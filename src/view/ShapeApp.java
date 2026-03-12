package view;

import controller.ShapeController;
import model.shapes.style.Style;
import model.shapes.style.StyleFactory;
import controller.states.*;
import controller.states.shapes.*;

import java.awt.*;


import javax.swing.*;

public class ShapeApp extends JFrame
{
  private static final long serialVersionUID = 1L;
  private ShapeContainer shapeContainer = new ShapeContainer();
  private ShapeController shapeController = new ShapeController();
  private int lineWidth = 1;
  private Color color = Color.BLACK;





  public ShapeApp()
  {
    createMenue();
    createToolbox();
    this.add(shapeContainer);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(800,800);
    this.setVisible(true);
    State.reset(this);
  }

  public void createMenue()
  {
    JMenu menu = new JMenu("Modes");
    shapeController.createMenuItem(menu, "Move", e -> State.setState(new StateMove(this)));
    shapeController.createMenuItem(menu, "Delete", e -> State.setState(new StateDelete(this)));
    shapeController.createMenuItem(menu, "Mark", e -> State.setState(new StateMark(this)));
    shapeController.createMenuItem(menu, "Unmark", e -> State.setState(new StateUnmark(this)));
    shapeController.createMenuItem(menu, "Resize", e -> State.setState(new StateResize(this)));

    JMenu shapes = new JMenu("Shapes");
    shapeController.createMenuItem(shapes, "Circle", e -> State.setState(new StateInsertCircle(this)));
    shapeController.createMenuItem(shapes, "Rectangle", e -> State.setState(new StateInsertRectangle(this)));
    shapeController.createMenuItem(shapes, "Line", e -> State.setState(new StateInsertLine(this)));
    shapeController.createMenuItem(shapes, "Triangle", e -> State.setState(new StateInsertTriangle(this)));
    shapeController.createMenuItem(shapes, "Pentagon", e -> State.setState(new StateInsertPentagon(this)));

    JMenu addMenu = new JMenu("Add");
    JMenu customShapes = new JMenu("Custom Shapes");

    shapeController.createMenuItem(addMenu, "Add marked model.shapes to custom tool", e ->
            shapeController.createMenuItem(customShapes, JOptionPane.showInputDialog("Ange namn på figur:"), f ->
                    State.setState(new StateInsertCustom(this))));
    shapeController.createMenuItem(addMenu, "Remove marked model.shapes to custom tool", e -> customShapes.removeAll());

    JMenu text = new JMenu("Text");
    shapeController.createMenuItem(text,"Add text", e -> {State.setState(new StateInsertText(this, JOptionPane.showInputDialog("Ange text:")));});

    JMenu print = new JMenu("Print");
    shapeController.createMenuItem(print,"Print Canvas", e -> shapeController.printComponent(shapeContainer));

    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);
    menuBar.add(shapes);
    menuBar.add(addMenu);
    menuBar.add(customShapes);
    menuBar.add(text);
    menuBar.add(print);
    this.setJMenuBar(menuBar);
  }

  private void createToolbox() {
    JPanel toolbox = new JPanel();
    JMenu shapes = new JMenu("Shapes");
    JPanel steps = new JPanel();
    JPanel objectTools = new JPanel();

    shapeController.createPanelButton(steps, "<<", e -> shapeContainer.undo());
    shapeController.createPanelButton(steps, ">>", e -> shapeContainer.redo());

    shapeController.createPanelButton(objectTools, "Red", e -> {this.color = Color.RED; shapeController.updateShapes(this);});
    shapeController.createPanelButton(objectTools, "Blue", e -> {this.color = Color.BLUE; shapeController.updateShapes(this);});
    shapeController.createPanelButton(objectTools, "-", e -> {this.lineWidth = Math.max(1, this.lineWidth - 1); shapeController.updateShapes((this));});
    shapeController.createPanelButton(objectTools, "+", e ->{this.lineWidth += 1; shapeController.updateShapes((this));});

    toolbox.add(steps, BorderLayout.EAST);
    toolbox.add(objectTools, BorderLayout.EAST);
    this.add(toolbox, BorderLayout.SOUTH);
  }

  public ShapeContainer getShapeContainer() {
    return shapeContainer;
  }

  public Style getCurrentStyle() {
    return StyleFactory.getInstance().getStyle(color, lineWidth);
  }

  public static void main(String[] args)
  {
    new ShapeApp(); // obs egentligen SwingUtilities ...
  }


}
