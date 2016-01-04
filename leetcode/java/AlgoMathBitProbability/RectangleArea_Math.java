/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <p>
 * Rectangle Area
 * Assume that the total area is never beyond the maximum possible value of int.
 */

// Algorithm : math, find the shadow area and substract it from total two retangles
public class RectangleArea_Math {

    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int width, height;
        int shadow = 0;
        if (A < E) {
            if (E < C) width = Math.min(C, G) - E;
            else width = 0;
        } else {
            if (A < G) width = Math.min(C, G) - A;
            else width = 0;
        }
        if (B > F) {
            if (B < H) height = Math.min(D, H) - B;
            else height = 0;
        } else {
            if (F < D) height = Math.min(D, H) - F;
            else height = 0;
        }

        return (C - A) * (D - B) + (G - E) * (H - F) - width * height;
    }
}
