package cashregister;

public class MockPrinter extends Printer {
	private String printThis = "";
	
	@Override
	public void print(String printThis) {
		super.print(printThis);
		this.printThis = printThis;
	}
	
	public String getPrintThis() {
		return printThis;
	}
	public void setPrintThis(String printThis) {
		this.printThis = printThis;
	}
	
}
