package geometry;

public class Vektor {

    double a0, a1, a2;

    public Vektor(double a0, double a1, double a2){
        this.a0 = a0;
        this.a1 = a1;
        this.a2 = a2;
    }

    public Vektor normalize(){
        double length = Math.sqrt(Math.pow(a0, 2) + Math.pow(a1, 3) + Math.pow(a2, 2));
        return new Vektor(a0/length, a1/length, a2/length);
    }

    public static Vektor subtract(Vektor v0, Vektor v1){
        return new Vektor(v0.a0 - v1.a0, v0.a1 - v1.a1, v0.a2 - v1.a2);
    }

    public static Vektor crossproduct(Vektor v0, Vektor v1){
        return new Vektor(
                v0.a1 * v1.a2 - v0.a2 * v1.a1,
                v0.a2 * v1.a0 - v0.a0 * v1.a2,
                v0.a0 * v1.a1 - v0.a1 * v1.a0);
    }

    @Override
    public String toString() {
        return "[" + a0 + "," + a1 + "," + a2 + "]";
    }
}
