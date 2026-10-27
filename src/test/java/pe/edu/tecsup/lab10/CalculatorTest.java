package pe.edu.tecsup.lab10;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private final Calculator calculator = new Calculator();

    @Test void suma() { assertEquals(7, calculator.add(3, 4)); }
    @Test void resta() { assertEquals(2, calculator.subtract(7, 5)); }
    @Test void multiplicacion() { assertEquals(42, calculator.multiply(6, 7)); }
    @Test void division() { assertEquals(4, calculator.divide(20, 5)); }
    @Test void divisionEntreCeroFalla() { assertThrows(ArithmeticException.class, () -> calculator.divide(1, 0)); }
}
