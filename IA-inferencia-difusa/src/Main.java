import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // Cargar variables
            List<LinguisticVariable> variables = FuzzySystemLoader.loadFromFile("IA-inferencia-difusa\\variables3.txt");

            // Cargar reglas
            FuzzyRuleBase ruleBase = new FuzzyRuleBase(variables);
            ruleBase.loadRules("IA-inferencia-difusa\\reglas3.txt");
            ruleBase.printRules();

            // Inputs (puedes probar distintos valores aquí)
            Map<String, Double> inputValues = new HashMap<>();
            inputValues.put("Temperatura", 15.0);
            inputValues.put("Humedad",41.0);
            //inputValues.put("Carga", 0.5);

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
