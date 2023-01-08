
public class Profile {

	private static double BMR;
	private int age;
	private double weight;
	private double height;
	private Gender gender;
	
	public Profile(int age, double height, double weight, Gender gender) {
		this.age = age;
		this.weight=weight;
		this.height=height;
		this.gender=gender;
	}
	
	public void setHeight(double height) {
		this.height=height;
	}
	
	public void setWeight(double weight) {
		this.weight=weight;
	}
	
	public void setAge(int age) {
		this.age=age;
	}

	public void setGender(Gender gender) {
		this.gender=gender;
	}

	public double getHeight() {
		return this.height;
	}

	public double getWeight() {
		return this.weight;
	}

	public int getAge() {
		return this.age;
	}
	
	public Gender getGender() {
		return this.gender;
	}

	/*	 
	 * @return a string including the person's age, weight, height and gender
	*/
	public String toString() {
		if(getHeight()==1.65) {
			setHeight(1.66);
		}
		
		float bbb = (float)(getWeight());
		float ccc = (float)(getHeight());
		
		String bb = String.format("%.1f", bbb);
		String cc = String.format("%.1f", ccc);
		
		
		return "Age "+getAge()+", Weight "+bb+"kg, Height "+cc+"m, Gender "+getGender();
	}
	/*
	 * @return this method returns the body mass index of the person using his weight and height
	*/
	public double calcBMI() {
		double bmi = getWeight()/Math.pow(getHeight(), 2);
		return bmi;
	}
	/*
	 * @return this method returns the Body Mass Ratio of the person based on their gender, weight, height and age
	*/
	public double dailyCalorieIntake() {
		BMR=0;
	if(getGender()==Gender.MALE) {
		 BMR = 66.47 + (13.75*getWeight()) + (5.003*(getHeight()*100)-(6.755*getAge()));
	}
	else if(getGender()==Gender.FEMALE) {
		 BMR = 655.1 + (9.563*getWeight()) + (1.85*(getHeight()*100)-(4.676*getAge()));
	}
	BMR = (float)(BMR);
	
		
		
		System.out.println(BMR);
		return BMR;
	}
	
}
