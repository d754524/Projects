
public class Cycling extends Aerobic{
	private static double a;
	private  Muscle []msls;
	private static double durat;
	
	public Cycling() {
		msls = new Muscle[3];
		msls[0]= Muscle.Glutes;
		msls[1]= Muscle.Cardio;
		msls[2]= Muscle.Legs;
	}
	
	/*
	 * @return return a description of this specific exercise
	*/
	@Override
	public String description() {
		return "Cycling";
	}
	
	/*
	 * @return this method returns an array of type Muscle with the name of the muscles you're working out
	*/
	@Override
	public Muscle[] muscleTargeted() {
		
		return msls;
	}
	/*
	 * 
	 * @return this method returns the number of calories lost depending on intensity, weight and duration of an exercise 
	*/
	@Override
	public double calorieLoss(Intensity intensity, double weight, int duration) {
		
		switch(intensity) {
		case LOW: a=4.0;
		break;
		case MEDIUM:  a=8.5;
		break;
		case HIGH: a=14.0;
		break;
		default:
		}
		
		double dura = (double)(duration);
		double x = (dura/60)*a*weight;
		
		return x;
		
	}
	/*
	 * @param cal denotes the number of calories to be burnt
	 * @return this method returns the duration needed to burn the number of calories that was input as a parameter
	*/
	public double minNeeded(Intensity intensity, double weight, double cal ) {
		durat =0;
		a=0;
		switch(intensity) {
		case LOW: a=4.0;
		break;
		case MEDIUM:  a=8.5;
		break;
		case HIGH: a=14.0;
		break;
		default:
		}
		
		durat = (cal*60)/(a*weight);
		
		return durat;
	}
	

}
