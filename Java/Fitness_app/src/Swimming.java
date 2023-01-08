
public class Swimming extends Aerobic{

	private static double a;
	private  Muscle []msls;
	private SwimmingType type;
	private static double durat;
	
	public Swimming (SwimmingType type) {
		this.type=type;
		
		switch(type) {
		case Butterflystroke: msls = new Muscle[5];
			msls[0]=Muscle.Abs;
			msls[1]=Muscle.Back;
			msls[2]=Muscle.Shoulders;
			msls[3]=Muscle.Biceps;
			msls[4]=Muscle.Triceps;
		break;
		case Breaststroke: msls = new Muscle[2];
			msls[0]=Muscle.Glutes;
			msls[1]=Muscle.Cardio;
		break;
		case Freestyle: msls = new Muscle[3];
			msls[0]=Muscle.Arms;
			msls[1]=Muscle.Legs;
			msls[2]=Muscle.Cardio;
		break;
		default:
		}
	}
	
	public Swimming() {
		this.type = SwimmingType.Freestyle;
		
		msls= new Muscle[3];
		msls[0]=Muscle.Arms;
		msls[1]=Muscle.Legs;
		msls[2]=Muscle.Cardio;
		
	}
	
	
	public void setSwimmingType(SwimmingType type) {
		
		this.type = type;
		
	}
	
	public SwimmingType getSwimmingType() {
		
		return this.type;		
	}
	
	@Override
	public String description() {
		return "Swimming";
	}
	
	
	@Override
	public Muscle[] muscleTargeted() {
		
			
		return msls;
	}

	@Override
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		a=0;
		switch(intensity) {
		case LOW: a=6;
		break;
		case MEDIUM:  a=8.3;
		break;
		case HIGH: a=10;
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
		case LOW: a=6;
		break;
		case MEDIUM:  a=8.3;
		break;
		case HIGH: a=10;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
}
