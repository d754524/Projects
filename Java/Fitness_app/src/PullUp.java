
public class PullUp extends Endurance{
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	public PullUp() {
		msls = new Muscle[2];
		msls[0]=Muscle.Biceps;
		msls[1]=Muscle.Arms;
	}
	
	public Muscle[] muscleTargeted() {
	
		return msls;
	}

	
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		
		a=0;
		switch(intensity) {
		case LOW: a=4.8;
		break;
		case MEDIUM:  a=6.0;
		break;
		case HIGH: a=7.5;
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
		case LOW: a=4.8;
		break;
		case MEDIUM:  a=6.0;
		break;
		case HIGH: a=7.5;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
	
	public String description() {
	
		return "PullUp";
	}
	
}
