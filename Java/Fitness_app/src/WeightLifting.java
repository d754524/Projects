
public class WeightLifting extends Anaerobic {
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	@Override
	public String description() {
		
		return "WeightLifting";	
	}
	
	public WeightLifting() {
		
		msls = new Muscle[4];
		
		msls[0]=Muscle.Shoulders;
		msls[1]=Muscle.Legs;
		msls[2]=Muscle.Arms;
		msls[3]=Muscle.Triceps;
	}
	
	public Muscle[] muscleTargeted() {
	
		return msls;		
	}
	
	
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		a=0;
		switch(intensity) {
		case LOW: a=3.5;
		break;
		case MEDIUM:  a=5.0;
		break;
		case HIGH: a=6.0;
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
		case LOW: a=3.5;
		break;
		case MEDIUM:  a=5.0;
		break;
		case HIGH: a=6.0;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
}
