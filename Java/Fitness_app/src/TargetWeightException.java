
//@SuppressWarnings("serial")
public class TargetWeightException extends RuntimeException{

	public TargetWeightException() {
		super("Only works to lose weight");
	}
	
	public TargetWeightException(String message) {
		super(message);
	}
	
}
