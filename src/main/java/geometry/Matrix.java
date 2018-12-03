package geometry;

public class Matrix {

    private double a[][] = new double[4][4];

    public Matrix(double[][] a){
        this.a = a;
    }

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

    public Vector multVecMatix(Vector p){
        double x = p.a0 * a[0][0] + p.a1 * a[1][0] + p.a2 * a[2][0] + a[3][0];
        double y = p.a0 * a[0][1] + p.a1 * a[1][1] + p.a2 * a[2][1] + a[3][1];
        double z = p.a0 * a[0][2] + p.a1 * a[1][2] + p.a2 * a[2][2] + a[3][2];
        return new Vector(x, y, z);
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

    // static Methods

    public static Matrix multiply(Matrix m1, Matrix m2){
        double[][] product = new double[4][4];
        for(int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    product[i][j] += m1.a[i][k] * m2.a[k][j];
                }
            }
        }
        return new Matrix(product);
    }

    public Matrix invert() {
        int n = a.length;
        double[][] x = new double[n][n];
        double[][] b = new double[n][n];
        int[] index = new int[n];
        for (int i = 0; i < n; ++i)
            b[i][i] = 1;
        // Transform the matrix into an upper triangle
        gaussian(a, index);
        // Update the matrix b[i][j] with the ratios stored
        for (int i = 0; i < n - 1; ++i)
            for (int j = i + 1; j < n; ++j)
                for (int k = 0; k < n; ++k)
                    b[index[j]][k] -= a[index[j]][i] * b[index[i]][k];
        // Perform backward substitutions
        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / a[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int k = j + 1; k < n; ++k) {
                    x[j][i] -= a[index[j]][k] * x[k][i];
                }
                x[j][i] /= a[index[j]][j];
            }
        }
        return new Matrix(x);
    }

    private static void gaussian(double[][] a, int[] index) {
        int n = index.length;
        double[] c = new double[n];
        // Initialize the index
        for (int i = 0; i < n; ++i)
            index[i] = i;
        // Find the rescaling factors, one from each row
        for (int i = 0; i < n; ++i) {
            double c1 = 0;
            for (int j = 0; j < n; ++j) {
                double c0 = Math.abs(a[i][j]);
                if (c0 > c1) c1 = c0;
            }
            c[i] = c1;
        }
        // Search the pivoting element from each column
        int k = 0;
        for (int j = 0; j < n - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < n; ++i) {
                double pi0 = Math.abs(a[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }
            // Interchange rows according to the pivoting order
            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < n; ++i) {
                double pj = a[index[i]][j] / a[index[j]][j];
                // Record pivoting ratios below the diagonal
                a[index[i]][j] = pj;
                // Modify other elements accordingly
                for (int l = j + 1; l < n; ++l)
                    a[index[i]][l] -= pj * a[index[j]][l];
            }
        }
    }
}