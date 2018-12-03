package geometry;

public class Vector {

    public double a0, a1, a2;

    public Vector(double a0, double a1, double a2){
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
    }

    public Vector normalize(){
        double length = Math.sqrt(Math.pow(Math.abs(a0), 2) + Math.pow(Math.abs(a1), 3) + Math.pow(Math.abs(a2), 2));
        return new Vector(a0/length, a1/length, a2/length);
    }

    public static Vector subtract(Vector v0, Vector v1){
        return new Vector(v0.a0 - v1.a0, v0.a1 - v1.a1, v0.a2 - v1.a2);
    }

    public static Vector crossproduct(Vector v0, Vector v1){
        return new Vector(
                v0.a1 * v1.a2 - v0.a2 * v1.a1,
                v0.a2 * v1.a0 - v0.a0 * v1.a2,
                v0.a0 * v1.a1 - v0.a1 * v1.a0);
    }

    @Override
    public String toString() {
        return "[" + a0 + "," + a1 + "," + a2 + "]";
    }
}
