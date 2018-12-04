import geometry.Matrix;
import geometry.Vector;

import static geometry.Vector.crossproduct;
import static geometry.Vector.subtract;

public class Renderer {

    Matrix cameraToWorld;
    Matrix worldToCamera;

    private class Pixel<T extends Number>{
        T x;
        T y;
        private Pixel(T x, T y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args){
        //orthogonale matrix: transposition = inversion
        Matrix m = new Matrix(0.718762, 0.615033, -0.324214, 0, -0.393732, 0.744416, 0.539277, 0, 0.573024, -0.259959, 0.777216, 0, 0.526967, 1.254234, -2.53215, 1);
        Vector v1 = new Vector(-0.5, 0.5, -0.5);
        Vector v2 = m.multVecMatix(v1);
        long start = System.nanoTime();
        long systemCallTime = System.nanoTime() - start;
        start = System.nanoTime();
        Vector v3 = m.invert().multVecMatix(v2);
        System.out.println(System.nanoTime() - start - systemCallTime);
        System.out.println(v2);
        System.out.println(v3);
    }

    private Pixel<Integer> computePixelCoordinates(Matrix worldToCamera, Vector point,
                                                   double canvasWidth, double canvasHeight,
                                                   int imageWidth, int imageHeigth){
        Vector csPoint = worldToCamera.multVecMatix(point);
        Pixel<Double> pScreen= new Pixel<>(csPoint.a0 / -csPoint.a2, csPoint.a1 / -csPoint.a2);
        double x = (pScreen.x + canvasWidth * 0.5) / canvasWidth;
        double y = (pScreen.y + canvasHeight * 0.5) / canvasHeight;
        Pixel<Double> pNDC = new Pixel<>(x, y);
        return new Pixel<>((int)(pNDC.x * imageWidth), (int)(pNDC.y * imageHeigth));
    }

    private static Matrix lookAt(Vector from, Vector to, Vector tmp, boolean worldToCam){
        //TODO: fix vertikal Camera
        Vector forward = subtract(from, to).normalize();
        Vector right = crossproduct(tmp.normalize(), forward);
        Vector up = crossproduct(forward, right);
        Matrix result = createCameraToWorldMatrix(from, forward, right, up);
        return worldToCam ? result.invert() : result;
    }

    private static Matrix lookAt(Vector from, Vector to, boolean worldToCam){
        return lookAt(from, to, new Vector(0,1,0), worldToCam);
    }

    private static Matrix createCameraToWorldMatrix(Vector from, Vector forward, Vector rigth, Vector up){
        return new Matrix(
                rigth.a0, rigth.a1, rigth.a2, 0,
                up.a0, up.a1, up.a2, 0,
                forward.a0, forward.a1, forward.a2, 0,
                from.a0, from.a1, from.a2, 1);
    }
}
