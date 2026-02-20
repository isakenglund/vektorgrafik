package States;

import java.awt.*;

public abstract class State {

    private static State state;

    public static State getState(){
        return this.state;
    }
    protected static void setSate(State state){
        this.state = state;
    }

    public static void reset(ShapeApp app){
        setSate(new StateInsert(app));
    }

    abstract void handleClick(Point point);
    abstract void handleHold(Point point, boolean pointerDown);
}
