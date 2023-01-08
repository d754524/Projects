
public interface Fitness {

	public Muscle[] muscleTargeted();
	
	public double calorieLoss (Intensity intensity, double weight, int duration);
	
	public double minNeeded(Intensity intensity, double weight, double cal);
	
	public String description();
	
	
}
