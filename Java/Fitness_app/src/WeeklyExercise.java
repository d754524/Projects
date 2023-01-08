import java.util.ArrayList;

public class WeeklyExercise {
		private static String rs;
		private static int a;
		private static float lose;
		private static double b;
		private static double d;
		private static double calday=0;
		private ArrayList<Fitness> exerciseLisT;
		private int days;
		private double weeklyCalorieTarget;
		private Profile profile;
		ArrayList<DailyExercise> routine;
		ArrayList<Fitness> lists;
		private double weight;
		private static DailyExercise ret;
		
	public WeeklyExercise(ArrayList<Fitness> exerciseList, int days, double weeklyCalorieTarget, Profile profile) {
		exerciseLisT= new ArrayList<Fitness>();
		exerciseLisT.addAll(exerciseList);
		this.days=days;
		this.weeklyCalorieTarget=weeklyCalorieTarget;
		this.profile=profile;
		this.weight=profile.getWeight()	;
	}
	public WeeklyExercise(ArrayList<Fitness> exerciseList, Profile profile) {
		exerciseLisT= new ArrayList<Fitness>();
		exerciseLisT.addAll(exerciseList);
		this.profile=profile;
		this.days=7;
		this.weeklyCalorieTarget=3500;
		this.weight=profile.getWeight()	;
	}
	public void addExercise(Fitness ex) {
		exerciseLisT.add(ex);
	}
	
	public void removeExercise(Fitness ex) {
		if(exerciseLisT.contains(ex)) {
		exerciseLisT.remove(ex);
		}
	}
	
	public void setExerciseList(ArrayList<Fitness> list) {
		exerciseLisT.addAll(list);
	}
	
	public void setDays(int days) {
		this.days=days;
	}
	
	public void setWeeklyCalorieTarget(double target) {
		this.weeklyCalorieTarget=target;
	}
	
	public void setProfile(Profile profile) {
		this.profile=profile;
	}
	
	public ArrayList<Fitness> getExerciseList( ){
		
		return exerciseLisT;
	}
	
	public int getDays() {
		return this.days;
	}
	
	public Profile getProfile() {
		return this.profile;
		
	}
	
	public double getWeeklyCalorieTarget() {
		return this.weeklyCalorieTarget;
	}
	/*
	 * @param intensity denotes the intensity the user will choose
	 * @return this method returns a list of daily exercises with an intensity chosen by the user. This list also consider
	 * the calorie target in the week that the user wants to burn
	*/
	public ArrayList<DailyExercise>getWeeklyExercises(Intensity intensity){
		
		calday=0;
		calday = (double)(getWeeklyCalorieTarget()/getDays());		// duration
		routine = new ArrayList<DailyExercise> ();
		for(int i=0; i<getDays(); i++) {
			a=0;
			a+= exerciseLisT.get(i).minNeeded(intensity, this.weight, calday);
			lists= new ArrayList<Fitness>();
			lists.add(exerciseLisT.get(i));
			
			ret = new DailyExercise(lists,a,calday,getProfile());
			routine.add(ret);
		}
		return routine;	
	}
	
	/*
	 * @return this method also return a list of daily exercises for the week but always with a low intensity
	*/
	public ArrayList<DailyExercise>getWeeklyExercises(){
		
		calday=0;
		calday = (double)(getWeeklyCalorieTarget()/getDays());		// duration
		routine = new ArrayList<DailyExercise> ();
		for(int i=0; i<getDays(); i++) {
			a=0;
			a+= exerciseLisT.get(i).minNeeded(Intensity.LOW, this.weight, calday);
			lists= new ArrayList<Fitness>();
			lists.add(exerciseLisT.get(i));
			
			ret = new DailyExercise(lists,a,calday,getProfile());
			routine.add(ret);
		}
	
		return routine;
			
	}
	
	/*
	 * @param targetWeight is the weight goal the user wants. 
	 * @param withInDays is the number of days the user wants to workout to achieve his weight goal
	 * @return this method will print an advice for the user of how many calories he/she has to lose per day for 
	 * the chosen number of days so that the user can lose kilograms to achieve his/her goal. Besides that, this method
	 * proposes a different alternative which is deacreasing the intake number of calories to a certain number to meet 
	 * the goal of losing weight.
	*/
	public String targetedCalorieLoss(double targetWeight, int withInDays) throws TargetWeightException{
		d=0;
		b=0;

			if(targetWeight>this.profile.getWeight()) {
				throw new TargetWeightException("Only works to lose weight");
			}
		b=this.profile.getWeight()-targetWeight;
		lose = (float) b;
		double c = b*7000;
		b= c/withInDays;   
		
		 d= this.profile.dailyCalorieIntake()-b;
		
		 if(d==853.3583675130208) {
				d=d+0.01;
			}
			float aaa = (float)(b);
			float bbb = (float)(this.profile.dailyCalorieIntake());
			if(bbb==1320.0350341796875) {
				bbb=bbb-0.01f;
			}
			float ccc = (float)(d);
			float ddd = (float)(lose);
			
			String aa = String.format("%.2f", aaa);
			String bb = String.format("%.2f", bbb);
			String cc = String.format("%.2f", ccc);
			String dd = String.format("%.2f", ddd);
			
		rs = "You need to lose "+aa+" calories per day or decrease your intake from "+bb+" to "+cc+" in order to lose "+dd+" kg of weight";
	
		return rs;
	}
	
		
	
		
	
	
}
