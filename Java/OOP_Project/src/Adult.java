
public class Adult extends Person {
private String employer;
 private float a;

 
	public Adult(String name,String birthday,String SSN,float income,String employer) {
		setName(name);
		setBirthday(birthday);
		setSSN(SSN);
		setIncome(income);
		this.employer=employer;
	}
	@Override
	public String toString(){
		
		return super.toString()+" "+getIncome();
	}
	
	public float adjustedIncome() {		
		
		if(getIncome()>=Taxation.socialSecurityIncomeLimit) {
			a = getIncome()-(Taxation.socialSecurityIncomeLimit*(Taxation.socialSecurityRate/2));
			a = a-(getIncome()*(Taxation.medicareRate/2)); 
		}
		else if(getIncome()>0 && getIncome()<Taxation.socialSecurityIncomeLimit){
		a = getIncome()-(getIncome()*(Taxation.socialSecurityRate/2));
		a = a-(getIncome()*(Taxation.medicareRate/2));
		}
		return a;
	}

	public float taxWithheld() {
		if(getIncome()>0&&getIncome()<=50000) {
			a = getIncome()*0.10f;
		}
		else if(getIncome()>50000&&getIncome()<=150000) {
			a = (50000*0.10f) + ((getIncome()-50000)*0.15f);
		}
		else if(getIncome()>150000) {
			a= (50000*0.10f)+ (100000*0.15f) + ((getIncome()-150000)*0.20f);
		}
		return a;
	}
	
	public float deduction(Family z) {
		
		if(z.getFilingStatus()==1 && adjustedIncome()<=100000) {
			if(Taxation.adultBaseExemption>adjustedIncome()) {
				a = adjustedIncome();
			}
			else if(adjustedIncome()>Taxation.adultBaseExemption) {
				a = Taxation.adultBaseExemption;
			}
		}
		else if(z.getFilingStatus()==1 && adjustedIncome()>100000) {
			a = Taxation.adultBaseExemption;
			a = a- (a*(((adjustedIncome()-100000)/1000)*0.005f));
			
			if(a<2100) {
				a = 2100;
			}
		}
		else if((z.getFilingStatus()==2 ||z.getFilingStatus()==3)) {
			a = Taxation.adultBaseExemption/2;
			
			if(adjustedIncome()<=100000) {
				a = Taxation.adultBaseExemption/2;
				if(adjustedIncome()<Taxation.adultBaseExemption/2) {
					a = adjustedIncome();
				}
			}
			else if(adjustedIncome()>100000) {
				a = Taxation.adultBaseExemption/2;
				a = a- (a*(((adjustedIncome()-100000)/1000)*0.005f));
				if(a<1050) {
					a=1050;
				}
			}
		}
		
		return a;
	}

	public String getEmployer() {
		return employer;
	}

	
	
	
	
}
