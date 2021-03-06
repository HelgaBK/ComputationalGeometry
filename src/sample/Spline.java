package sample;

import javafx.geometry.Point2D;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.paint.Color;


public class Spline {
    public static List<Float> getControlPoints(float x0, float y0, float x1, float y1, float x2, float y2, float t) {
        float d01 = (float) Math.sqrt(Math.pow(x1 - x0, 2) + Math.pow(y1 - y0, 2));
        float d12 = (float) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));

        float fa = t * d01 / (d01 + d12);
        float fb = t - fa;

        float p1x = x1 + fa * (x0 - x2);
        float p1y = y1 + fa * (y0 - y2);

        float p2x = x1 - fb * (x0 - x2);
        float p2y = y1 - fb * (y0 - y2);
        List<Float> a = new ArrayList<>();
        a.add(p1x);
        a.add(p1y);
        a.add(p2x);
        a.add(p2y);
        return a;
    }
    public static void drawSpline(List<Float> pts, float t, boolean closed) {
        List<Point2D> fpl = new ArrayList<>();
        List<Point2D> spl = new ArrayList<>();
        List<Float> cp = new ArrayList<>();  
        int n = pts.size() - 1;

        if (closed) {
            pts.add(pts.get(0));
            pts.add(pts.get(1));
            pts.add(pts.get(2));
            pts.add(pts.get(3));

            pts.add(0, pts.get(n - 1));
            pts.add(0, pts.get(n - 1));
            for (var i = 0; i < n; i += 2) {
                List<Float> temp = new ArrayList<>(getControlPoints(pts.get(i), pts.get(i + 1), pts.get(i + 2), pts.get(i + 3), pts.get(i + 4), pts.get(i + 5), t));

                cp.addAll(temp);
            }
            cp.add(cp.get(0));
            cp.add(cp.get(1));


            for (var i = 2; i < n + 1; i += 2) {
                Point2D fp = new Point2D(cp.get(2 * i - 2), cp.get(2 * i - 1));
                Point2D sp = new Point2D(cp.get(2 * i), cp.get(2 * i + 1));
                Point2D fk = new Point2D(pts.get(i), pts.get(i + 1));
                Point2D sk = new Point2D(pts.get(i + 2), pts.get(i + 3));
                ArrayList<Point2D> arrPoints = new ArrayList<>();
                arrPoints.add(fk);
                arrPoints.add(fp);
                arrPoints.add(sp);
                arrPoints.add(sk);
                Casteljo(arrPoints);
                fpl.add(fp);
                fpl.add(sp);
            }

        }
    }

    private static void Casteljo(ArrayList<Point2D> arrPoints) {
        for(int i = 1; i <1000; i++){
            ArrayList<Point2D> newPoints = new ArrayList<>();
            for(Point2D p : arrPoints){
                newPoints.add(new Point2D(p.getX(), p.getY()));
            }
            boolean check = true;
            while (check){
                for(int j = 1; j < newPoints.size(); j++){
                    double disX = (newPoints.get(j).getX() - newPoints.get(j - 1).getX()) / 1000 * i;
                    double disY = (newPoints.get(j).getY() - newPoints.get(j - 1).getY()) / 1000 * i;
                    Point2D p = new Point2D(newPoints.get(j-1).getX() + disX, newPoints.get(j-1).getY() + disY);
                    if (newPoints.size() == 2){
                        check = false;
                        Task.DrawPoint(p, Color.RED, 1);
                    }
                    newPoints.set(j -1, p);
                }
                newPoints.remove(newPoints.size() - 1);
            }
        }
    }
}
