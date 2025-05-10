import java.util.ArrayList;
import java.util.List;

public class LinguisticVariable {

        private String name;
    private double min, max;
    private List<FuzzySet> sets = new ArrayList<>();

    public LinguisticVariable(String name, double min, double max) {
        this.name = name;
        this.min = min;
        this.max = max;
    }

    public void addFuzzySet(FuzzySet set) {
        sets.add(set);
    }

    public String getName() {
        return name;
    }

    public List<FuzzySet> getFuzzySets() {
        return sets;
    }

    public double getMembership(String setName, double x) {
        for (FuzzySet set : sets) {
            if (set.getName().equalsIgnoreCase(setName)) {
                return set.membership(x);
            }
        }
        return 0;
    }

    public void fuzzify(double x) {
        for (FuzzySet set : sets) {
            double value = set.membership(x);
            set.setMembershipValue(value);
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Variable Lingüística: ").append(name)
          .append(" [").append(min).append(", ").append(max).append("]\n");

        for (FuzzySet set : sets) {
            sb.append("  - Conjunto: ").append(set.getName());

            if (set instanceof TriangularFuzzySet) {
                TriangularFuzzySet t = (TriangularFuzzySet) set;
                sb.append(" (Triangular)")
                  .append(" [a=").append(t.getA())
                  .append(", b=").append(t.getB())
                  .append(", c=").append(t.getC())
                  .append("]");
            } else if (set instanceof TrapezoidalFuzzySet) {
                TrapezoidalFuzzySet t = (TrapezoidalFuzzySet) set;
                sb.append(" (Trapezoidal)")
                  .append(" [a=").append(t.getA())
                  .append(", b=").append(t.getB())
                  .append(", c=").append(t.getC())
                  .append(", d=").append(t.getD())
                  .append("]");
            }

            sb.append(" → Último µ(x)=")
              .append(String.format("%.3f", set.getMembershipValue()))
              .append("\n");
        }

        return sb.toString();
    }

}
