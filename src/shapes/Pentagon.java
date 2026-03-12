package shapes;

import java.awt.*;
import shapes.style.Style;

public class Pentagon extends Shape {
    int[] xPoints;
    int[] yPoints;

    public Pentagon(Point p, double width, double height, Style style) {
        super(p, width, height, style);
        this.xPoints = new int[5];
        this.yPoints = new int[5];
    }

    public Pentagon(Pentagon pentagon) {
        super(pentagon);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        double centerX = getPosition().getX();
        double centerY = getPosition().getY();

        double radiusX = getWidth() / 2;
        double radiusY = getHeight() / 2;

        double angleStep = 2 * Math.PI / 5;
        double startAngle = -Math.PI / 2; // Spetsen rakt upp

        int[] xPoints = new int[5];
        int[] yPoints = new int[5];

        for (int i = 0; i < 5; i++) {
            xPoints[i] = (int) (centerX + radiusX * Math.cos(startAngle + i * angleStep));
            yPoints[i] = (int) (centerY + radiusY * Math.sin(startAngle + i * angleStep));
        }

        g.drawPolygon(xPoints, yPoints, 5);
    }

    @Override
    public Shape clone() {
        return new Pentagon(this);
    }


}
