
public class TriangularFuzzySet extends FuzzySet {

    private double a, b, c;

    public TriangularFuzzySet(String name, double a, double b, double c) {
        super(name);
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public double membership(double x) {
        if (x <= a || x >= c) return 0;
        if (x == b) return 1;
        if (x < b) return (x - a) / (b - a);
        return (c - x) / (c - b);
        
    }

    public double getA() { return a; }
    public double getB() { return b; }
    public double getC() { return c; }
}

