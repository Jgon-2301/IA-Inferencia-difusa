import java.io.*;
import java.util.*;

public class FuzzySystemLoader {

    public static List<LinguisticVariable> loadFromFile(String path) throws IOException {
        List<LinguisticVariable> variables = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String line;
        LinguisticVariable currentVar = null;

        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\s+");

            if (parts.length == 3) {
                // Nueva variable
                currentVar = new LinguisticVariable(parts[0],
                        Double.parseDouble(parts[1]),
                        Double.parseDouble(parts[2]));
                variables.add(currentVar);
            } else {
                // Conjunto difuso
                String setName = parts[0];
                String type = parts[1].toUpperCase();

                if (type.equals("TRIANGULAR") && parts.length == 5) {
                    currentVar.addFuzzySet(new TriangularFuzzySet(setName,
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4])));
                } else if (type.equals("TRAPEZOIDAL") && parts.length == 6) {
                    currentVar.addFuzzySet(new TrapezoidalFuzzySet(setName,
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]),
                            Double.parseDouble(parts[5])));
                }
            }
        }

        reader.close();
        return variables;
    }
}
