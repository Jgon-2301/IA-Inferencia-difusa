import java.io.IOException;
import java.util.List;

public class Main {
public static void main(String[] args) {
        try {
            List<LinguisticVariable> variables = FuzzySystemLoader.loadFromFile("IA-inferencia-difusa\\variables.txt");

            /*double inputValue = 50;

            for (LinguisticVariable var : variables) {
                var.fuzzify(inputValue);
                System.out.println(var);
            }*/

            FuzzyRuleBase ruleBase = new FuzzyRuleBase(variables);
            ruleBase.loadRules("IA-inferencia-difusa\\reglas.txt");
            ruleBase.printRules();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
