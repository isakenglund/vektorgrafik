package States;

import javax.swing.*;
import java.awt.*;

public abstract class State {
    private static State currentState;

    public State getState(){
        return this;
    }

    public static void setState(State state){
        currentState = state;
    }

    public static void reset(JFrame app){
        setState(new StateInsert(app));
    }

    public static State getCurrentState(){
        return currentState;
    }

    abstract void handleClick(Point point);
    abstract void handleHold(Point point, boolean pointerDown);
}
