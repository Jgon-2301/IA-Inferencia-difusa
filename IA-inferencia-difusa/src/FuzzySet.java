public abstract class FuzzySet {
    protected String name;
    protected double membershipValue;

    public FuzzySet(String name) {
        this.name = name;
        this.membershipValue = 0.0;
    }

    public String getName() {
        return name;
    }

    public double getMembershipValue() {
        return membershipValue;
    }

    public void setMembershipValue(double value) {
        this.membershipValue = value;
    }

    public abstract double membership(double x);
}
