package states;

import main.Pointable;
import main.ShapeApp;
import shapes.Point;
import states.shapes.StateInsertCircle;

public abstract class State implements Pointable {
    private static State currentState;

    protected ShapeApp app;

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
        setState(new StateInsertCircle(app));
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
