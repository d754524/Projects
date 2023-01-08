
public class Taxation {
	private static double d;
	private static double c;
	public static final float socialSecurityRate = 0.124f;
	public static final float socialSecurityIncomeLimit = 137700f;
	public static final float medicareRate = 0.029f;
	public static final float adultBaseExemption = 3000f;
	public static final float childBaseExemption = 2000f;
	public static final float medianIncomePerCapita = 31099f;
	
	
	private static void incomeBrackets(int bracket, int filingStatus) {
		/*   filingstatus #
			1= Single
			2= Married (jointly)
		    3= Married (Separately)
		*/
		
		if(bracket ==1) {

			switch(filingStatus) {
			case 1: 					
					System.out.println("$0 to $10,000");		  
		    break;
		    
			case 2:
					System.out.println("$0 to $20,000");
		    break;
		    
			case 3:System.out.println("$0 to $12,000");
		
			break;	
		}
			
		}
		
		if(bracket ==2) {
			
		switch(filingStatus) {
		  case 1:
			  System.out.println("$10,000.01 to $40,000");
		    break;
		    
		  case 2:
			  System.out.println("20,000.01 to $70,000");
		   break;
		    
		  case 3:
			  System.out.println("$12,000.01 to $44,000");
		  break;
		  		   
		}
		
		}
		if(bracket ==3) {
			
		switch(filingStatus) {
		  case 1:
			  System.out.println("$40,000.01 to $80,000");
		    break;
		  case 2:
			  System.out.println("$70,000.01 to $160,000");
		    break;
		  case 3:
			  System.out.println("$44,000.01 to 88,000");
			  break;
		}
		
		}
		if(bracket ==4) {
			switch(filingStatus) {
			  case 1:
				  System.out.println("$80,000.01 to $160,000");
			    break;
			  case 2:
				  System.out.println("$160,000.01 to $310,000");
			    break;
			  case 3:
				  System.out.println("$88,000.01 to $170,000");
				  break;
			}
			
			
		}
		if(bracket ==5) {
			switch(filingStatus) {
			  case 1:
				  System.out.println("$160,000.01 or more");
			    break;
			  case 2:
				  System.out.println("$310,000.01 or more");
			    break;
			  case 3:
				  System.out.println("$170,000.01 or more");
				  break;
			}
			
			
		}
	}
	
	private static void taxRates(int bracket, int filingStatus) {
		
		/*   filingstatus #
		1= Single
		2= Married (jointly)
	    3= Married (Separately)
	*/
		
	if(bracket ==1) {
		
		switch(filingStatus) {
		case 1: 
				
				System.out.println("10%");	
					
				break;
	    case 2:
				System.out.println("10%");
				
				break;
		case 3:System.out.println("10%");
	
					
				break;
	}
		
	}
	
	else if(bracket ==2) {
		
	switch(filingStatus) {
	  case 1:
		  System.out.println("12%");
		  
		  break;
	  case 2:
		  System.out.println("12%");
		  
		  break;
	  case 3:
		  System.out.println("12%");
		  
		  break;   
	}
	
	}
	else if(bracket ==3) {
		
	switch(filingStatus) {
	  case 1:
		  System.out.println("22%");
		 
		  break;
	  case 2:
		  System.out.println("23%");
		  
		  break;
	  case 3:
		  System.out.println("24%");
		  
		  break;
	}
	
	}
	else if(bracket ==4) {
		switch(filingStatus) {
		  case 1:
			  System.out.println("24%");
			
		    break;
		    
		  case 2:
			  System.out.println("25%");
			  
		    break;
		    
		  case 3:
			  System.out.println("26%");
			  
			  break;
		}
		
		
	}
	else if(bracket ==5) {
		switch(filingStatus) {
		  case 1:
			  System.out.println("32%");
			
		    break;
		  case 2:
			  System.out.println("33%");
			  
		    break;
		  case 3:
			  System.out.println("35%");
			
			  break;
		}
		
		
	}
		
	}

	
	public static int getNumTaxBrackets() {		
		return 5;
	}

	public static int maxIncomeTaxBracket(Family z) {
		
		int k = z.bracketNum();
		
		return k;
	}
	
	public static double bracketIncome(Family a, int b) {
		
			c=0;
			
			if(a.bracketNum()==b) {
				for(int i =1 ;i<b; i++) {
				d += a.brakIncome(i);						
				}
				
			  c= a.getTaxableIncome()-d;
			}
			else if(b<a.bracketNum()) {
				c = a.brakIncome(b);
			}
		
		return c;
	}
	
	public static double bracketTaxRate(int b, int f) {
		
		double [][] BTR ={      {0,0   ,0   ,0   },
								{0,0.1 ,0.1 ,0.1 },
						        {0,0.12,0.12,0.12},
						        {0,0.22,0.23,0.24},
						        {0,0.24,0.25,0.26},
						        {0,0.32,0.33,0.35}			
		};
		
		return BTR[b][f];
		
		
	}
}
