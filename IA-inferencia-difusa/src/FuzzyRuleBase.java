import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FuzzyRuleBase {
    private List<FuzzyRule> rules = new ArrayList<>(); // Lista de reglas
    private List<LinguisticVariable> variables; // Lista de variables

    public FuzzyRuleBase(List<LinguisticVariable> variables) {
        this.variables = variables;
    }

    // Función para cargar reglas desde archivo
    public void loadRules(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;

            // Dividir la línea en tokens, separados por espacios
            String[] segments = line.split("\\s+");

            String a1Var = segments[1];
            String a1Set = segments[3];
            String a2Var = segments[5];
            String a2Set = segments[7];
            String cVar = segments[9];
            String cSet = segments[11];

            // Validar existencia de variables y conjuntos en la base cargada
            LinguisticVariable var1 = findVariableByName(a1Var);
            LinguisticVariable var2 = findVariableByName(a2Var);
            LinguisticVariable varC = findVariableByName(cVar);
            System.out.println("var1: " + var1 + " var2: " + var2 + " varC: " + varC);

            // Validar existencia de variables
            if (var1 == null || var2 == null || varC == null) {
                throw new RuntimeException("Variable no encontrada en archivo de variables");
            }
            // Validar existencia de conjuntos
            if (!hasSet(var1, a1Set) || !hasSet(var2, a2Set) || !hasSet(varC, cSet)) {
                throw new RuntimeException("Conjunto no encontrado en variable correspondiente");
            }

            // Crear regla
            FuzzyRule rule = new FuzzyRule(a1Var, a1Set, a2Var, a2Set, cVar, cSet);
            rules.add(rule);
        }
        reader.close();
    }

    private LinguisticVariable findVariableByName(String name) {
        for (LinguisticVariable v : variables) {
            if (v.getName().equalsIgnoreCase(name)) {
                return v;
            }
        }
        return null;
    }

    private boolean hasSet(LinguisticVariable var, String setName) {
        for (FuzzySet s : var.getFuzzySets()) {
            if (s.getName().equalsIgnoreCase(setName)) {
                return true;
            }
        }
        return false;
    }

    public void printRules() {
        for (FuzzyRule rule : rules) {
            System.out.println(rule.getRuleText());
        }
    }

    public List<FuzzyRule> getRules() {
    return rules;
    }

}

