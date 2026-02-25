package main;

import states.*;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class ShapeApp extends JFrame
{
  private static final long serialVersionUID = 1L;
  private ShapeContainer shapeContainer = new ShapeContainer();
  public ShapeApp()
  {
    createMenue();
    this.add(shapeContainer);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setSize(400,400);
    this.setVisible(true);
    State.reset(this);
  }

  public void createMenue()
  {
    JMenu menu = new JMenu("Modes");
    createMenuItem(menu, "Insert Circle", e -> State.setState(new StateInsertCircle(this)));
    createMenuItem(menu, "Insert Rectangle", e -> State.setState(new StateInsertRectangle(this)));
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
