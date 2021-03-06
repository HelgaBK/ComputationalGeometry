package sample;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task {


    static final int HEIGHT = 700;
    static final int WIDTH = 1300;
    static final int BORDER_DISTANCE_H = 300;
    static final int BORDER_DISTANCE_W = 500;
    static final int POINT_COUNT = 4500;
    static int count;
    static Group item_group;

    public Task() {

        this.Setup();
    }

    private void Setup() {
        item_group = new Group();
        List<Point2D> points = getShape();
        List<Point2D> shape = JarvisAlgorithm(points);
        count = shape.size();
        for (Point2D point : shape) {
            DrawPoint(point, Color.DARKRED, 5);
        }
        DrawShape(shape);
        for (Point2D point : points) {
            DrawPoint(point, Color.BLACK, 2);
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

    public Scene getScene() {
        return new Scene(item_group, WIDTH, HEIGHT);
    }

    private List<Point2D> getShape() {
        Random random = new Random();
        int count = (random.nextInt() % POINT_COUNT) + POINT_COUNT / 2;
        if (count < 100) count = 3000;
        ArrayList<Point2D> res = new ArrayList<>();
        while (res.size() < 15) {
            int x = random.nextInt(1100);
            int y = random.nextInt(700);
            boolean b = random.nextBoolean();
            Point2D point;
            if (Math.sqrt(Math.pow(650 - x, 2) + Math.pow(350 - y, 2)) < 320) {
                if (b)
                    point = new Point2D(x + 50, y);
                else
                    point = new Point2D(x - 50, y);

                res.add(point);
            }
        }
        return res;
    }

    private void DrawShape(List<Point2D> shape) {
        for (int i = 0; i < shape.size(); i++) {
            int j = (i + 1) % shape.size();
            Line l = new Line(shape.get(i).getX(), HEIGHT - shape.get(i).getY(), shape.get(j).getX(), HEIGHT - shape.get(j).getY());
            l.setStroke(Color.BLUE);
            item_group.getChildren().add(l);
        }
    }

    static void DrawPoint(Point2D point, Color color, int radius) {
        Circle circle = new Circle(radius);
        circle.setFill(color);
        circle.setTranslateX(point.getX());
        circle.setTranslateY(HEIGHT - point.getY());
        item_group.getChildren().add(circle);
    }

    private Point2D SmallestPoint(List<Point2D> shape) {
        Point2D p = shape.get(0);
        for (Point2D point2D : shape)

            if (point2D.getY() < p.getY())
                p = point2D;
            else if (point2D.getY() == p.getY() && point2D.getX() < p.getX())
                p = point2D;
        return p;
    }

    private double CosBetweenVectors(Point2D a, Point2D b, Point2D c, Point2D d) {
        Point2D x = new Point2D(b.getX() - a.getX(), b.getY() - a.getY());
        Point2D y = new Point2D(d.getX() - c.getX(), d.getY() - c.getY());
        double m = x.getX() * y.getX() + x.getY() * y.getY();
        double n = Math.sqrt(Math.pow(x.getX(), 2) + Math.pow(x.getY(), 2)) * Math.sqrt(Math.pow(y.getX(), 2) + Math.pow(y.getY(), 2));
        return ((m * 1000) / n);
    }

    private ArrayList<Point2D> JarvisAlgorithm(List<Point2D> shape) {
        ArrayList<Point2D> new_shape = new ArrayList<>();
        new_shape.add(SmallestPoint(shape));
        //DrawPoint(new_shape.get(0), Color.RED, 15);
        Point2D p = null;
        double cs = -2;
        for (Point2D i : shape) {
            if (new_shape.get(0).equals(i)) continue;
            if (p == null) p = i;
            else {
                double d = CosBetweenVectors(new_shape.get(0), new Point2D(i.getX(), new_shape.get(0).getY()), new_shape.get(0), i);
                if (cs < d) {
                    cs = d;
                    p = i;
                }
            }
        }
        new_shape.add(p);
        shape.remove(p);

        if (new_shape.get(1).getX() < new_shape.get(0).getX()) {
            p = new_shape.get(0);
            new_shape.set(0, new_shape.get(1));
            new_shape.set(1, p);
        }

        do {
            if (shape.isEmpty()) {
                System.out.println("Empty set");
                break;
            }
            p = new_shape.get(0);
            cs = CosBetweenVectors(new_shape.get(new_shape.size() - 2), new_shape.get(new_shape.size() - 1), new_shape.get(new_shape.size() - 1), p);
            for (Point2D i : shape) {
                if (p.equals(i)) continue;
                double d = CosBetweenVectors(new_shape.get(new_shape.size() - 2), new_shape.get(new_shape.size() - 1), new_shape.get(new_shape.size() - 1), i);
                if (d > cs) {
                    p = i;
                    cs = d;
                }
            }
            if (p.equals(new_shape.get(0))) break;
            new_shape.add(p);
            shape.remove(p);
        } while (true);

        return new_shape;
    }

}