
public abstract class Flexibility implements Fitness {

	
	@Override
	public Muscle[] muscleTargeted() {
		
		return null;
	}

	@Override
	public String description() {
		return "Flexibility is uncomfortable and it takes time, so people don't like to do it";
	}


	@Override
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		
		return 0;
	}
	
	
}
