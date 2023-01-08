
public class Plyometrics extends Anaerobic {
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	public Plyometrics() {
		msls = new Muscle[3];
		
		msls[0]=Muscle.Abs;
		msls[1]=Muscle.Legs;
		msls[2]=Muscle.Glutes;
		
		
	}
	
	public Muscle[] muscleTargeted() {
		
		return msls;		
	}
	
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		a=0;
		switch(intensity) {
		case LOW: a=2.5;
		break;
		case MEDIUM:  a=4.8;
		break;
		case HIGH: a=7.4;
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
		case MEDIUM:  a=4.8;
		break;
		case HIGH: a=7.4;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
	
	@Override
	public String description() {
		
		return "Plyometrics";	
	}
	
	
	
	
	
	
}
