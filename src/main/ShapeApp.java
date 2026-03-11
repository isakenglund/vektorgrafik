package main;

import shapes.style.Style;
import shapes.style.StyleFactory;
import states.*;
import states.shapes.*;

import java.awt.*;
import java.awt.event.ActionListener;


import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class ShapeApp extends JFrame
{
  private static final long serialVersionUID = 1L;
  private ShapeContainer shapeContainer = new ShapeContainer();
  private ShapeController shapeController = new ShapeController();
  private int lineWidth = 1;
  private Color color = Color.BLACK;
  private String customShapeName = null;
  private String textName = null;


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
    createMenuItem(menu, "Move", e -> State.setState(new StateMove(this)));
    createMenuItem(menu, "Delete", e -> State.setState(new StateDelete(this)));
    createMenuItem(menu, "Mark", e -> State.setState(new StateMark(this)));
    createMenuItem(menu, "Unmark", e -> State.setState(new StateUnmark(this)));
    createMenuItem(menu, "Resize", e -> State.setState(new StateResize(this)));

    JMenu shapes = new JMenu("Shapes");
    createMenuItem(shapes, "Circle", e -> State.setState(new StateInsertCircle(this)));
    createMenuItem(shapes, "Rectangle", e -> State.setState(new StateInsertRectangle(this)));
    createMenuItem(shapes, "Line", e -> State.setState(new StateInsertLine(this)));
    createMenuItem(shapes, "Triangle", e -> State.setState(new StateInsertTriangle(this)));
    createMenuItem(shapes, "Pentagon", e -> State.setState(new StateInsertPentagon(this)));

    JMenu addMenu = new JMenu("Add");
    JMenu customShapes = new JMenu("Custom Shapes");

    createMenuItem(addMenu, "Add marked shapes to custom tool", e -> {
      customShapeName = JOptionPane.showInputDialog("Ange namn på figur:");
    createMenuItem(customShapes, customShapeName, f -> {State.setState(new StateInsertCustom(this));});
    });
    createMenuItem(addMenu, "Remove marked shapes to custom tool", e -> customShapes.removeAll());

    JMenu text = new JMenu("Text");
    createMenuItem(text,"Add text", e -> {
      State.setState(new StateInsertText(this, JOptionPane.showInputDialog("Ange text:")));
    });

    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);
    menuBar.add(shapes);
    menuBar.add(addMenu);
    menuBar.add(customShapes);
    menuBar.add(text);
    this.setJMenuBar(menuBar);
  }

  private void createToolbox() {
    JPanel toolbox = new JPanel();
    JMenu shapes = new JMenu("Shapes");
    JPanel steps = new JPanel();
    JPanel objectTools = new JPanel();

    createPanelButton(steps, "<<", e -> State.setState(new StateMove(this)));
    createPanelButton(steps, ">>", e -> State.setState(new StateMove(this)));

    createPanelButton(objectTools, "Red", e -> {this.color = Color.RED; shapeController.updateShapes(this);});
    createPanelButton(objectTools, "Blue", e -> {this.color = Color.BLUE; shapeController.updateShapes(this);});
    createPanelButton(objectTools, "-", e -> {this.lineWidth = Math.max(1, this.lineWidth - 1); shapeController.updateShapes((this));});
    createPanelButton(objectTools, "+", e ->{this.lineWidth += 1; shapeController.updateShapes((this));});

    toolbox.add(steps, BorderLayout.EAST);
    toolbox.add(objectTools, BorderLayout.EAST);
    this.add(toolbox, BorderLayout.SOUTH);
  }

  private void createPanelButton(JPanel panel, String label, ActionListener listener) {
    JButton button = new JButton(label);
    button.addActionListener(listener);
    panel.add(button);
  }

  private void createMenuItem(JMenu menu, String label, ActionListener listener)
  {
    JRadioButton menuItem = new JRadioButton(label);
    menuItem.addActionListener(listener);
    menu.add(menuItem);
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
