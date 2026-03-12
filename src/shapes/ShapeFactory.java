package shapes;

import shapes.style.Style;
import shapes.style.StyleFactory;

import java.awt.*;
import java.util.ArrayList;

public class ShapeFactory {

    public static Shape createShapeFromCsv(String[] csv) {
        String shapeType = csv[0];

        double x = Double.parseDouble(csv[1]);
        double y = Double.parseDouble(csv[2]);
        double width = Double.parseDouble(csv[3]);
        double height = Double.parseDouble(csv[4]);
        int r = Integer.parseInt(csv[5]);
        int g = Integer.parseInt(csv[6]);
        int b = Integer.parseInt(csv[7]);
        int lineWidth = Integer.parseInt(csv[8]);

        Color color = new Color(r, g, b);
        Point p = new Point(x - width / 2, y - height / 2);

        Style style = StyleFactory.getInstance().getStyle(color, lineWidth);

        return switch (shapeType) {
            case "Triangle" -> new Triangle(p, width, height, style);
            case "Rectangle" -> new Rectangle(p, width, height, style);
            case "Circle" -> new Circle(p, width, height, style);
            case "Line" -> new Line(p, width, height, style);
            case "Pentagon" -> new Pentagon(p, width, height, style);
            case "CompositeShape" -> new CompositeShape(p, width, height, new ArrayList<>(), style);
            default -> throw new IllegalArgumentException("Unknown shape: " + shapeType);
        };
    }


}
