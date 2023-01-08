import java.util.ArrayList;

public class DailyExercise {

	private ArrayList<Fitness> exerciseLisT;
	private int duration;
	private double calorieTarget;
	private Profile profile;
	private ArrayList<Fitness> exercs;
	private static int count1=0;
	
	
	public DailyExercise(ArrayList<Fitness> exerciseList, int duration, double calorieTarget, Profile profile) {
		exerciseLisT= new ArrayList<Fitness>();
		exerciseLisT.addAll(exerciseList);
		this.duration=duration;
		this.calorieTarget=calorieTarget;
		this.profile= profile;
	}
	
	public DailyExercise(ArrayList<Fitness> exerciseList, Profile profile) {
		exerciseLisT= new ArrayList<Fitness>();
		exerciseLisT.addAll(exerciseList);
		this.profile= profile;
		this.duration= 60;		//hour
		this.calorieTarget=500;
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
		
		this.exerciseLisT.addAll(list);
	}
	
	public void setDuration(int duration) {
		this.duration=duration;
	}
	
	public void setCalorieTarget(double target) {
		this.calorieTarget=target;
	}
	
	public void setProfile(Profile profile) {
		this.profile=profile;
	}
	/*
	 * 
	 * @return return an ArrayList of exercises
	*/
	public ArrayList<Fitness> getExerciseList( ){
		return exerciseLisT;
	}
	
	public int getDuration() {
		return this.duration;
	}
	
	public double getCalorieTarget() {
		return this.calorieTarget;
	}
	
	public Profile getProfile() {
		return this.profile;
	}
	/*
	 * @param targetMuscle denotes an array of type Muscle with certain muscles
	 * @return return exercises from exerciseList input in the constructor that work those muscles in the parameter targetMuscle
	*/
	public Fitness[] getExercises(Muscle[] targetMuscle) {
		count1=0;
		exercs = new ArrayList<Fitness>();
		for(int i=0; i<exerciseLisT.size(); i++) {
			count1=0;
			for(int j=0; j<targetMuscle.length; j++) {
				
				for(Muscle x :exerciseLisT.get(i).muscleTargeted()) {
					if(x==targetMuscle[j]) {
						count1++;
						if(targetMuscle.length==count1) {
							
							exercs.add(exerciseLisT.get(i)); // revise
						}
					}

					}
				}
			
						
			}
		Fitness[] exercS = new Fitness[exercs.size()];
		for(int i=0 ; i<exercS.length; i++) {
			exercS[i]=exercs.get(i);
		}
		
		if(exercS.length==0) {
			exercS=null;
		}
		
		
	return exercS;
		
	}
		
		
	}
	

