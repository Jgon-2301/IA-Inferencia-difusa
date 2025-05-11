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
}
