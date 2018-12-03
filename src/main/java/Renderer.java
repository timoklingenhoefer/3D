import geometry.Matrix;
import geometry.Quaternion;
import geometry.Vector;

import static geometry.Vector.crossproduct;
import static geometry.Vector.subtract;

public class Renderer {

    public static void main(String[] args){
        //orthogonale matrix: transposition = inversion
        Matrix m = new Matrix(0.718762, 0.615033, -0.324214, 0, -0.393732, 0.744416, 0.539277, 0, 0.573024, -0.259959, 0.777216, 0, 0.526967, 1.254234, -2.53215, 1);
        System.out.println(m.invert());
    }

    private static Matrix lookAt(Vector from, Vector to, Vector tmp){
        //TODO: fix vertikal Camera
        Vector forward = subtract(from, to).normalize();
        Vector right = crossproduct(tmp.normalize(), forward);
        Vector up = crossproduct(forward, right);
        return createCameraToWorldMatrix(from, forward, right, up);
    }

    private static Matrix lookAt(Vector from, Vector to){
        return lookAt(from, to, new Vector(0,1,0));
    }

    private static Matrix createCameraToWorldMatrix(Vector from, Vector forward, Vector rigth, Vector up){
        return new Matrix(
                rigth.a0, rigth.a1, rigth.a2, 0,
                up.a0, up.a1, up.a2, 0,
                forward.a0, forward.a1, forward.a2, 0,
                from.a0, from.a1, from.a2, 1);
    }
}
