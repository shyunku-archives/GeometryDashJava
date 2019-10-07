package Exceptions;

public class FatalException extends Exception{
	public FatalException(){
		super("Fatal Exception Occured!");
	}
	
	public void throwThis() {
		try {
			throw this;
		}catch(FatalException e) {
			e.printStackTrace();
		}
		System.exit(-1);
	}
}