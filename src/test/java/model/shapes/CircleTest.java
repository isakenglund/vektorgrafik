package model.shapes;

import model.shapes.style.Style;
import org.junit.Test;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

// ci test

public class CircleTest {

    @Test
    public void constructorShouldSetCorrectValues() {
        Point position = new Point(10, 20);
        Style style = new Style(Color.BLACK, 2);

        Circle circle = new Circle(position, 40, 60, style);

        // Shape stores the center, not the original top-left position
        assertEquals(30, circle.getPosition().getX());
        assertEquals(50, circle.getPosition().getY());

        assertEquals(40, circle.getWidth());
        assertEquals(60, circle.getHeight());

        assertSame(style, circle.getStyle());
        assertFalse(circle.isMarked());
    }

    @Test
    public void cloneShouldCreateNewCircleWithSameValues() {
        Point position = new Point(10, 20);
        Style style = new Style(Color.RED, 3);

        Circle original = new Circle(position, 40, 60, style);

        Shape clone = original.clone();

        assertNotSame(original, clone);
        assertInstanceOf(Circle.class, clone);

        assertEquals(original.getPosition().getX(),
                clone.getPosition().getX());

        assertEquals(original.getPosition().getY(),
                clone.getPosition().getY());

        assertEquals(original.getWidth(), clone.getWidth());
        assertEquals(original.getHeight(), clone.getHeight());

        assertSame(original.getStyle(), clone.getStyle());
    }

    @Test
    public void cloneShouldCopyPositionIndependently() {
        Circle original = new Circle(
                new Point(10, 20),
                40,
                60,
                new Style(Color.BLACK, 2)
        );

        Circle clone = (Circle) original.clone();

        clone.setPosition(new Point(100, 100));

        assertEquals(30, original.getPosition().getX());
        assertEquals(50, original.getPosition().getY());

        assertEquals(100, clone.getPosition().getX());
        assertEquals(100, clone.getPosition().getY());
    }

    @Test
    public void intersectsShouldReturnTrueForPointInsideCircleBounds() {
        Circle circle = new Circle(
                new Point(10, 20),
                40,
                60,
                new Style(Color.BLACK, 2)
        );

        // Center is (30, 50)
        assertTrue(circle.intersects(new Point(30, 50)));
    }

    @Test
    public void intersectsShouldReturnTrueForPointOnBoundary() {
        Circle circle = new Circle(
                new Point(10, 20),
                40,
                60,
                new Style(Color.BLACK, 2)
        );

        // Left boundary = 30 - 20 = 10
        assertTrue(circle.intersects(new Point(10, 50)));

        // Right boundary = 30 + 20 = 50
        assertTrue(circle.intersects(new Point(50, 50)));
    }

    @Test
    public void intersectsShouldReturnFalseForPointOutsideBounds() {
        Circle circle = new Circle(
                new Point(10, 20),
                40,
                60,
                new Style(Color.BLACK, 2)
        );

        assertFalse(circle.intersects(new Point(100, 100)));
    }
}
