package view;

import controller.ShapeController;
import model.shapes.Shape;
import model.shapes.style.Style;
import model.shapes.style.StyleFactory;
import controller.states.*;
import controller.states.shapes.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;
import javax.swing.*;

public class ShapeApp extends JFrame {
    private static final long serialVersionUID = 1L;
    private ShapeContainer shapeContainer = new ShapeContainer();
    private ShapeController shapeController = new ShapeController();
    private int lineWidth = 1;
    private Color color = Color.BLACK;


    public ShapeApp() {
        createMenue();
        createToolbox();
        this.add(shapeContainer);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 800);
        this.setVisible(true);
        State.reset(this);
    }

    public void createMenue() {

        JMenu menu = new JMenu("Modes");
        ButtonGroup buttonGroup = new ButtonGroup();
        createRadioMenuItem(menu, buttonGroup, "Move", e -> State.setState(new StateMove(this)));
        createRadioMenuItem(menu, buttonGroup, "Delete", e -> State.setState(new StateDelete(this)));
        createRadioMenuItem(menu, buttonGroup, "Mark", e -> State.setState(new StateMark(this)));
        createRadioMenuItem(menu, buttonGroup, "Unmark", e -> State.setState(new StateUnmark(this)));
        createRadioMenuItem(menu, buttonGroup, "Resize", e -> State.setState(new StateResize(this)));

        JMenu shapes = new JMenu("Shapes");
        createRadioMenuItem(shapes, buttonGroup, "Circle", e -> State.setState(new StateInsertCircle(this)));
        createRadioMenuItem(shapes, buttonGroup, "Rectangle", e -> State.setState(new StateInsertRectangle(this)));
        createRadioMenuItem(shapes, buttonGroup, "Line", e -> State.setState(new StateInsertLine(this)));
        createRadioMenuItem(shapes, buttonGroup, "Triangle", e -> State.setState(new StateInsertTriangle(this)));
        createRadioMenuItem(shapes, buttonGroup, "Pentagon", e -> State.setState(new StateInsertPentagon(this)));

        JMenu customShapes = new JMenu("Custom Shapes");
        JMenu manageCustomShapes = new JMenu("Manage Custom Shapes");

        createMenuItem(manageCustomShapes, "Add marked shapes to custom tool", e -> {
            String name = JOptionPane.showInputDialog("Ange namn på figur:");
            if (name != null && !name.trim().isEmpty()) {

                List<Shape> savedShapes = shapeController.getMarkedShapes(this);

                createRadioMenuItem(customShapes, buttonGroup, name, f -> {
                    State customState = new StateInsertCustom(this, savedShapes);
                    State.setState(customState);
                });
            }
        });
        createMenuItem(manageCustomShapes, "Remove marked shapes to custom tool", e -> customShapes.removeAll());

        JButton addTextButton = new JButton("Add text");
        addTextButton.addActionListener(e -> {
            State.setState(new StateInsertText(this, JOptionPane.showInputDialog("Ange text:")));
        });

        JButton printButton = new JButton("Print Canvas");
        printButton.addActionListener(e -> shapeController.printComponent(shapeContainer));

        JMenu file = new JMenu("File");
        createMenuItem(file,"Export canvas", e -> shapeController.exportFile(shapeContainer));
        createMenuItem(file,"Import canvas", e -> shapeController.importFile(shapeContainer));

        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menu);
        menuBar.add(shapes);
        menuBar.add(customShapes);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(file);
        menuBar.add(manageCustomShapes);
        menuBar.add(addTextButton);
        menuBar.add(printButton);
        this.setJMenuBar(menuBar);
    }

    private void createToolbox() {
        JPanel toolbox = new JPanel();
        JMenu shapes = new JMenu("Shapes");
        JPanel steps = new JPanel();
        JPanel objectTools = new JPanel();

        createPanelButton(steps, "<<", e -> shapeContainer.undo());
        createPanelButton(steps, ">>", e -> shapeContainer.redo());

        createPanelButton(objectTools, "Red", e -> {
            this.color = Color.RED;
            shapeController.updateShapes(this);
        });
        createPanelButton(objectTools, "Blue", e -> {
            this.color = Color.BLUE;
            shapeController.updateShapes(this);
        });
        createPanelButton(objectTools, "-", e -> {
            this.lineWidth = Math.max(1, this.lineWidth - 1);
            shapeController.updateShapes((this));
        });
        createPanelButton(objectTools, "+", e -> {
            this.lineWidth += 1;
            shapeController.updateShapes((this));
        });

        toolbox.add(steps, BorderLayout.EAST);
        toolbox.add(objectTools, BorderLayout.EAST);
        this.add(toolbox, BorderLayout.SOUTH);
    }

    public void createPanelButton(JPanel panel, String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    public void createRadioMenuItem(JMenu menu, ButtonGroup group, String label, ActionListener listener) {
        JRadioButtonMenuItem menuItem = new JRadioButtonMenuItem(label);
        menuItem.addActionListener(listener);
        group.add(menuItem);
        menu.add(menuItem);
    }

    public void createMenuItem(JMenu menu, String label, ActionListener listener) {
        JMenuItem menuItem = new JMenuItem(label);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }


    public ShapeContainer getShapeContainer() {
        return shapeContainer;
    }

    public Style getCurrentStyle() {
        return StyleFactory.getInstance().getStyle(color, lineWidth);
    }


    public static void main(String[] args) {
        new ShapeApp(); // obs egentligen SwingUtilities ...
    }


}
