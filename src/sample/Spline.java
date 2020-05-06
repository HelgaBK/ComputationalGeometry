package sample;

import java.util.ArrayList;
import java.util.List;

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
    public static void drawSpline(List<Float> shapeFloat, float v, boolean b) {

    }
}
