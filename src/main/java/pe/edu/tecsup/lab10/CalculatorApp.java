package pe.edu.tecsup.lab10;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/** Ejecuta operaciones y deja trazas de entrada y resultado en Log4J 2. */
public class CalculatorApp {
    private static final Logger LOG = LogManager.getLogger(CalculatorApp.class);
    private final Calculator calculator = new Calculator();

    public double calculate(String operation, double a, double b) {
        LOG.info("Operación solicitada: {}({}, {})", operation, a, b);
        double result = switch (operation.toLowerCase()) {
            case "sumar" -> calculator.add(a, b);
            case "restar" -> calculator.subtract(a, b);
            case "multiplicar" -> calculator.multiply(a, b);
            case "dividir" -> calculator.divide(a, b);
            default -> throw new IllegalArgumentException("Operación no soportada: " + operation);
        };
        LOG.info("Resultado: {}", result);
        return result;
    }

    public static void main(String[] args) {
        CalculatorApp app = new CalculatorApp();
        app.calculate("sumar", 8, 4);
        app.calculate("multiplicar", 8, 4);
        app.calculate("dividir", 8, 4);
    }
}
