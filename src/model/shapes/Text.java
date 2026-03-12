package model.shapes;

import model.shapes.style.Style;

import java.awt.*;

public class Text extends Shape {

    private final String text;

    public Text(Point p, double width, double height, Style style, String text) {
        super(p, width, height, style);
        this.text = text;
    }

    public Text(Text text) {
        super(text);
        this.text = text.text;
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.setFont(new Font("Arial", Font.BOLD, (int) getHeight()));
        g.drawString(this.text, (int) (getPosition().getX() - getWidth() / 2), (int) (getPosition().getY() + getHeight() / 2));
    }

    @Override
    public Shape clone() {
        return new Text(this);
    }
}
