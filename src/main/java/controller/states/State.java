package controller.states;

import controller.Pointable;
import view.ShapeApp;
import model.shapes.Point;
import controller.states.shapes.StateInsertCircle;

public abstract class State implements Pointable {
    private static State currentState;

    protected ShapeApp app;

    public State(ShapeApp app) {
        this.app = app;
    }

    public State getState() {
        return this;
    }

    public static void setState(State state) {
        currentState = state;
    }

    public static void reset(ShapeApp app) {
        setState(new StateInsertCircle(app));
    }

    public static State getCurrentState() {
        return currentState;
    }

    @Override
    public void pointerDown(Point point) {
        if (app.getShapeContainer().getSelected() != null){
            System.out.println(app.getShapeContainer().getSelected());
            app.getShapeContainer().saveState();
        }
    }


    @Override
    public void pointerUp(Point point) {
        app.getShapeContainer().setSelected(null);
    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {

    }

}
