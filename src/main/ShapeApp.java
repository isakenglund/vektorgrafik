package main;

import shapes.Shape;
import states.*;
import states.shapes.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.LinkedList;

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

    JMenuBar menuBar = new JMenuBar();
    menuBar.add(menu);
    this.setJMenuBar(menuBar);
  }

  private void createToolbox() {
    JPanel toolbox = new JPanel();
    JPanel shapes = new JPanel();
    JPanel steps = new JPanel();
    JPanel manipulate = new JPanel();
    JPanel objectTools = new JPanel();

    createPanelButton(manipulate, "Mv", e -> State.setState(new StateMove(this)));
    createPanelButton(manipulate, "Rz", e -> State.setState(new StateResize(this)));
    createPanelButton(manipulate, "Ro", e -> State.setState(new StateRotate(this)));
    createPanelButton(manipulate, "Del", e -> State.setState(new StateDelete(this)));

    createPanelButton(shapes, "C", e -> State.setState(new StateInsertCircle(this)));
    createPanelButton(shapes, "R", e -> State.setState(new StateInsertRectangle(this)));
    createPanelButton(shapes, "L", e -> State.setState(new StateInsertLine(this)));
    createPanelButton(shapes, "T", e -> State.setState(new StateInsertTriangle((this))));
    createPanelButton(shapes, "P", e -> State.setState(new StateInsertPentagon((this))));

    createPanelButton(steps, "<<", e -> State.setState(new StateMove(this)));
    createPanelButton(steps, ">>", e -> State.setState(new StateMove(this)));

    createPanelButton(objectTools, "Red", e -> State.setState(new StateMark(this)));
    createPanelButton(objectTools, "Blue", e -> State.setState(new StateMark(this)));
    createPanelButton(objectTools, "-", e -> LineWidth.getInstance().setWidth(LineWidth.getInstance().getWidth() - 1));
    createPanelButton(objectTools, "+", e -> LineWidth.getInstance().setWidth(LineWidth.getInstance().getWidth()+1));

    toolbox.add(manipulate,BorderLayout.WEST);
    toolbox.add(shapes, BorderLayout.CENTER);
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
