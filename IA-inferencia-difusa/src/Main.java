import java.io.IOException;
import java.util.List;

public class Main {
public static void main(String[] args) {
        try {
            List<LinguisticVariable> variables = FuzzySystemLoader.loadFromFile("variables.txt");

            double inputValue = 50;

            for (LinguisticVariable var : variables) {
                var.fuzzify(inputValue);
                System.out.println(var);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
