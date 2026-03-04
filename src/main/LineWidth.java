package main;

public class LineWidth {
    private static LineWidth instance;
    private int width;

    private LineWidth() {
        width = 1;
    }

    public static LineWidth getInstance() {
        if (instance == null) {
            instance = new LineWidth();
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }
    public void setWidth(int width) {
        if( width > 0) {
            this.width = width;
        }
        System.out.println( "LineWidth: " + width);
    }
}
