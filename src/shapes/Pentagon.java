package shapes;

import java.awt.*;

public class Pentagon extends Shape {
    int[] xPoints;
    int[] yPoints;

    public Pentagon(Point point, double width, double height) {
        super(point, width, height);
        this.xPoints = new int[5];
        this.yPoints = new int[5];
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        double angleStep = 2 * Math.PI / 5;
        double startAngle = -Math.PI / 2; // Spetsen uppåt

        for (int i = 0; i < 5; i++) {
            xPoints[i] = (int) (getPosition().getX() + getWidth() / 2 + (getWidth() / 2) * Math.cos(startAngle + i * angleStep));
            yPoints[i] = (int) (getPosition().getY() + getHeight() / 2 + (getHeight() / 2) * Math.sin(startAngle + i * angleStep));
        }

        var old = g2.getTransform();
        g2.rotate(getRotationRadians(), getPosition().getX() + getWidth() / 2, getPosition().getY() + getHeight() / 2);
        g2.drawPolygon(new Polygon(xPoints,yPoints,5));
        g2.setTransform(old);
    }

}
