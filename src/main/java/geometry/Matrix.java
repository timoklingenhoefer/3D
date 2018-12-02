package geometry;

public class Matrix {

    private double a[][] = new double[4][4];

    public Matrix(){}

    public Matrix(double a00, double a01, double a02, double a03,
                  double a10, double a11, double a12, double a13,
                  double a20, double a21, double a22, double a23,
                  double a30, double a31, double a32, double a33){

        this.a[0][0] = a00;
        this.a[0][1] = a01;
        this.a[0][2] = a02;
        this.a[0][3] = a03;
        this.a[1][0] = a10;
        this.a[1][1] = a11;
        this.a[1][2] = a12;
        this.a[1][3] = a13;
        this.a[2][0] = a20;
        this.a[2][1] = a21;
        this.a[2][2] = a22;
        this.a[2][3] = a23;
        this.a[3][0] = a30;
        this.a[3][1] = a31;
        this.a[3][2] = a32;
        this.a[3][3] = a33;
    }

    public Matrix transpose(){
        return new Matrix(
                a[0][0], a[1][0], a[2][0], a[3][0],
                a[0][1], a[1][1], a[2][1], a[3][1],
                a[0][2], a[1][2], a[2][2], a[3][2],
                a[0][3], a[1][3], a[2][3], a[3][3]);
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        for(int i = 0;i<4;i++){
            for(int j = 0;j<4;j++){
                if(j==0){
                    s.append("|");
                }
                s.append(a[i][j] >= 10 ? "" + a[i][j] : " " + a[i][j]);
                if(j!=3){
                    s.append(",");
                }else{
                    s.append("|");
                }
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static Matrix createCameraToWorldMatrix(Vektor from, Vektor forward, Vektor rigth, Vektor up){
        return new Matrix(
                rigth.a0, rigth.a1, rigth.a2, 0,
                up.a0, up.a1, up.a2, 0,
                forward.a0, forward.a1, forward.a2, 0,
                from.a0, from.a1, from.a2, 1);
    }
}
