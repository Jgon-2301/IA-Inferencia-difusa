import java.util.*;

public class MamdaniInferenceEngine {

    private List<LinguisticVariable> variables;
    private List<FuzzyRule> rules;

    public MamdaniInferenceEngine(List<LinguisticVariable> variables, List<FuzzyRule> rules) {
        this.variables = variables;
        this.rules = rules;
    }

    public double infer(Map<String, Double> inputs, String outputVariableName) {
        // Fuzzificación
        for (Map.Entry<String, Double> entry : inputs.entrySet()) {
            LinguisticVariable var = findVariable(entry.getKey());
            if (var != null) {
                System.out.println("Fuzzificando variable: " + var.getName() + " con valor: " + entry.getValue());
                var.fuzzify(entry.getValue());
            }
        }

        // Inicializar mapa de conjuntos activados para la variable de salida
        LinguisticVariable outputVar = findVariable(outputVariableName);
        Map<String, List<Double>> outputMemberships = new HashMap<>();

        // Evaluación de reglas (inferencia)
        System.out.println("\n--- Evaluación de Reglas ---");
        for (FuzzyRule rule : rules) {
            double mu1 = findVariable(rule.getAntecedent1Var()).getMembership(rule.getAntecedent1Set(),
                    inputs.get(rule.getAntecedent1Var()));
            double mu2 = findVariable(rule.getAntecedent2Var()).getMembership(rule.getAntecedent2Set(),
                    inputs.get(rule.getAntecedent2Var()));
            double activation = Math.min(mu1, mu2); // Operador AND (mínimo)
            System.out.println(rule.getRuleText() + " → Activación: " + activation);

            // Acumulación de activación por conjunto del consecuente
            outputMemberships.computeIfAbsent(rule.getConsequentSet(), k -> new ArrayList<>()).add(activation);
        }

        // Agregación (tomamos el máximo por conjunto)
        Map<String, Double> aggregated = new HashMap<>();
        for (Map.Entry<String, List<Double>> entry : outputMemberships.entrySet()) {
            aggregated.put(entry.getKey(), Collections.max(entry.getValue()));
        }

        // Desfuzzificación por centroide
        return defuzzify(outputVar, aggregated);
    }

    private double defuzzify(LinguisticVariable outputVar, Map<String, Double> aggregatedSets) {
        System.out.println("\n--- Desfuzzificación ---");
        double num = 0.0;
        double denom = 0.0;
        int steps = 100;
        double max = 0.0, clipped = 0.0;
        double xfake = 0.0;

        double stepSize = (outputVar.getMax() - outputVar.getMin()) / steps;

        for (int i = 0; i <= steps; i++) {
            double x = outputVar.getMin() + i * stepSize;
            double maxMu = 0.0;
            xfake = x;

            for (FuzzySet set : outputVar.getFuzzySets()) {
                Double activation = aggregatedSets.get(set.getName());
                if (activation != null) {
                    double clippedMu = Math.min(activation, set.membership(x));
                    // Guardar valor
                    clipped = Math.min(activation, set.membership(x));
                    // Guardar maximo
                    maxMu = Math.max(maxMu, clippedMu);
                    max = Math.max(max, maxMu);
                }
            }
            num += x * maxMu;
            denom += maxMu;
        }
        // mostrar max y clipped
        System.out.println("x: " + xfake + " MinMu: " + clipped);
        System.out.println("x: " + xfake + " MaxMu: " + max);

        double result = denom == 0 ? 0 : num / denom;
        System.out.println("Resultado crisp = " + result);
        return result;
    }

    private LinguisticVariable findVariable(String name) {
        for (LinguisticVariable v : variables) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }

}
