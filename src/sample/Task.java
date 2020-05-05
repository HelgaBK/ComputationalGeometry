package sample;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class Task {
    static final int HEIGHT = 700;
    static final int WIDTH = 1300;
    static final int BORDER_DISTANCE_H = 300;
    static final int BORDER_DISTANCE_W = 500;
    static final int POINT_COUNT = 4500;
    static int count;
    static Group item_group;

    public Task() {
        this.Setup;
    }

    public void Setup{
    item_group = new Group();
        List<Point2D> points = getShape();
        List<Point2D> shape = JarvisAlgorithm(points);
        count = shape.size();
        for(Point2D point : shape){
            DrawPoint(point, Color.DARKRED, 5);
        }
        shape.add(new Point2D(shape.get(0).getX(), shape.get(0).getY()));
        //Casteljo(shape);
        List<Float> shapeFloat = new ArrayList<>();
        for (Point2D p : shape) {
            shapeFloat.add((float) p.getX());
            shapeFloat.add((float) p.getY());
        }
        Spline.drawSpline(shapeFloat, 0.33f, true);


    }

    private void DrawPoint(Point2D point, Color color, int radius) {
    }

    private List<Point2D> JarvisAlgorithm(List<Point2D> points) {
    }


    private List<Point2D> getShape() {
    }


    public Scene getScene() {
    }
}
