
public class Yoga extends Flexibility {
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	public Yoga() {
		msls = new Muscle[2];
		msls[0]= Muscle.Triceps;
		msls[1]= Muscle.Biceps;
	}
	
	
	@Override
	public String description() {
		return "Yoga";
	}
	
	@Override
	public Muscle[] muscleTargeted() {
		
		return msls;
	}

	public double calorieLoss(Intensity intensity, double weight, int duration) {
		a=0;
		switch(intensity) {
		case LOW: a=2.0;
		break;
		case MEDIUM:  a=3.0;
		break;
		case HIGH: a=4.0;
		break;
		default:
		}
		
		double dura = (double)(duration);
		double x = (dura/60)*a*weight;
		
		return x;
	
	}
	public double minNeeded(Intensity intensity, double weight, double cal ) {
		durat =0;
		a=0;
		switch(intensity) {
		case LOW: a=2.0;
		break;
		case MEDIUM:  a=3.0;
		break;
		case HIGH: a=4.0;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
}
