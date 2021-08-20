package kclosestpointtoorigin;

import java.util.Comparator;
import java.util.PriorityQueue;

class Point {
    private int x;
    private int y;
    private int [] p;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(int [] p) {
        x = p[0];
        y = p[1];
        this.p = p;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    Double getDist() {
        return Math.sqrt(x*x+y*y);
    }

    int [] getP() {
        return p;
    }
}
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        PriorityQueue<Point> closestPoints = new PriorityQueue<>(10, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                Double d1 = o1.getDist();
                Double d2 = o2.getDist();
                return Double.compare(d1,d2);
            }
        });

        for (int [] point : points) {
            closestPoints.add(new Point(point));
        }

        int [][] retValue = new int[k][2];
        for (int j = 0;j<k;++j) {
            retValue[j] = closestPoints.poll().getP();
        }

        return retValue;


    }
}
