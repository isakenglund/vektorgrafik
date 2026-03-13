package model.shapes;
import java.awt.*;
import java.util.ArrayList;
import model.shapes.style.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ShapeFactory {

    public static Shape createShapeFromCsv(String[] csv, BufferedReader br) throws IOException {
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

        if (shapeType.equals("CompositeShape")) {
            int numChildren = Integer.parseInt(csv[9]);
            List<Shape> children = new ArrayList<>();

            for (int i = 0; i < numChildren; i++) {
                String line = br.readLine();
                if (line != null && !line.equals("end")) {
                    children.add(createShapeFromCsv(line.split(","), br));
                }
            }
            br.readLine();

            return new CompositeShape(p, width, height, children, style);
        }

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
