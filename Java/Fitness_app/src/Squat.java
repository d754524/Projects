
public class Squat extends Endurance {
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	public Squat() {
		msls = new Muscle[3];
		msls[0]=Muscle.Glutes;
		msls[1]=Muscle.Abs;
		msls[2]=Muscle.Back;

	}
	
	
	public Muscle[] muscleTargeted() {
	
		return msls;
	}

	
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		
		a=0;
		switch(intensity) {
		case LOW: a=2.5;
		break;
		case MEDIUM:  a=5.0;
		break;
		case HIGH: a=7.0;
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
		case LOW: a=2.5;
		break;
		case MEDIUM:  a=5.0;
		break;
		case HIGH: a=7.0;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
	
	public String description() {
	
		return "Squat";
	}
	
}
