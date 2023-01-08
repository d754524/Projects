
public class Person {
	private float a;
	private boolean x;
	private static int id =0;{
		id++;
	}
	private String name;	
	private String birthday;  
	private String SSN;
	private float income;
	
	public Person() {
		
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		Person.id = id;	
	}

	public String getName() {
		return name;
	}

	public boolean setName(String name) {
		for(char c :name.toCharArray()) {
			if(Character.isDigit(c)||c=='_' || c=='-'||c=='*') {
				x=false;
				break;
			}
			else if(Character.isDigit(c)==false) {
				x=true;
				this.name=name;
				}
		}
		return x;
	}

	public String getBirthday() {
		return birthday;
	}

	public boolean setBirthday(String birthday) {
		
		
			if(birthday.matches(".*[a-z].*")){
				 x=false;
			}
			else if(birthday.length()<10||birthday.length()>10) {
				x=false;
			}
			
			else {
				x=true;
				this.birthday = birthday;
				}

		return x;
	}

	public String getSsn() {
		return SSN;
	}

	public boolean setSSN(String ssn) {
		if(ssn.length()<11 || ssn.length()>11) {
			x=false;
		}
		
		else if(ssn.matches(".*[a-z].*")) {
			x=false;
		}
		else {		 
			x=true;
		this.SSN = ssn;
		}
		return x;
	}

	public float getIncome() {
		return this.income;
	}

	public boolean setIncome(float income) {
		
		if(income<0) {
			x=false;
		}else {
		this.income = income;	
		}
		return x;
	}
		
	public String toString() {
		return getName()+" "+"xxx-xx-"+getSsn().substring(7)+" "+getBirthday().substring(0,4)+"/**/**";
	}
	
	public float deduction(Family z){
		
		return 0.0f;
	}
	

	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
