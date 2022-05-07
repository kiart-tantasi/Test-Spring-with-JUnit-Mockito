import java.util.List;

import static org.mockito.Mockito.*;

public class MainApp {
    public static void main(String[] args) {

        // 1 simple example
        // create mock
        DummyService dummyService = mock(DummyService.class);

        // mocking behaviors
        when(dummyService.doSomething("A")).thenReturn("Ant");
        when(dummyService.doSomething("B")).thenReturn("Boy");

        System.out.println("TEST RESULT (A is Ant): " + (dummyService.doSomething("A") == "Ant"));
        System.out.println("TEST RESULT (B is Bad): " + (dummyService.doSomething("B") == "Bad"));



        // 2 verify
        //
        List mockedList = mock(List.class);

        // using mock object - it does not throw any "unexpected interaction" exception
        mockedList.add("one");
        mockedList.clear();

        // selective, explicit, highly readable verification
        verify(mockedList).add("one");
        verify(mockedList).clear();



        // 3 stubbing (when ... thenReturn ... )
        System.out.println("GET 0: " + mockedList.get(0));

        when(mockedList.get(0)).thenReturn("one");

        System.out.println("GET 0: " + mockedList.get(0));




    }
}
