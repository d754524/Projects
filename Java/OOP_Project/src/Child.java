
public class Child extends Person {
	
	private float a;
	private String School;
	private float tuition;
	 
	 
	public Child(String name, String birthday, String ssn, float income, String School, float tuition) {
		setName(name);
		setBirthday(birthday);
		setSSN(ssn);
		setIncome(income);
		this.School=School;
		this.tuition = tuition;	
	}
	@Override	
	public String toString() {
		
//		String x = getName()+" "+"***-***-"+getSsn().substring(9)+" "+getBirthday().substring(0,4)+"/**/**"+this.School;
		
		return super.toString()+" "+this.School;
	}

	public float getTuition() {	
	return this.tuition;
	}
	
	public float adjustedIncome() {
		
		a = this.getIncome();
		return a;
	}

	public float deduction(Family z) {
	
		if(z.getNumChildren()<=2) {
			a = getIncome();
			if(a>Taxation.childBaseExemption) {
				a=Taxation.childBaseExemption;
			}
		}
		else if(z.getNumChildren()>2&& z.getNumChildren()>=12) {
			a = Taxation.childBaseExemption-(Taxation.childBaseExemption*0.50f);
		}
		else if(z.getNumChildren()>2) {
			a = Taxation.childBaseExemption-(Taxation.childBaseExemption*(0.05f*(z.getNumChildren()-2)));
		}
		
	return a;
	}
	

	
	
	
	
	}
