import org.ejml.data.DMatrix4x4;

import static org.ejml.dense.fixed.CommonOps_DDF4.invert;
import static org.ejml.dense.fixed.CommonOps_DDF4.transpose;

public class Renderer {

    public static void main(String[] args){
        DMatrix4x4 mat = new DMatrix4x4();
        DMatrix4x4 copy = mat.copy();
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

        invert(mat,mat);
        transpose(copy);
        mat.print();
        copy.print();
        //orthogonale matrix: transposition = inversion

    }
}
