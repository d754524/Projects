
public abstract class Anaerobic implements Fitness {

	
	
	@Override
	public Muscle[] muscleTargeted() {
		
		return null;
	}

	@Override
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		
		return 0;
	}

	@Override
	public String description() {
		
		return "Anaerobic means \"without oxygen\"";
	}

	
	
	
	
}
