package calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorTest {

    @InjectMocks
    private Calculator calculator = new Calculator();

    @Mock
    private CalculatorService calculatorService;

    @Test
    public void testAdd() {
        when(calculatorService.add(5.0, 2.0)).thenReturn(7.0);
        assertEquals(7.0, calculator.add(5.0, 2.0), 0);
        verify(calculatorService).add(5.0, 2.0);
    }

    @Test
    public void shouldRunThreeTimes() {
        when(calculatorService.add(1.0, 1.0)).thenReturn(2.0);
        assertEquals(2.0, calculator.add(1.0, 1.0),0);
        assertEquals(2.0, calculator.add(1.0, 1.0),0);
        assertEquals(2.0, calculator.add(1.0, 1.0),0);
        verify(calculatorService, times(3)).add(1.0, 1.0);
        verify(calculatorService, never()).divide(1.0, 1.0);
    }

    @Test
    public void shouldDivideAtLeastOnceAndTheMostTwice() {
        when(calculatorService.divide(1.0, 1.0)).thenReturn(1.0);
        assertEquals(1.0, calculator.divide(1.0, 1.0),0);
        assertEquals(1.0, calculator.divide(1.0, 1.0),0);
        verify(calculatorService, atLeast(1)).divide(1.0, 1.0);
        verify(calculatorService, atMost(2)).divide(1.0, 1.0);
    }

    @Test
    public void shouldThrow() {
        doThrow(new IllegalArgumentException("Can't add 1 and 1"))
                .when(calculatorService).add(1.0, 1.0);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    calculator.add(1.0, 1.0);
                });
//        assertEquals(2.0, calculator.add(1.0, 1.0), 0);
    }

    @Test
    public void shouldReset() {
        when(calculatorService.add(1.0, 1.0)).thenReturn(2.0);

        assertEquals(2.0, calculator.add(1.0, 1.0), 0);

        reset(calculatorService);

        assertEquals(0.0, calculator.add(1.0, 1.0), 0);
    }



    // Behavior Driven Development
    @Test
    public void shouldGivenWhenAssert() {

        // Given
        given(calculatorService.add(3.0, 2.0)).willReturn(5.0);

        // When
        double sum = calculator.add(3.0, 2.0);

        // Then
        assertEquals(sum, 5.0, 0);

    }


    // timeouts
    @Test
    public void shouldBeFast() {
        when(calculatorService.add(3.0, 2.0)).thenReturn(5.0);

        assertEquals(5.0, calculator.add(3.0, 2.0), 0);

        verify(calculatorService, timeout(10).times(1)).add(3.0, 2.0);

    }


}
