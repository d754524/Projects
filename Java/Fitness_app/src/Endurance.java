
public abstract class Endurance implements Fitness{

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
	
		return "Endurance is all about sweat and patience";
	}

	
	
	
	
}
