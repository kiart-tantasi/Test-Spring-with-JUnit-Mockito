package calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.spy;

@RunWith(MockitoJUnitRunner.class)
public class SpyTest {

    private Calculator calculator;
    private CalculatorService calculatorService;

    @Before
    public void setUp() {
        calculator = new Calculator();
        ImplCalculatorService implCalculatorService = new ImplCalculatorService();
        calculatorService = spy(implCalculatorService); // spy on real implementation
        calculator.setCalculatorService(calculatorService);
    }

    @Test
    public void shouldAdd() {
        assertEquals(2.0, calculator.add(1.0, 1.0), 0);
    }

    @Test
    public void shouldNotEqual() {
        assertNotEquals(3.0, calculator.subtract(5.0, 2.0), 0);
        // Subtract method is not implemented yet.
    }


}
