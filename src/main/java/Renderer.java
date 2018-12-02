import geometry.Matrix;
import geometry.Quaternion;
import geometry.Vektor;

import static geometry.Quaternion.slerp;
import static geometry.Vektor.crossproduct;
import static geometry.Vektor.subtract;

public class Renderer {

    public static void main(String[] args){
        //orthogonale matrix: transposition = inversion
        Quaternion q1 = new Quaternion(0.5,0.5,0.5,0.5);
        Quaternion q2 = new Quaternion(0,4,2,1);
        Vektor v1 = new Vektor(1,0,0);
        System.out.println(q1.rotate(v1));
    }

    private static Matrix lookAt(Vektor from, Vektor to, Vektor tmp){
        //TODO: fix vertikal Camera
        Vektor forward = subtract(from, to).normalize();
        Vektor right = crossproduct(tmp.normalize(), forward);
        Vektor up = crossproduct(forward, right);
        return createCameraToWorldMatrix(from, forward, right, up);
    }

    private static Matrix lookAt(Vektor from, Vektor to){
        return lookAt(from, to, new Vektor(0,1,0));
    }

    private static Matrix createCameraToWorldMatrix(Vektor from, Vektor forward, Vektor rigth, Vektor up){
        return new Matrix(
                rigth.a0, rigth.a1, rigth.a2, 0,
                up.a0, up.a1, up.a2, 0,
                forward.a0, forward.a1, forward.a2, 0,
                from.a0, from.a1, from.a2, 1);
    }
}
