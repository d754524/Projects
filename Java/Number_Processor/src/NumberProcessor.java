
public class NumberProcessor {
	
	
	/** 
	    *
	    *  This method returns true if its integer argument is "Excessive", otherwise it returns false
	    *  A number is defined to be "Excessive" if the sum of its positive divisors is greater than 2 times the number itself.   
	    *  For example, 12 and 48 are "Excessive" whereas 4 and 16 are not.
	    *  
    */
	   public static boolean isExcessive(int input) {								//DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
		   	int x = input*2;
		   	int j=0;
		   	boolean k = true;
		   	for(int i = 1; i<=input;i++) {
		   		
		   		if (input%i==0) {
		   			j+=i;
		   		}
		   	}
		   	
		   	if(j > x) {
		   		k = true;
		   		System.out.println(k);
		   	}
		   	else {
		   		k=false;
		   		System.out.println(k);
		   	}
		   	
			return k;
		   
	    }
	 
	   	 
	  /**  
	    * 
	    * This method returns true if its argument is "Power", false otherwise. 
	    * A number is Power if it its value is the sum of  x^y + y^x, where x and y are integers greater than 1.
	    * 
	    * 
	    */
	         public static boolean isPower(long num) {									// DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
	        	 double x=1;
	        	 double y = 1;
	        	 boolean bol = false;
	        	 for(int i = 2; i<=num; i++) {
	        		 
	        		 for(int j = 2; j<=i ; j++) {
	        			 
	        			  x = Math.pow(i, j);
	        			  y = Math.pow(j, i);
	        			  
	        			  if(x+y==num) {
	        				  bol=true;
	        				  System.out.println(bol+"   "+"("+i+"^"+j +" + "+ j+"^"+i+")");
	        				  break;
	        			  }
	        			  
	        			else if( x+y != num ) {
	        				
	        					  bol = false;
	        					 if(i==num) {
	        						 System.out.println("False");
	        						 break;
	        						 
	        					 }
	        						 
	        				  }
	        		 }
	        		 
	        		 if(x+y==num)
	        			 break;
	        		 
	        	 }
	        	 
				return bol ;
	        	 
	        	 
	        	 
	       
	    }

	  
	 
	  /** 
		    * 
		    * This method accepts an  integer and returns true if the number is "Squad", false otherwise.
		    * An even digit integer is called "Squad" , if we can factor the number using two integers (a and b), whose product give the number n and with the following characteristics:

                    * Both a and b contains half the number of digits in the integer n. For example, if the number is 2568, a and b should be a two digit numbers.
                    * n contains the digits from both a and b. For example for n= 1530, a = 30 and b= 15. a * b = n and n contains all the digits in a and b (3, 0, 1 and 5).
                    * Both a and b cannot have trailing 0 at the same time, i.e., at most one of the numbers can have trailing 0. 
		    *
	  */  
	  
	   public static boolean isSquad(long num) {										//DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
  
		   boolean x = false;
		   long y = num;
		   int count=0;
		 long z = num;
		int indexs=0;
		
		  while(y>0) {

			 y = y/10;
			 count++;
		
		  }
		  if(count%2!=0) {
			  x=false;
			 System.out.println("false");
			 System.exit(0);
		  }

		  long [] arr1 = new long[count]; 									//ARRAY    arr1

		  while(count>0) {

			  arr1[indexs]=z%10;				// DIGITS OF INPUT
			  z= z/10;
			  
			  indexs++;
			  count--;
		  }
		
		  
		 if(arr1[0]==0 && arr1[1]==0 && arr1[2]==0) {
			 System.out.println("false(does not meet the 3rd condition)");
			 System.exit(0);
		 }
		  int filter=0;
		  int filterFalse=0;
		  
		   	for(int i = 2; i<=num; i++) {
		   		
		   		for(int k = 2; k<=num ; k++) {		
		   					   		
		   			if(i*k==num   ) {

		   				int r = i;
		   				int rr= k;
		   				int counter2=0;
		   				int counter3=0;
	/////////////////////////////////////////////////////////////////////////////////////////	   				
		   				while(r>0) {
		   					r=r/10;
		   					counter2++;
		   				}
		   				r=i;										// arr1 = num digits   iDigits = i digits         kDigits = k digits
		   				long [] iDigits = new long[counter2];			// iDigits  array 
//		   				
		   				int r2;
		   				
		   				for(int iD = 0; iD<iDigits.length;iD++) {				// assigning digits to iDigits
		   					
		   					r2=r%10;
		   					iDigits[iD]=r2;
		   					r=r/10;
		   				}		   				
	/////////////////////////////////////////////////////////////////////////////////////////
		   				while(rr>0) {
		   					rr=rr/10;
		   					counter3++;
		   				}
		   				long [] kDigits = new long[counter3];
		   				rr=k;
		   				int r3;
		   				
		   				for(int kD = 0; kD<kDigits.length;kD++) {			// assigning digits t o kDigits

		   					r3=rr%10;
		   					kDigits[kD]=r3;
		   					rr=rr/10;		   					
		   				}		
	/////////////////////////////////////////////////////////////////////////////////////////	   				
		   				int ccount=0;
		   				int ccount1=0;

		   				for(int ii= 0; ii<iDigits.length ; ii++) {
		   					for(int jj = 0 ; jj<arr1.length; jj++) {
		   						
		   						if(iDigits[ii] == arr1[jj]) {
		   							
		   							ccount++;
		   							
		   							break;
		   						}
		   							
		   					}
		   				
		   				}
		 /////////////////////////////////////////////////////////////////  				
		   				for(int ii= 0; ii<kDigits.length ; ii++) {
		   					for(int jj = 0 ; jj<arr1.length; jj++) {
		   						
		   						if(kDigits[ii] == arr1[jj]) {
		   							
		   							ccount1++;	
		   							break;
		   						}
		   							
		   					}
		   				
		   				}			
	 //////////////////////////////////////////////////////////////////////    		

		   		if((ccount==iDigits.length && ccount1==kDigits.length) && (ccount==ccount1)) {		
		   				long aa=0,bb=0,cc=0;
		   				
		   			for(int i2=0 ; i2<iDigits.length; i2++) {
		   					aa = aa + iDigits[i2];
		   					
		   			}
		   			for(int i3=0 ; i3<kDigits.length; i3++) {
		   				bb = bb + kDigits[i3];
	   			    }
		   			for(int i4=0 ; i4<arr1.length; i4++) {
		   				cc = cc + arr1[i4];
	   			    }
		   				if(aa+bb == cc && filter<1) {
		   				
		   				x = true;
		   				System.out.println(x+" ("+i+" * "+k+")" );
		   					filter++;
		   				break;
		   					
		   				}		
		   			}
		   		else if(filter<1 && i==(num/2) )   {														
		   			
		   			x= false;	
		   					System.out.println(x);
		   					filterFalse++;	
		   		}  		
		   		}		   			
		   	}
		   		
		   	}
		   		
			return x;
		   	

	    }
	         
	         
	 /** 
	  	    * 
	  	    * Considering the sequence 
	          *           1, 6, 15, 28, 45, 66, 91, 120, 153, 190, 231, ....

	          * The method returns the nth "MaSequence" number. If n is <= 0, it returns 0
	  	    *
	  */
	     
	  public static int maSequence(int num){									//DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
		  int increment=0;
		  int mult=1;
		  int seque;
		  if(num<=0) {
			  System.out.println("0");
			  System.exit(0);
		  }
		  for(int n  =1; n<num ; n++) {
			  
			  increment+=2;
			  
		  }
		  mult+= increment;
		  seque = num*mult;
		  System.out.println(seque);
		return increment;
	        
	          }
	         
	  /** 
	         * 
	         * This method accepts a number and returns true if the number is "OneSummative", false otherwise.
	         * A positive integer is called "OneSummative" , if the repetitive sum of the square of its digits is one:
	         *
	         *         * Consider 7: 7^2 = 49; 4^2 + 9^2 = 97; 9^2 + 7^2 = 130; 1^2 + 3^2 + 0^2 = 10; 1^2 + 0^2 = 1.
	         *
	         *         *  Consider 392: 3^2 + 9^2 + 2^2 = 94; 9^2 + 4^2 = 97; 9^2+ 7^2 = 130; 1^2 + 3^2 + 0^2 = 10; 1^2 + 0^2 = 1
	   */   
	   public static boolean isOneSummative(long num)						//DONE
	       	{
	        	// DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
		   
	int counter=2;
	
	int result=0;
		   for(int i = 1; i < counter; i++) {
			 
			   int digits=0;
			   int arr1Counter=0;	
			   long num2 = num;
			   
			   while(num>0) {
					   		
				   num/=10;
				 digits++;
					   	}	
			   																	
			   long[] arr1 = new long[digits];

			   while(num2>0) {
				   
				   arr1[arr1Counter] = num2%10; 
				   num2/=10;
				   arr1Counter++;	 
			   }
			   				   	
			   	result= 0;
			   for(int j = 0;j<arr1.length; j++) {

				   		result += Math.pow(arr1[j],2);
			   }

			   if(result!=1 ) {
				   counter++;
				   num=result;
			   }
			   else if(result==1) {
				   System.out.println("true");
				   System.exit(0);
			   }
			  
			   if(counter>80) {
				   System.out.println("false");
				   System.exit(0);
			   }
			   
		   }
			return true;
	 	    }
	         
	     
      /** 
		    * 
		    * This method returns true if the array is EvenDual false otherwise. 
		    * An array is called EvenDual if it has the following properties:
	        *        - The value of the first element equals the sum of the next two elements, which is equals to the next three elements, equals to the sum of the next four elements, etc.
	        *        - It has a size of x*(x+1)/2 for some positive integer x .
	        *
	        *  For example {6, 2, 4, 2, 2, 2, 1, 5, 0, 0} isEvenDual, whereas {2, 1, 2, 3, 5, 6} is not
       */
	     
	   public static  boolean isEvenDual(int array[]) {								//DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
		   boolean x = true;
		   
		   if(array.length==0) {
			   x=false;
			   System.out.println(x);
		   }
		  
		   int nG=0;
		   int counter =0;

		   for(int i =0; i <=array.length ; i++) {
			   
			   if(array.length == (i*(i+1))/2) {
				
				   counter++;
				   nG=i;
				   break;
			   }			   
		   }
		   
		   if(counter ==0) {			   
			   x=false;
			   System.out.println(x+" (does not meet the second criterion)");
			   System.exit(0);
		   }
		   
		   		int counter2 = 2;
		   		int sum = 0;
		   		int k =1;
		   		int counter3 = 3;
		   		int counter4 = 0;
		   		
		   		for(int i=1 ;i <= nG-1 ; i++) {
		   			
		   			for(sum=0; k<=counter2 ; k++) {
		   				
		   				sum = sum + array[k];
		   						   				
		   				if(array[0]==sum) {

		   					counter2= counter2 +counter3;;
		   					k++;	
		   					counter3++;
		   					counter4++;
		   					break;
		   				}
		   				
		   			}
		   			
		   			
		   			if(nG-1 == counter4) {
		   				x = true;
		   				System.out.println(x);
		   			}
		   			else if ( (nG-1 != counter4) && (i==nG-1) ) {		   				
		   				x=false;
		   				System.out.println(x);
		   			}
		   			
		   		}


		return x;
 
	    }

	 
	    
	 
	  /** 
	      * 
	      * This method  accepts a positive n and returns corresponding "IncrementalArray". 
	      * An array is called "IncrementalArray" if for the given positive integer n, it produces an array with incremental pattern.<p>
	      *           *if n = 1, it will produce {1}
	      *           * if n= 2. it produces {1, 1, 2}
	      *           * if n= 4. it produces {1, 1, 2, 1, 2, 3, 1, 2, 3, 4}
	      *           * if n= 6. it produces {1, 1, 2, 1, 2, 3, 1, 2, 3, 4,1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 6}
	      *          
	  */
	     
	  public static int[] incrementalArray(int n) {									//DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
		  if(n<1) {
			  System.out.println("Not a valid entry");
			  System.exit(0);
		  }
		  int size;
		  size = (n*(n+1))/2;

	       int[] arr1 = new int[size];
	       int counter = 1;
	       int arrCounter = 0;
	        for(int i = 1 ; i<=n ; i++) {
	        	
	        	for(int k = 1; k<=counter;k++) {
	        		
	        		arr1[arrCounter]= k;	        		
	        		arrCounter++;			
	        	}
	        	counter++;
	        }
	        
	        	for(int i = 0; i<arr1.length; i++) {
	        		
	        		if(arr1.length==1) {
	        			System.out.println("{"+arr1[0]+"}");
	        			break;
	        		}
	        		if(i==0) {
	        			System.out.print("{"+arr1[0]+", ");
	        		}
	        		else if (i==arr1.length-1) {
	        			System.out.print(arr1[arr1.length-1]+"}");
	        		}
	        		else
	        		System.out.print(arr1[i]+", ");
	        	}
		  return arr1;
	        
	    }

	   
	 
	  
	  /** 
	    * 
	    * This method returns true if the array is Divisible, false otherwise.
	    * An array is called "Divisible" if it can be divided into two non-empty sub arrays, 
	    * where the sum of elements of the first sub array equals the sum of elements of the second sub array. 
	    *
	    * For example, {6, 2, 4, 2, 2, 2, 1, 5, 0, 0}	is Divisible as it can give the sub arrays {6,2,4} and {2,2,2,1,5,0,0}
	    *               {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, -2, -1}	is also Divisible as it gives sub arrays {0,0,0,0,0,0,0,0,0,0} and {1,1,1,-2,-1})
	    *
	  */
	   public static boolean isDivisible(int array []) {							//DONE
	
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
		   boolean x = true;
		   int sum=0;
		   
		   for(int i =0 ; i< array.length ; i++) {
			   
			   sum+= array[i];
		   }
		   
		   
		   if(sum%2!=0) {			   
			   x = false;
			   System.out.print(x);
//			   System.out.println(x);
			   System.exit(0);
		   }
		   else {
			   System.out.print(x+" (");
			   x=true;
		   }
		   int filter = sum/2;
		   int sum2=0;
		   int k = 0;
		   for(int i = 1 ; i<=2 ; i++) {
			   
			  
			 System.out.print("{");  
			   
			   for(; k<array.length ; k++) {
				   			   
				   sum2+= array[k];
				
			if(i==1) {	   
				 
				   if(sum2==filter) {
					   System.out.print(array[k]);
					   break;
				   }
				   
				   System.out.print(array[k]+", ");
			}	   
			if(i==2) {
				
				if(k==array.length-1) {
					System.out.print(array[k]);
				}
				else
				System.out.print(array[k]+", ");

			}

				   	}
			   sum2=0;
		   		k++;	   		
			   System.out.print("}");
			   
			   if(i==1) {
			   	System.out.print(" and ");	
			   }
			   		
			   }
			   
			   System.out.print(")");
		   
			return x; 
	    }
	
	   
	/**
	 * An array is called ConsecutiveDual if it contains consecutive elements (greater than 1 element) of same value.
	 *  For example, the array {1, 2, 3, 3, 4, 5} and { 4, 4 , 4 , 4, 4 } are "ConsecutiveDual" arrays 
	 *  where as {10, 9, 8, 7, 8, 9} and {0,1,0,1,0,1} are not.
	 */
		  

     public static boolean isConsecutiveDual(int[] array) {							//DONE
	     // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
    	 
    	 
    	
    	 boolean x = false;
    	 for(int i =1; i<array.length; i++) {
    		 
    		 if(array[array.length-2]==array[array.length-1]) {
    			 x=true;
    			 System.out.println(x);
    			 System.exit(0);   			
    		 }
	 
    	 else if(array[i-1]==array[i] ) {
    			 x= true;
    			 System.out.println(x);
//    			 System.exit(0);   		
    			 break;
    		 }
    	 
 
    	 }

		return x;
          }

    /** 
           * 
           * This method returns true if the array is "PairArray", false otherwise.
           * An array is called "PairArray" if exactly one pair of its elements sum to 10. 
           * For example, {4,11,14, 6} is PairArray as only 4 and 6 sum to 10
           * The array {10,3,0,15,7} is not PairArray as more than one pair (10,0) and (3,7) sum to 10. 
           * {4,1,11} is not also PairArray as no pair sums to 10
            *
        *
      */
      public static boolean isPairArray(int array[]) {							//DONE
	
        // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
    	  boolean x = false;
    	  int counter =0;
    	  int k = 1;
    	  for(int i = 0; i<array.length-2; i++) {
    		 
    		  for(; k<array.length ; k++) {

    			  if(array[i]+array[k]==10) {
    				  counter++;
    				  
    			  }
    		  }	  
    		  
    		System.out.println(k);  
    		  k++;
    		  
    	  }
    	  	if(counter>1 || counter==0) {
    	  		x=false;
    	  	}
    	  	else
    	  		x=true;

    		return x;
          }
//##############################################Honors Section##############################
 /*	
  * 
  * 
  * /* 
   * 
   * This part only applies to Honors section students 
   *  if you are Honors section student, you have to uncomment this part 
   *  
   *  
   */
  
/**
 * 
 *  Given an array of integers, find the consecutive elements with the largest sum. 
 *  For example, if the array is {-2, 11, -4, , 13, -5, 2} the maximum sum is 20 which is the sum of the subarray that contains 11, -4, 13.
 *
 *//*
     public static int maxSum(int array[]) { 
		 // DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
        throw new RuntimeException("not implemented!");  
     }
	
	
	
	
	/**
	 * 
	 *  Given an array of integers, find the sub array with the largest sum. 
	 *  For example, if the array is {-2, 11, -4, , 13, -5, 2} the maximum sum is 20 which is the sum of the subarray {11, -4, 13}.
	 *
	 */
/*	
	public static int[] maxSubArray(int array[]) {
		// DELETE THE LINE BELOW ONCE YOU IMPLEMENT THE CALL!
        throw new RuntimeException("not implemented!");  
    }
*/
}