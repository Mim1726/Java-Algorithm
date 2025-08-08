package dc;

import java.util.*;

class Point {
    double x, y;
    Point(double x, double y) { this.x = x; this.y = y; }
}

public class ClosestPair {

    public static double closestPair(Point[] points) {
        Arrays.sort(points, Comparator.comparingDouble(p -> p.x));
        return closestUtil(points, 0, points.length - 1);
    }

    private static double closestUtil(Point[] points, int l, int r) {
        if (r - l <= 3) {
            return bruteForce(points, l, r);
        }

        int mid = (l + r) / 2;
        double dLeft = closestUtil(points, l, mid);
        double dRight = closestUtil(points, mid + 1, r);

        double d = Math.min(dLeft, dRight);

        // Build strip of points near the middle line
        List<Point> strip = new ArrayList<>();
        double midX = points[mid].x;
        for (int i = l; i <= r; i++) {
            if (Math.abs(points[i].x - midX) < d) {
                strip.add(points[i]);
            }
        }

        strip.sort(Comparator.comparingDouble(p -> p.y));
        return Math.min(d, stripClosest(strip, d));
    }

    private static double bruteForce(Point[] points, int l, int r) {
        double minDist = Double.MAX_VALUE;
        for (int i = l; i <= r; i++) {
            for (int j = i + 1; j <= r; j++) {
                minDist = Math.min(minDist, dist(points[i], points[j]));
            }
        }
        return minDist;
    }

    private static double stripClosest(List<Point> strip, double d) {
        double min = d;
        for (int i = 0; i < strip.size(); i++) {
            for (int j = i + 1; j < strip.size() && (strip.get(j).y - strip.get(i).y) < min; j++) {
                min = Math.min(min, dist(strip.get(i), strip.get(j)));
            }
        }
        return min;
    }

    private static double dist(Point p1, Point p2) {
        return Math.hypot(p1.x - p2.x, p1.y - p2.y);
    }

    public static void main(String[] args) {
        Point[] points = {
            new Point(2, 3),
            new Point(12, 30),
            new Point(40, 50),
            new Point(5, 1),
            new Point(12, 10),
            new Point(3, 4)
        };
        System.out.println("Closest distance: " + closestPair(points));
    }
}
