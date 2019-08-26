package cashregister;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CashRegisterTest {
	static MockPrinter mockPrinter;
	static CashRegister cashRegister;
	
	@BeforeAll
	public static void init() {
		mockPrinter = new MockPrinter();
    	cashRegister = new CashRegister(mockPrinter);
	}
	
	@BeforeEach
	public void clean() {
		mockPrinter.setPrintThis("");
	}

    @Test
    public void should_print_the_real_purchase_when_call_process() {
        //given
    	Item[] items = {new Item("test product", 1)};
    	Purchase purchase = new Purchase(items);
    	
        //when
    	cashRegister.process(purchase);
        //then
    	assertEquals("test product\t1.0\n", mockPrinter.getPrintThis());
    }

    @Test
    public void should_print_the_stub_purchase_when_call_process() {
        //given
    	StubPurchase stubPurchase = new StubPurchase();
    
        //when
    	cashRegister.process(stubPurchase);
        //then
    	assertEquals("test product\t1.0\n", mockPrinter.getPrintThis());
    }

    @Test
    public void should_verify_with_process_call_with_mockito() {
        //given
    	Printer printer = Mockito.mock(Printer.class);
    	Purchase purchase = Mockito.mock(Purchase.class);
    	Mockito.when(purchase.asString()).thenReturn("test product");
        //when
    	
    	CashRegister cashRegister = new CashRegister(printer);
    	cashRegister.process(purchase);
        //then
    	verify(printer).print("test product");
    }

}
