package shapes;

import java.awt.*;
import java.util.List;

public class CompositeShape extends Shape implements Composite {

    private List<Shape> shapesList;

    public CompositeShape(double x, double y, double width, double height, List<Shape> shapesList)
    {
        super(x,y,width,height);
        this.shapesList = shapesList;
    }

    public CompositeShape(Point point, double width, double height, List<Shape> shapesList)
    {
        this(point.getX(), point.getY(),width,height, shapesList);
    }

    @Override
    public void draw(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        //shapesList.forEach(shape -> shape.draw(g2));

        int drawX = (int) (getWidth() < 0 ? getPosition().getX() + getWidth() : getPosition().getX());
        int drawY = (int) (getHeight() < 0 ? getPosition().getY() + getHeight() : getPosition().getY());
        int drawWidth = (int) Math.abs(getWidth());
        int drawHeight = (int) Math.abs(getHeight());

        g2.drawRect(drawX, drawY, drawWidth, drawHeight);

        double cx = drawX + drawWidth / 2.0;
        double cy = drawY + drawHeight / 2.0;

        var old = g2.getTransform();


        shapesList.forEach(shape -> {
            g2.rotate(shape.getRotationRadians(), cx, cy);
            shape.draw(g2);
            g2.setTransform(old);
        });

    }


    @Override
    public void moveTo(Point point)
    {
        double dx = point.getX() - getPosition().getX();
        double dy = point.getY() - getPosition().getY();
        super.moveTo(point);
        for(Shape shape : shapesList){
            shape.move(dx, dy);
        };
    }

    @Override
    public void move(double dx, double dy)
    {
        for(Shape shape : shapesList) shape.move(dx, dy);
        getPosition().move(dx, dy);
    }

    @Override
    public void resizeTo(Point point) {
        double oldWidth = getWidth();
        double oldHeight = getHeight();

        setWidth(point.getX() - getPosition().getX());
        setHeight(point.getY() - getPosition().getY());

        if (oldWidth == 0 || oldHeight == 0) {
            return;
        }

        double scaleX = getWidth() / oldWidth;
        double scaleY = getHeight() / oldHeight;

        for (Shape shape : shapesList) {
            double relativeX = (shape.getPosition().getX() - getPosition().getX()) * scaleX;
            double relativeY = (shape.getPosition().getY() - getPosition().getY()) * scaleY;

            shape.moveTo(new Point(getPosition().getX() + relativeX, getPosition().getY() + relativeY));

            if (shape instanceof Circle) {
                double avgScale = (scaleX + scaleY) / 2;
                double newRadius = (shape.getWidth() / 2) * avgScale;
                Point edgePoint = new Point(
                        shape.getPosition().getX() + newRadius,
                        shape.getPosition().getY()
                );
                shape.resizeTo(edgePoint);
            } else {
                double newShapeWidth = shape.getWidth() * scaleX;
                double newShapeHeight = shape.getHeight() * scaleY;

                Point newEndCorner = new Point(
                        shape.getPosition().getX() + newShapeWidth,
                        shape.getPosition().getY() + newShapeHeight
                );
                shape.resizeTo(newEndCorner);
            }
        }
    }
    public Shape peel()
    {
        return new CompositeShape(getPosition(), getWidth(), getHeight(), shapesList);
    }

    @Override
    public List<Shape> getChildren() {
        return shapesList;
    }

    @Override
    public void rotateTo(Point point) {
        for(Shape shape : shapesList) shape.rotateTo(point);
        super.rotateTo(point);
    }
}
