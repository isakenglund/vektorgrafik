package states;

import main.Pointable;
import main.ShapeApp;
import shapes.Point;

public abstract class State implements Pointable {
    private static State currentState;

    ShapeApp app;

    public State(ShapeApp app){
        this.app = app;
    }

    public State getState(){
        return this;
    }

    public static void setState(State state){
        currentState = state;
    }

    public static void reset(ShapeApp app){
        setState(new StateInsert(app, "RECTANGLE"));
    }

    public static State getCurrentState(){
        return currentState;
    }

    @Override
    public void pointerDown(Point point) {

    }

    @Override
    public void pointerUp(Point point) {
        app.getShapeContainer().setSelected(null);
    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {

    }

}
