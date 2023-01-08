
public class TaiChi extends Flexibility{
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	public TaiChi() {
		msls = new Muscle[2];
		msls[0]=Muscle.Arms;
		msls[1]=Muscle.Legs;
	}
	
	
	public double calorieLoss(Intensity intensity, double weight, int duration) {
	
		a=0;
		switch(intensity) {
		case LOW: a=1.5;
		break;
		case MEDIUM:  a=3.0;
		break;
		case HIGH: a=5.0;
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
		case LOW: a=1.5;
		break;
		case MEDIUM:  a=3.0;
		break;
		case HIGH: a=5.0;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
	public Muscle[] muscleTargeted() {
	
	return msls;
	}
	

	@Override
	public String description() {
		return "TaiChi";
	}
}
