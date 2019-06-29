package io.github.n3roo.math.physics.collision;

import io.github.n3roo.math.Position;
import io.github.n3roo.math.Vec2d;
import io.github.n3roo.math.body.shapes.Polygon;

import java.util.ArrayList;

public class CollisionAlgorithms {

    /**
     * It analyses the collision (if there is one) between the two given polygons using SAT algorithm.
     * @param a the reference polygon,
     * @param b the target polygon.
     * @return the collision result between two *convex* polygon (not working properly with concave polygons)
     */
    public static CollisionResult polygonPolygonSAT(Polygon a, Polygon b) {
        CollisionResult collisionResult = new CollisionResult(a, b);

        Polygon poly1 = a.copy();
        Polygon poly2 = b.copy();

        for(int shape = 0; shape < 2; shape ++) {
            if(shape == 1) {
                poly1 = b.copy();
                poly2 = a.copy();
            }

            // e1 -> edge 1
            // e2 -> edge 2
            // We need two edges to get the projected axis of this segment
            for(int e1 = 0; e1 < poly1.getWorldPoints().size(); e1++) {
                int e2 = (e1 + 1) % poly1.getWorldPoints().size();
                Vec2d projAxis = new Vec2d(
                        - poly1.getWorldPoints().get(e2).y - poly1.getWorldPoints().get(e1).y,
                        poly1.getWorldPoints().get(e2).x - poly1.getWorldPoints().get(e1).x
                );
                double d = Math.sqrt(projAxis.x * projAxis.x + projAxis.y * projAxis.y);
                projAxis.x /= d;
                projAxis.y /= d;

                double[] minMaxP1 = getProjMinMax(poly1.getWorldPoints(), projAxis);
                double[] minMaxP2 = getProjMinMax(poly2.getWorldPoints(), projAxis);

                if(!(minMaxP2[1] >= minMaxP1[0] && minMaxP1[1] >= minMaxP2[0])) {
                    collisionResult.overlap = false;
                    return collisionResult;
                }
            }
        }

        collisionResult.overlap = true;
        return collisionResult;
    }

    /**
     * It analyses the collision (if there is one) between the two given polygons using diagonals.
     * @param a the reference polygon,
     * @param b the target polygon.
     * @return the collision result between two *convex* polygon (not working properly with concave polygons)
     */
    public static CollisionResult polygonPolygonDIAG(Polygon a, Polygon b) {
        CollisionResult collisionResult = new CollisionResult(a, b);

        Polygon poly1 = a.copy();
        Polygon poly2 = b.copy();

        for(int shape = 0; shape < 2; shape ++) {
            if (shape == 1) {
                poly1 = b.copy();
                poly2 = a.copy();
            }

            // Check diagonals of polygon ...
            for(int p = 0; p < poly1.getWorldPoints().size(); p++) {
                Vec2d lineP1s = poly1.getPosition().toVec2d();
                Vec2d lineP1e = poly1.getWorldPoints().get(p).toVec2d();

                // ... against edges of the other
                for(int q = 0; q < poly2.getWorldPoints().size(); q++) {
                    Vec2d lineP2s = poly2.getWorldPoints().get(q).toVec2d();
                    Vec2d lineP2e = poly2.getWorldPoints().get((q + 1) % poly2.getWorldPoints().size()).toVec2d();

                    // "off the shelf" line segment intersection
                    double h = (lineP2e.x - lineP2s.x) * (lineP1s.y - lineP1e.y) - (lineP1s.x - lineP1e.x) * (lineP2e.y - lineP2s.y);
                    double t1 = ((lineP2s.y - lineP2e.y) * (lineP1s.x - lineP2s.x) + (lineP2e.x - lineP2s.x) * (lineP1s.y - lineP2s.y)) / h;
                    double t2 = ((lineP1s.y - lineP1e.y) * (lineP1s.x - lineP2s.x) + (lineP1e.x - lineP1s.x) * (lineP1s.y - lineP2s.y)) / h;

                    if (t1 >= 0.0f && t1 < 1.0f && t2 >= 0.0f && t2 < 1.0f) {
                        collisionResult.overlap = true;
                        return collisionResult;
                    }
                }
            }

        }

        collisionResult.overlap = false;
        return collisionResult;
    }

    private static double[] getProjMinMax(ArrayList<Position> polyPoints, Vec2d projAxis) {
        double min = Double.MAX_VALUE;
        double max = - Double.MAX_VALUE;

        for(Position p : polyPoints) {
            double q = p.x * projAxis.x + p.y * projAxis.y;
            min = Math.min(min, q);
            max = Math.max(max, q);
        }

        return new double[]{min, max};
    }
}
