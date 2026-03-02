package main;

import states.*;
import states.shapes.*;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ShapeApp extends JFrame
{
  private static final long serialVersionUID = 1L;
  private ShapeContainer shapeContainer = new ShapeContainer();
  public ShapeApp()
  {
    createMenue();
    createToolbox();
    this.add(shapeContainer);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400,400);
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
    createMenuItem(menu, "Merge", e -> State.setState(new StateMerge(this)));
    createMenuItem(menu, "Unmerge", e -> State.setState(new StateUnmerge(this)));


    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);
    this.setJMenuBar(menuBar);
  }

  private void createToolbox() {
    JPanel toolbox = new JPanel();
    toolbox.setSize(100, 400);
    createDrawButton(toolbox, "Circle", e -> State.setState(new StateInsertCircle(this)));
    createDrawButton(toolbox, "Rectangle", e -> State.setState(new StateInsertRectangle(this)));
    createDrawButton(toolbox, "Line", e -> State.setState(new StateInsertLine(this)));
    createDrawButton(toolbox, "Triangle", e -> State.setState(new StateInsertTriangle((this))));
    createDrawButton(toolbox, "Polygon", e -> State.setState(new StateInsertPentagon((this))));
    this.add(toolbox, BorderLayout.SOUTH);
  }

  private void createDrawButton(JPanel panel, String label, ActionListener listener) {
    JButton button = new JButton(label);
    button.addActionListener(listener);
    panel.add(button);
  }

  private void createMenuItem(JMenu menu, String label, ActionListener listener)
  {
    JMenuItem menuItem = new JMenuItem(label);
    menuItem.addActionListener(listener);
    menu.add(menuItem);
  }

  public ShapeContainer getShapeContainer() {
    return shapeContainer;
  }

  public static void main(String args[])
  {
    new ShapeApp(); // obs egentligen SwingUtilities ...
  }
}
