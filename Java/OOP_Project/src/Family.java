

public class Family {
	
private float c = 0;
private double kk;
private int numMembers;
private double h;
private double ii;
private double jj;
private float a=0;
private float b=0;	// 1=single, 2=jointly, 3=separately
private float f=0;
private float g=0;
private float limit=0;
private int filingStatus;
private int e;
public static int counter=0;
public static int counterA=0;
public static int counterC=0;
public static int counterAD=0;
Person[] Members;
public Adult [] adults;
public Child [] children;
public Family(int members, int filingStatus) {
	counter=0;
	counterA=0;
	counterC=0;
	 this.numMembers=members;
	 Members= new Person[this.numMembers];
	 if(filingStatus==1) {
	 children= new Child[members-1];
	 adults= new Adult[1];
	 }
	 else if(filingStatus==2) {
		 children= new Child[members-2];
		 adults= new Adult[2]; 
	 }
	 else if(filingStatus==3) {
		 children= new Child[members-1];
		 adults= new Adult[1];
	 }
	this.filingStatus = filingStatus;
}
	
//public void addMember(Adult x) {		
	
	
//		counterAD++;
//		System.out.println(counterAD);
//		if(counterAD>1 && getFilingStatus()==1) {
//			Adult[] ExcAdult = new Adult[counterAD];
//			
//			for(int i =0; i <adults.length;i++) {
//				ExcAdult[i]= adults[i];
//			}
//			ExcAdult[counterAD-1]=x;
//			adults = new Adult[counterAD];
//			for(int i=0; i<adults.length; i++) {
//				adults[i]=ExcAdult[i];
//			}
//			counterA++;
//		
//	}
//	 if(getFilingStatus()==1 && counterAD<2) {
//	adults[counterA]=x;
//	counterA++;
//	}
	
	
	 
	/*
	 * adults[counterA]=x; counterA++;
	 * 
	 * Members[counter]=x; counter++;
	 */
	
	
//}
//public void addMember(Child x) {		
	
//	Members[counter]=x;	
//	counter++;
//	children[counterC]=x;
//	counterC++;
	
	
//}

public void addMember(Person x) {
	
	if(x instanceof Adult) {
		adults[counterA++]=(Adult) x;
		Members[counter++]=x;
	}
	else if(x instanceof Child) {
		children[counterC++]=(Child) x;
		Members[counter++]=x;
	}
}

public float adultsDeducs() {		
	/* 
		The method adultsDeducs returns the deduction for each adult in the family.
	*/
	a=0;
	
	float b= 0;
	for(int i=0 ;i<adults.length;i++) { 
		b= adults[i].adjustedIncome();
	
	
	if(getFilingStatus()==1 && b<=100000) {
		if(i>0) {
			c=0;
		}
		else
		c=Taxation.adultBaseExemption*2;
		
		
	}
	else if(getFilingStatus()==1 && b>100000) {
		
		c = Taxation.adultBaseExemption*2;
		
		c = (c*(1-((((int)b-100000)/1000)*0.005f)));
		if(c<4200) {
			c = 4200;
		}
		
	}
	else if(getFilingStatus()==2 && b<100000) {
		c =Taxation.adultBaseExemption;
		if(b<Taxation.adultBaseExemption) {
			c=b;
		}
	}
	else if(getFilingStatus()==2 && b>100000) {
		c=Taxation.adultBaseExemption;
		
		c = (c*(1-((((int)b-100000)/1000)*0.005f)));
		if(c<2100) {
			c = 2100;
		}
		
	}
	else if(getFilingStatus()==3 && b<100000) {
		c = Taxation.adultBaseExemption;
	}
	else if(getFilingStatus()==3 && b>100000) {
		c = Taxation.adultBaseExemption;
		
		c = (c*(1-((((int)b-100000)/1000)*0.005f)));
		
		if(c<2100) {
			c = 2100;
		}
	}
	  	a = a+c;
	}	
	
	return a;
	
}

public int getNumAdults() {
		System.out.println("ADULTS#: "+(counterA));
	return counterA;
}

public int getNumChildren() {
	
	return counterC;
}
	
public int getFilingStatus() {
	
	return this.filingStatus;
}

public float getTaxableIncome() {	
	b=0;

	for(int i =0;i< adults.length;i++) {
		b+= adults[i].adjustedIncome();		
	}
	
	b = b-adultsDeducs();
	
	return b;
	
}

public int bracketNum() {
	
	if(getFilingStatus()==1 && getTaxableIncome()>0 && getTaxableIncome()<=10000) {
		e = 1;	
	}
	else if(getFilingStatus()==1 && getTaxableIncome()>10000 && getTaxableIncome()<=40000) {
		e = 2;	
	}
	else if(getFilingStatus()==1 && getTaxableIncome()>40000 && getTaxableIncome()<=80000) {
		e = 3;	
	}
	else if(getFilingStatus()==1 && getTaxableIncome()>80000 && getTaxableIncome()<=160000) {
		e = 4;	
	}
	else if(getFilingStatus()==1 && getTaxableIncome()>160000) {
		e = 5;	
	}
	
	else if(getFilingStatus()==2 && getTaxableIncome()>0 && getTaxableIncome()<=20000) {
		e = 1;	
	}
	else if(getFilingStatus()==2 && getTaxableIncome()>20000 && getTaxableIncome()<=70000) {
		e = 2;	
	}
	else if(getFilingStatus()==2 && getTaxableIncome()>70000 && getTaxableIncome()<=160000) {
		e = 3;	
	}
	else if(getFilingStatus()==2 && getTaxableIncome()>160000 && getTaxableIncome()<=310000) {
		e = 4;	
	}
	else if(getFilingStatus()==2 && getTaxableIncome()>310000 ) {
		e = 5;	
	}
	else if(getFilingStatus()==3 && getTaxableIncome()>0 && getTaxableIncome()<=12000) {
		e = 1;	
	}
	else if(getFilingStatus()==3 && getTaxableIncome()>12000 && getTaxableIncome()<=44000) {
		e = 2;	
	}
	else if(getFilingStatus()==3 && getTaxableIncome()>44000 && getTaxableIncome()<=88000) {
		e = 3;	
	}
	else if(getFilingStatus()==3 && getTaxableIncome()>88000 && getTaxableIncome()<=170000) {
		e = 4;	
	}
	else if(getFilingStatus()==3 && getTaxableIncome()>170000) {
		e = 5;	
	}
	return  e;
}


public float getLimit() {       //returns the pre-tax for taxCredit
	f = getTaxableIncome();

	if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.1) {
		limit = f*0.1f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.12) {
		limit = f*0.12f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.22) {
		limit = f*0.22f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.23) {
		limit = f*0.23f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.24) {
		limit = f*0.24f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.25) {
		limit = f*0.25f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.26) {
		limit = f*0.26f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.32) {
		limit = f*0.32f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.33) {
		limit = f*0.33f;		
	}
	else if(Taxation.bracketTaxRate(bracketNum(),getFilingStatus())==0.35) {
		limit = f*0.35f;		
	}
	return limit;
	
}


public float taxCredit() {			
	g = getLimit();	
	a = getTaxableIncome();

	if(g>2000f) {
		limit =2000;	
	}
	else if(g<2000) {
		limit = g;	
	}
	
if(a < Taxation.medianIncomePerCapita/2) {
	   b = ((int)a/1000)*30;
	   
	   if(b>limit) {
		   c= limit;
	   }
	   else if(b<limit) {
		   c=b;
		   
	   }
	}
	
	b=0;
	
if(a<Taxation.medianIncomePerCapita/2) {
	
	for(int i =0;i<children.length;i++) {		//FIX
		
			if(children[i].getTuition()<1000) {
					b = b + children[i].getTuition();
			}
			else if(1000<children[i].getTuition()){
				b = b+ 1000;
			}
		}
	

}	
	
	if(a<Taxation.medianIncomePerCapita/2) {
		
		if(c+b>limit) {

			c=limit;			
		}
		else if(c+b<limit) {

			c=c+b;
		}
		
	}
	else if(a>Taxation.medianIncomePerCapita/2){
		c=0;	
		
	}

	return c;
	
}


public double brakIncome(int b) {
	
	double[][] BTR = {   {0,0     ,0     ,0   },
						 {0,10000 ,20000 ,1200 },
					  	 {0,30000,50000,32000},
					  	 {0,40000,90000,44000},
					  	 {0,80000,150000,82000},
					  	 {0,160000,310000,170000}	
	        
	};
	return BTR[b][getFilingStatus()];
}

public double calculateTax() {
	 ii=0;
	
	for (int i = 1; i<=bracketNum();i++) {
		
		if(bracketNum()==1&&getTaxableIncome()<brakIncome(i)) {
			h = Taxation.bracketTaxRate(i, getFilingStatus());

			ii += getTaxableIncome()*h;
		}
		else if(i==bracketNum()) {
			h = Taxation.bracketTaxRate(i, getFilingStatus());
			ii+= ((getTaxableIncome()-kk)*h);
		}
		else {
	h = Taxation.bracketTaxRate(i, getFilingStatus());
			kk+=brakIncome(i);
		ii += brakIncome(i)*h;
		}
		
	}
	
	jj = ii-taxCredit();
	ii=0;
	for(int i = 0; i<adults.length;i++) {
		ii+= adults[i].taxWithheld();
	}

	jj=jj-ii;
	
	return jj;
}


}
