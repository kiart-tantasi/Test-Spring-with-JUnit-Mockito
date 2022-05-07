package calculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CreateMockTest {
    private CalculatorService calculatorService;
    private Calculator calculator;

    @Before
    public void setUp() {
        calculator = new Calculator();
        calculatorService = mock(CalculatorService.class);
        calculator.setCalculatorService(calculatorService);
    }

    @Test
    public void shouldAdd() {
        when(calculatorService.add(5.0,5.0)).thenReturn(10.0);
        assertEquals(10.0,
                calculator.add(5.0, 5.0),
                0);
        verify(calculatorService).add(5.0, 5.0);
    }

    @Test
    public void shouldSubtract() {
        when(calculatorService.subtract(10.0,5.0)).thenReturn(5.0);
        assertEquals(5.0,
                calculator.subtract(10.0, 5.0),
                0);
        verify(calculatorService).subtract(10.0, 5.0);
    }

    @Test
    public void shouldBeInOrder() {
        when(calculatorService.add(10.0, 5.0)).thenReturn(15.0);
        when(calculatorService.subtract(10.0, 5.0)).thenReturn(5.0);
        when(calculatorService.divide(10.0, 5.0)).thenReturn(2.0);

        assertEquals(15.0, calculator.add(10.0, 5.0), 0);
        assertEquals(5.0, calculator.subtract(10.0, 5.0), 0);
        assertEquals(2.0, calculator.divide(10.0, 5.0), 0);

        InOrder inOrder = inOrder(calculatorService);
        inOrder.verify(calculatorService).add(10.0, 5.0);
//        inOrder.verify(calculatorService).divide(10.0, 5.0); // will fail the order
        inOrder.verify(calculatorService).subtract(10.0, 5.0);
        inOrder.verify(calculatorService).divide(10.0, 5.0);
    }

    @Test
    public void testCallBacks() {
        when(calculatorService.add(1.0, 2.0)).thenAnswer(new Answer<Double>() {

            @Override
            public Double answer(InvocationOnMock invocationOnMock) throws Throwable {

                // get the arguments passed to mock
                Object[] args = invocationOnMock.getArguments();
                System.out.println("ARGS: " + args);

                // get the mock
                Object mock = invocationOnMock.getMock();
                System.out.println("MOCK: " + mock);

                return 3.0;
            }
        });

        assertEquals(3.0, calculator.add(1.0, 2.0), 0);
    }


}
