import geometry.*;

import static geometry.Matrix.createCameraToWorldMatrix;
import static geometry.Vektor.crossproduct;
import static geometry.Vektor.subtract;

public class Renderer {

    public static void main(String[] args){
//        new DMatrix4x4(rightX,rightY,rightZ,0,
//                upX,upY,upZ,0,
//                forwardX,forwardY,forwardZ,0,
//                x-translation,y-translation,z-translation,1);
        //from -> to
        //forward Vektor:   Vec3f forward = Normalize(from - to)
        //rigth Vektor:     Vec3f tmp(0, 1, 0);
        //                  Vec3f right = crossProduct(Normalize(tmp), forward);
        //up Vektor:        Vec3f up = crossProduct(forward, right);
        //translation: Koordinaten from
        //orthogonale matrix: transposition = inversion
        System.out.println(crossproduct(new Vektor(1,2,3), new Vektor(-7,8,9)));
    }

    private Matrix lookAt(Vektor from, Vektor to, Vektor tmp){
        //TODO: fix vertikal Camera
        Vektor forward = subtract(from, to).normalize();
        Vektor right = crossproduct(tmp.normalize(), forward);
        Vektor up = crossproduct(forward, right);
        return createCameraToWorldMatrix(from, forward, right, up);
    }

    private Matrix lookAt(Vektor from, Vektor to){

        return lookAt(from, to, new Vektor(0,1,0));
    }
}
