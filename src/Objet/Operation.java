package Objet;
import java.io.Serializable;
public class Operation implements Serializable{
    private double a;
    private String op;
    private double b;

    public Operation(double a, String op, double b) {
        this.a = a;
        this.op = op;
        this.b = b;
    }

    public double getA() {
        return a;
    }

    public String getOperateur() {
        return op;
    }
    public double getB() {
        return b;
    }
}
