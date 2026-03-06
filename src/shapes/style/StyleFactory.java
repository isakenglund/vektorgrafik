package shapes.style;

import java.awt.*;
import java.util.HashMap;

public class StyleFactory {

    private static StyleFactory instance;
    private HashMap<String, Style> styles;

    private StyleFactory() {};

    public static StyleFactory getInstance() {
        if (instance == null){
            instance = new StyleFactory();
            instance.styles = new HashMap<>();
        }

        return instance;
    }

    public Style getStyle(Color color, int thickness) {
        String key = color + "-" + thickness;

        if (styles.containsKey(key)) {
            return styles.get(key);
        }

        Style newStyle = new Style(color, thickness);
        styles.put(key, newStyle);
        return newStyle;
    }
}
