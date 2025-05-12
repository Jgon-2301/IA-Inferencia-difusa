import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
public static void main(String[] args) {
    try {
            // Cargar variables
            List<LinguisticVariable> variables = FuzzySystemLoader.loadFromFile("variables.txt");

            // Cargar reglas
            FuzzyRuleBase ruleBase = new FuzzyRuleBase(variables);
            ruleBase.loadRules("reglas.txt");
            ruleBase.printRules();

            // Inputs (puedes probar distintos valores aquí)
            Map<String, Double> inputValues = new HashMap<>();
            inputValues.put("Temperatura", 52.0);
            inputValues.put("Humedad", 72.0);

            // Fuzzificar entradas
            for (LinguisticVariable var : variables) {
                if (inputValues.containsKey(var.getName())) {
                    var.fuzzify(inputValues.get(var.getName()));
                }
            }

            // (Opcional) Imprimir variables para verificar los µ(x)
            for (LinguisticVariable var : variables) {
                System.out.println(var);
            }

            // Ejecutar inferencia
            MamdaniInferenceEngine engine = new MamdaniInferenceEngine(variables, ruleBase.getRules());
            double result = engine.infer(inputValues, "Riesgo");

            // Mostrar resultado
            System.out.println("\nResultado final (riesgo): " + result);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
