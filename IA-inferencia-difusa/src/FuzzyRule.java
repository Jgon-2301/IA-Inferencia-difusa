public class FuzzyRule {
    private String antecedent1Var; // Primera variable de entrada
    private String antecedent1Set; // Conjunto de la primera variable
    private String antecedent2Var; // Segunda variable de entrada
    private String antecedent2Set; // Conjunto de la segunda variable
    private String consequentVar; // Variable de salida
    private String consequentSet; // Conjunto de la variable

    public FuzzyRule(String a1Var, String a1Set, String a2Var, String a2Set, String cVar, String cSet) {
        this.antecedent1Var = a1Var;
        this.antecedent1Set = a1Set;
        this.antecedent2Var = a2Var;
        this.antecedent2Set = a2Set;
        this.consequentVar = cVar;
        this.consequentSet = cSet;
    }

    public String getRuleText() {
        return "IF " + antecedent1Var + " IS " + antecedent1Set +
               " AND " + antecedent2Var + " IS " + antecedent2Set +
               " THEN " + consequentVar + " IS " + consequentSet;
    }

    public String getAntecedent1Var() {
    return antecedent1Var;
}

public void setAntecedent1Var(String antecedent1Var) {
    this.antecedent1Var = antecedent1Var;
}

public String getAntecedent1Set() {
    return antecedent1Set;
}

public void setAntecedent1Set(String antecedent1Set) {
    this.antecedent1Set = antecedent1Set;
}

public String getAntecedent2Var() {
    return antecedent2Var;
}

public void setAntecedent2Var(String antecedent2Var) {
    this.antecedent2Var = antecedent2Var;
}

public String getAntecedent2Set() {
    return antecedent2Set;
}

public void setAntecedent2Set(String antecedent2Set) {
    this.antecedent2Set = antecedent2Set;
}

public String getConsequentVar() {
    return consequentVar;
}

public void setConsequentVar(String consequentVar) {
    this.consequentVar = consequentVar;
}

public String getConsequentSet() {
    return consequentSet;
}

public void setConsequentSet(String consequentSet) {
    this.consequentSet = consequentSet;
}

}
