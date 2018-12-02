package geometry;

public class Matrix {

    private double a00, a01, a02, a03, a10, a11, a12, a13, a20, a21, a22, a23, a30, a31, a32, a33;

    public Matrix(){};

    public Matrix(double a00, double a01, double a02, double a03,
                  double a10, double a11, double a12, double a13,
                  double a20, double a21, double a22, double a23,
                  double a30, double a31, double a32, double a33){

        this.a00 = a00;
        this.a01 = a01;
        this.a02 = a02;
        this.a03 = a03;
        this.a10 = a10;
        this.a11 = a11;
        this.a12 = a12;
        this.a13 = a13;
        this.a20 = a20;
        this.a21 = a21;
        this.a22 = a22;
        this.a23 = a23;
        this.a30 = a30;
        this.a31 = a31;
        this.a32 = a32;
        this.a33 = a33;
    }

    public static Matrix createCameraToWorldMatrix(Vektor from, Vektor forward, Vektor rigth, Vektor up){
        return new Matrix(
                rigth.a0, rigth.a1, rigth.a2, 0,
                up.a0, up.a1, up.a2, 0,
                from.a0, from.a1, from.a2, 0,
                forward.a0, forward.a1, forward.a2, 1);
    }
}
