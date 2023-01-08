
public class TaxYear {

	private boolean a;
	private static int NumFamAccepted = 0;
	private static int NumPeopleAccepted = 0;
	private int max;
	public TaxYear(int max) {
		this.max=max;
	}
	
	public boolean taxFiling(Family x) {
		if(x.getFilingStatus()==1 && x.getNumAdults()>=2) {
			a=false;
		}
		else if(x.getFilingStatus()==1 && x.getNumChildren()>0) {
			a=false;
		}
		
		else if(x.getFilingStatus()==2 && x.getNumAdults()==2) {
			a=true;
			if(x.getNumChildren()==0 || x.getNumChildren()>0) {
				a=true;
				NumFamAccepted++;
				if(true) {
					NumPeopleAccepted=NumPeopleAccepted+x.getNumAdults()+x.getNumChildren();
				}
			}
		}
		
		else if(x.getFilingStatus()==3 && x.getNumAdults()==1 && x.getNumChildren()>0) {
			a=true;
			NumFamAccepted++;
			if(true) {
				NumPeopleAccepted=NumPeopleAccepted+x.getNumAdults()+x.getNumChildren();
			}
		}
		if(x.getNumAdults()>=2 && x.getNumChildren()==0) {
			a=false;
		}
		else if(x.getNumChildren()>0 && x.getNumAdults()==0 ) {
			a=false;
		}
		else if(x.getNumChildren()>0 && x.getNumAdults()>0 && x.getNumAdults()<=2){
			a=true;
			NumFamAccepted++;
			if(true) {
				NumPeopleAccepted=NumPeopleAccepted+x.getNumAdults()+x.getNumChildren();
			}
		}
		
		return a;
	}
	
	public float taxWithheld() {
		
		return 0;
	}
	
	public float taxOwed() {
		
		return 0;
	}
	
	public float taxDue() {
		
		return 0;
	}
	
	public float taxCredits() {
		
		return 0;
	}
	
	public int numberOfReturnsFiled() {
		
		return NumFamAccepted;
	}
	
	public int numberOfPersonsFiled() {
		
		return NumPeopleAccepted;
	}
	
	public Family getTaxReturn(int index) {
		
		
		return null;
	}
	
}
