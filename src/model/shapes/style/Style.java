package model.shapes.style;

import java.awt.*;

public class Style {
    private Color color;
    private int lineWidth;

    public Style(Color color, int lineWidth) {
        this.color = color;
        this.lineWidth = lineWidth;
    }

    public Color getColor() {
        return color;
    }

    public int getLineWidth() {
        return lineWidth;
    }
}
