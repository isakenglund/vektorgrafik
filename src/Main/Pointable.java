package Main;

import Shapes.Point;

public interface Pointable
  {
  public void pointerDown(Point point);
  public void pointerUp(Point point);
  public void pointerMoved(Point point, boolean pointerDown);
  }
