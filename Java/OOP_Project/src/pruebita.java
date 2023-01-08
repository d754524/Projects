
public class pruebita {

	
	public static void main(String[] args) {
		
		 Adult a1 = new Adult("name1", "1732/02/22", "987-65-4321", 0.00f, "GMU");
		    Adult a2 = new Adult("name2", "1732/02/22", "987-65-4321", 1234.56f, "GMU");
		    Family f1 = new Family((byte)2, (byte)1);
		    f1.addMember(a1);
		    f1.addMember(a2);
	   
//		Adult a1 = new Adult("name1", "1732/02/22", "987-65-4321", 0.00f, "GMU");
//	    Adult a2 = new Adult("name2", "1732/02/22", "987-65-4321", 1234.56f, "GMU");
//	    Adult a3 = new Adult("name3", "1732/02/22", "987-65-4321", 13456.78f, "GMU");
//	    Adult a4 = new Adult("name4", "1732/02/22", "987-65-4321", 23979.54f, "GMU");
//	    Adult a5 = new Adult("name5", "1732/02/22", "987-65-4321", 67890.12f, "GMU");
//	    Adult a6 = new Adult("name6", "1732/02/22", "987-65-4321", 123456.78f, "GMU");
//	    Adult a7 = new Adult("name7", "1976/05/01", "789-56-1234", 145000.98f, "Mason");
//	    Adult a8 = new Adult("name8", "1732/02/22", "987-65-4321", 267890.12f, "GMU");
//	    Adult a9 = new Adult("name9", "1732/02/22", "987-65-4321", 312346.78f, "GMU");
//	    Child c1 = new Child("kid1", "2000/01/01", "999-65-1111", 0.0f, "Fairfax High School", 3300.0f);
//	    Child c2 = new Child("kid2", "2000/01/01", "999-65-1111", 100.0f, "Fairfax High School", 0.0f);
//	    Child c3 = new Child("kid3", "2000/01/01", "999-65-1111", 300.0f, "Fairfax High School", 0.0f);
//	    Child c4 = new Child("kid4", "2000/01/01", "999-65-1111", 900.0f, "Fairfax High School", 900.0f);
//	    Child c5 = new Child("kid5", "2000/01/01", "999-65-1111", 1600.0f, "Fairfax High School", 1234.0f);
//	    Child c6 = new Child("kid6", "2000/01/01", "999-65-1111", 7300.0f, "Fairfax High School", 6650.0f);
//	    Child c7 = new Child("kid7", "2000/01/01", "999-65-1111", 12000.0f, "Fairfax High School", 11999.0f);
//	    Child c8 = new Child("kid8", "2000/01/01", "999-65-1111", 27000.0f, "Fairfax High School", 100.0f);
//	    Child c9 = new Child("kid9", "2000/01/01", "999-65-1111", 41560.0f, "Fairfax High School", 8765.0f);
		
//		
//		Family f1 = new Family((byte)2, (byte)1);
//	    f1.addMember(a1);
//	    f1.addMember(a2);
//	    f1.calculateTax();
//	    Family f2 = new Family((byte)4, (byte)2);
//	    f2.addMember(a3);
//	    f2.addMember(a4);
//	    f2.addMember(c1);
//	    f2.addMember(c2);
//	    f2.calculateTax();
//	    Family f3 = new Family((byte)3, (byte)2);
//	    f3.addMember(a5);
//	    f3.addMember(a6);
//	    f3.addMember(c3);
//	    f3.calculateTax();
//	    Family f4 = new Family((byte)6, (byte)2);
//	    f4.addMember(a7);
//	    f4.addMember(a8);
//	    f4.addMember(c4);
//	    f4.addMember(c5);
//	    f4.addMember(c6);
//	    f4.addMember(c7);
//	    f4.calculateTax();
//	    Family f5 = new Family((byte)3, (byte)3);
//	    f5.addMember(a9);
//	    f5.addMember(c8);
//	    f5.addMember(c9);
//	    f5.calculateTax();
	    
//	    System.out.println(f2.getNumAdults()+" "+ f2.getNumChildren());
//	    System.out.println(f2.getTaxableIncome());
//	   System.out.println(Taxation.adultBaseExemption*2);
//	    System.out.println(a.adjustedIncome()-(6000*(1-((((int)a.adjustedIncome()-100000)/1000)*0.005f))));
//	    System.out.println(f.adults[0].adjustedIncome()+", "+f.adults[1].adjustedIncome());
//	    System.out.println(f.adultsDeducs());
//	    System.out.println((f.adults[0].adjustedIncome()+f.adults[1].adjustedIncome())-f.adultsDeducs());
//	    System.out.println(f.getTaxableIncome()*Taxation.bracketTaxRate(0, 2));
//	   
//	    System.out.println(Taxation.bracketTaxRate(0, 2));
//		 
	  
//	   System.out.println(f.taxCredit());
//	   System.out.println(f.getTaxableIncome());
//	    System.out.println(f.adultsDeducs());
//	    System.out.println(f.bracketNum());
	  
	   System.out.println();
	   System.out.println(f1.calculateTax());
//	    System.out.println(f.getTaxableIncome());
//	    System.out.println();
//	    System.out.println(f.taxCredit());
//	   
	}

}
