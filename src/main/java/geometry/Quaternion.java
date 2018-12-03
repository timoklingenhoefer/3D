package geometry;

import static java.lang.Math.*;

public class Quaternion {
    private double q0, q1, q2, q3;

    public Quaternion(double q0, double q1, double q2, double q3){
        this.q0 = q0;
        this.q1 = q1;
        this.q2 = q2;
        this.q3 = q3;
    }

    public Quaternion normalize(){
        double length = sqrt(pow(abs(q0),2) + pow(abs(q1),2) + pow(abs(q2),2) + pow(abs(q3),2));
        return new Quaternion(q0/length, q1/length, q2/length, q3/length);
    }

    private double dotProduct(Quaternion quaternion){
        return q0 * quaternion.q0 + q1 * quaternion.q1 + q2 * quaternion.q2 + q3 * quaternion.q3;
    }

    private Quaternion negate(){
        return new Quaternion(-q0, -q1, -q2, -q3);
    }

    private Quaternion subtract(Quaternion quaternion){
        return new Quaternion(q0 - quaternion.q0, q1 - quaternion.q1, q2 - quaternion.q2, q3 - quaternion.q3);
    }

    private Quaternion add(Quaternion quaternion){
        return new Quaternion(q0 + quaternion.q0, q1 + quaternion.q1, q2 + quaternion.q2, q3 + quaternion.q3);
    }

    private Quaternion scalarMultiplikation(double x){
        return new Quaternion(q0 * x, q1 * x, q2 * x, q3 * x);
    }

    public Vector rotate(Vector v){
        double v0 = (1 - 2*pow(q2,2) - 2*pow(q3,2)) * v.a0 + 2*(q1*q2 + q0*q3) * v.a1 + 2*(q1*q3 - q0*q2) * v.a2;
        double v1 = 2*(q1*q2 - q0*q3) * v.a0 + (1 - 2*pow(q1,2) - 2*pow(q3,2)) * v.a1 + 2*(q2*q3 + q0*q1) * v.a2;
        double v2 = 2*(q1*q3 + q0*q2) * v.a0 + 2*(q2*q3 - q0*q1) * v.a1 + (1 - 2*pow(q1,2) - 2*pow(q2,2)) * v.a2;
        return new Vector(v0, v1, v2);
    }

    public static Quaternion slerp(Quaternion v0, Quaternion v1, double t){
        v0 = v0.normalize();
        v1 = v1.normalize();

        double dot = v0.dotProduct(v1);

        if(dot < 0.0){
            v1 = v1.negate();
            dot = -dot;
        }

        double DOT_THRESHOLD = 0.9995;
        if (dot > DOT_THRESHOLD) {
            Quaternion result = v0.add(v1.subtract(v0).scalarMultiplikation(t));
            result.normalize();
            return result;
        }

        double theta_0  = acos(dot);
        double theta = theta_0*t;
        double sin_theta = sin(theta);
        double sin_theta_0 = sin(theta_0);

        double s0 = cos(theta) - dot * sin_theta / sin_theta_0;
        double s1 = sin_theta / sin_theta_0;

        return v0.scalarMultiplikation(s0).add(v1.scalarMultiplikation(s1));
    }

    @Override
    public String toString() {
        return q0 + " + " + q1 + "i + " + q2 + "j + " + q3 + "k";
    }
}
