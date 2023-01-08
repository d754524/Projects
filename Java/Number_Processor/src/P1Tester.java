/** Example of using unit tests for this assignment.  To run them on the command line, make
 * sure that the junit-cs211.jar is in the same directory.  
 * 
 * On Mac/Linux:
 *  javac -cp .:junit-cs211.jar *.java         # compile everything
 *  java -cp .:junit-cs211.jar P1Tester        # run tests
 * 
 * On windows replace colons with semicolons: (: with ;)
 *  demo$ javac -cp .;junit-cs211.jar *.java   # compile everything
 *  demo$ java -cp .;junit-cs211.jar P1Tester  # run tests
 */
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;
  
public class P1Tester {
  public static void main(String args[]){
    org.junit.runner.JUnitCore.main("P1Tester");
  }
  int dataExcessive[] = {10, 12,48,77,1050, 4565,-98};
  boolean resExcessive[] = {false, true, true, false, true, false, true};

  void checkIsExcessive(int a) {
	  int n = dataExcessive[a];
	  boolean result = resExcessive[a];
	  String errMsg = String.format("isExcessive(%d) incorrect", n);
	  assertEquals(errMsg, result, NumberProcessor.isExcessive(n));
  }
     
  @Test(timeout=1000) public void isExcessive_00() { checkIsExcessive(0); }
  @Test(timeout=1000) public void isExcessive_01() { checkIsExcessive(1); }
  @Test(timeout=1000) public void isExcessive_02() {checkIsExcessive(2); }
  @Test(timeout=1000) public void isExcessive_03() { checkIsExcessive(3); }
  @Test(timeout=1000) public void isExcessive_04() { checkIsExcessive(4); }
  @Test(timeout=1000) public void isExcessive_05() { checkIsExcessive(5); }
  @Test(timeout=1000) public void isExcessive_06() { checkIsExcessive(6); }
  
  
  
  long dataPower[] = {9, 57, -6, 4509, 2505, 450};;
  boolean resPower[] = {false, true, false, false, false, false};

  void checkIsPower(int a) {
	  long n = dataPower[a];
	  boolean result = resPower[a];
	  String errMsg = String.format("isPower(%d) incorrect", n);
	  assertEquals(errMsg, result, NumberProcessor.isPower(n));
  }
     
  @Test(timeout=1000) public void isPower_00() { checkIsPower(0); }
  @Test(timeout=1000) public void isPower_01() { checkIsPower(1); }
  @Test(timeout=1000) public void isPower_02() {checkIsPower(2); }
  @Test(timeout=10000) public void isPower_03() { checkIsPower(3); }
  @Test(timeout=10000) public void isPower_04() { checkIsPower(4); }
  @Test(timeout=1000) public void isPower_05() { checkIsPower(5); }

  long numSquad[]= {2520, 1347, 1260, 2187, 6880, 11256,105210,  457809, 116725};
  boolean resSquad[] = {false , false , true , true , true,  false,  true , false,  true };
  
  void checkIsSquad(int a) {
	  boolean result = resSquad[a];
	  String errMsg = String.format("isSquad(%d) incorrect", numSquad[a]);
	  assertEquals(errMsg, result, NumberProcessor.isSquad(numSquad[a]));
  }

  @Test(timeout=1000) public void isSquad_00() { checkIsSquad(0); }
  @Test(timeout=1000) public void isSquad_01() { checkIsSquad(1); }
  @Test(timeout=1000) public void isSquad_02() { checkIsSquad(2); }
  @Test(timeout=1000) public void isSquade_03() { checkIsSquad(3); }
  @Test(timeout=1000) public void isSquad_05() { checkIsSquad(5); }
  @Test(timeout=1000) public void isSquad_06() { checkIsSquad(6); }
  @Test(timeout=1000) public void isSquad_07() { checkIsSquad(7); }
  @Test(timeout=1000) public void isSquad_08() { checkIsSquad(8); }

  
  
  
  int numSequence[]= {2,17,25,0,-13, 100};
  int resSequence[]= {6, 561, 1225, 0, 0, 19900};

  void checkMaSequence(int a) {
	  
	  String errMsg = String.format("incorrect maSequence(%d)", numSequence[a]);
	  assertEquals(errMsg, resSequence[a], NumberProcessor.maSequence(numSequence[a]));
  }

  @Test(timeout=1000) public void MaSequence_00() { checkMaSequence(0); }
  @Test(timeout=1000) public void MaSequence_01() { checkMaSequence(1); }
  @Test(timeout=1000) public void MaSequence_02() { checkMaSequence(2); }
  @Test(timeout=1000) public void MaSequence_03() { checkMaSequence(3); }
  @Test(timeout=1000) public void MaSequence_04() { checkMaSequence(4); }
  @Test(timeout=1000) public void MaSequence_05() { checkMaSequence(5); }
  

   

  int [] numOneSummative= {20,7,13,11, 285, 19,400};
  boolean[] resOneSummative = {false,  true,  true , false,  false,  true  ,false };

  void checkIsOneSummative(int a) {
	  String errMsg = String.format("isOneSummative(%d) incorrect", numOneSummative[a]);
	  assertEquals(errMsg, resOneSummative[a], NumberProcessor.isOneSummative(numOneSummative[a]));
  }
 
  @Test(timeout=1000) public void IsOneSummative_00() { checkIsOneSummative(0); }
  @Test(timeout=1000) public void IsOneSummative_01() { checkIsOneSummative(1); }
  @Test(timeout=1000) public void IsOneSummative_02() { checkIsOneSummative(2); }
  @Test(timeout=1000) public void IsOneSummative_03() { checkIsOneSummative(3); }
  @Test(timeout=1000) public void IsOneSummative_04() { checkIsOneSummative(4); }
  @Test(timeout=1000) public void IsOneSummative_05() { checkIsOneSummative(5); }
  @Test(timeout=1000) public void IsOneSummative_06() { checkIsOneSummative(6); }
  

  int numEvenDual[][]= {{4,3,1,1,0,3},{9,9,9},{5,4,1,5},{6,2,4,2,2,2,6},{7,2,5,1,3,3,7,0,0,0},{1,1,0,0,1,1,0,0,0,4},{3},{-6,-2,-4,-2,-2,-2}};
  boolean resEvenDual[] = {true, false, false, false, true, false, true, true};


  void checkIsEvenDual(int a) {
	  int[] list = numEvenDual[a];
	  boolean result = resEvenDual[a];
	  String errMsg = String.format("isEvenDual incorrect for array %s", Arrays.toString(list));
	  assertEquals(errMsg, result, NumberProcessor.isEvenDual(list));
  }
	  
  @Test(timeout=1000) public void isEvenDual_00() { checkIsEvenDual(0); }
  @Test(timeout=1000) public void isEvenDual_01() { checkIsEvenDual(1); }
  @Test(timeout=1000) public void isEvenDual_02() { checkIsEvenDual(2); }
  @Test(timeout=1000) public void isEvenDual_03() { checkIsEvenDual(3); }
  @Test(timeout=1000) public void isEvenDual_04() { checkIsEvenDual(4); }
  @Test(timeout=1000) public void isEvenDual_05() { checkIsEvenDual(5); }
  @Test(timeout=1000) public void isEvenDual_06() { checkIsEvenDual(6); }
  @Test(timeout=1000) public void isEvenDual_07() { checkIsEvenDual(7); }
  


  int[] numIncrementalArray = {2,7,1,0, 5};
  int[][]resIncrementalArray =  { {1,1,2},{1,1,2,1,2,3,1,2,3,4,1,2,3,4,5,1,2,3,4,5,6,1,2,3,4,5,6,7},{1},null,{1,1,2,1,2,3,1,2,3,4,1,2,3,4,5}};

  void checkIncrementalArray(int a) {
	  String errMsg = String.format("incrementalArray ( %d) incorrect ", numIncrementalArray[a]);
	  assertArrayEquals(errMsg, resIncrementalArray[a], NumberProcessor.incrementalArray(numIncrementalArray[a]));
  }
	  

  @Test(timeout=1000) public void incrementalArray_00() { checkIncrementalArray(0); }
  @Test(timeout=1000) public void incrementalArray_01() { checkIncrementalArray(1); }
  @Test(timeout=1000) public void incrementalArray_02() { checkIncrementalArray(2); }
  @Test(timeout=1000) public void incrementalArray_03() { checkIncrementalArray(3); }
  @Test(timeout=1000) public void incrementalArray_04() { checkIncrementalArray(4); }
  
  int numDivisible[][]= {{6, 2, 4, 2, 2, 2, 1, 5, 0, 0},{3,2,1},{3,2,1,9,8,6},{3,2,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},{2}};
  boolean resDivisible[] = {true, true, false, false, true, false};

  void checkDivisible(int a) {
	  int[] list = numDivisible[a];
	  boolean result = resDivisible[a];
	  String errMsg = String.format("isDivisible incorrect for array %s", Arrays.toString(list));
	  assertEquals(errMsg, result, NumberProcessor.isDivisible(list));
  }
	  
  @Test(timeout=1000) public void isDivisible_00() { checkDivisible(0); } 
  @Test(timeout=1000) public void isDivisible_01() { checkDivisible(1); }
  @Test(timeout=1000) public void isDivisible_02() { checkDivisible(2); }
  @Test(timeout=1000) public void isDivisible_03() { checkDivisible(3); }
  @Test(timeout=1000) public void isDivisible_04() { checkDivisible(4); }
  @Test(timeout=1000) public void isDivisible_05() { checkDivisible(5); }
  
  
  int numConsecutiveDual[][]= {{1, -3, 4,  -2, -1, 6,9,9,9,-6,3},{-1, -3, 4, -1, -1, 2, 6, -4},{ -5, -7, -8, -4, -3, -2},{5, 6,2, 11, -4,  13, 5, 2},{285, 93, 90, 17, 65, 61}, {-37, 58, -89, -145, 42,-13}};
  boolean resConsecutiveDual[] = {true, true, false, false, false, false};

  void checkConsecutiveDual(int a) {
	  int[] list = numConsecutiveDual[a];
	  boolean result = resConsecutiveDual[a];
	  String errMsg = String.format("isConsecutiveDual incorrect for array %s", Arrays.toString(list));
	  assertEquals(errMsg, result, NumberProcessor.isConsecutiveDual(list));
  }
	  
  @Test(timeout=1000) public void isConsecutiveDual_00() { checkConsecutiveDual(0); } 
  @Test(timeout=1000) public void isConsecutiveDual_01() { checkConsecutiveDual(1); }
  @Test(timeout=1000) public void isConsecutiveDual_02() { checkConsecutiveDual(2); }
  @Test(timeout=1000) public void isConsecutiveDual_03() { checkConsecutiveDual(3); }
  @Test(timeout=1000) public void isConsecutiveDual_04() { checkConsecutiveDual(4); }
  @Test(timeout=1000) public void isConsecutiveDual_05() { checkConsecutiveDual(5); }
  
  
  int[][] numPair = { {2,17,25,0,-13, 10,0},{10},{18,0,0,0},{14,3,7,8},{12,4,6,9}};
  boolean[] resPair =  { false, false, false, true, true};

  void checkIsPairArray(int a) {
	  String errMsg = String.format("isPairArray incorrect for array %s", Arrays.toString(numPair[a]));
	  assertEquals(errMsg, resPair[a], NumberProcessor.isPairArray(numPair[a]));
  }
	  

  @Test(timeout=1000) public void isPairArray_00() { checkIsPairArray(0); }
  @Test(timeout=1000) public void isPairArray_01() { checkIsPairArray(1); }
  @Test(timeout=1000) public void isPairArray_02() { checkIsPairArray(2); }
  @Test(timeout=1000) public void isPairArray_03() { checkIsPairArray(3); }
  

  
 //############################################Honors Section Part############################### 
  /* 
   * 
   * This part only applies to Honors section students 
   *  if you are Honors section student, you have to uncomment this part 
   *  
   *  
   */
  /*
  int numMaxSum[][]= {{1, -3, 4,  -2, -1, 6,9,9,9,-6,3},{-1, -3, 4, -1, -1, 2, 6, -4},{ -5, -7, -8, -4, -3, -2},{5, 6,-2, 11, -4,  13, -5, 2},{285, 93, 90, 17, 65, 61}, {-37, 58, -89, -145, 42,-13}};
  int resMaxSum[] = {34, 10, 0, 29, 611, 58};
  
  
  void checkMaxSum(int a) {
	  int[] list = numMaxSum[a];
	  int result = resMaxSum[a];
	  String errMsg = String.format("MaxSum incorrect for array %s", Arrays.toString(list));
	  assertEquals(errMsg, result, NumberProcessor.maxSum(list));
  }
	  
  @Test(timeout=1000) public void MaxSum_00() { checkMaxSum(0); } 
  @Test(timeout=1000) public void MaxSum_01() { checkMaxSum(1); }
  @Test(timeout=1000) public void MaxSum_02() { checkMaxSum(2); }
  @Test(timeout=1000) public void MaxSum_03() { checkMaxSum(3); }
  @Test(timeout=1000) public void MaxSum_04() { checkMaxSum(4); }
  @Test(timeout=1000) public void MaxSum_05() { checkMaxSum(5); }
  
  
  int numSubArray[][]= {{1, -3, 4,  -2, -1, 6,9,9,9,-6,3},{-1, -3, 4, -1, -1, 2, 6, -4},{ -5, -7, -8, -4, -3, -2},{5, 6,-2, 11, -4,  13, -5, 2},{285, 93, 90, 17, 65, 61}, {-37, 58, -89, -145, 42,-13}};
  int resSubArray[][] = {{4,-2,-1,6,9,9,9}, {4,-1,-1,2,6}, new int[]{}, {5,6,-2,11,-4,13}, {285,93,90,17,65,61}, {58}};

  void checkMaxSubArray(int a) {
	  int[] list = numSubArray[a];
	  int[] result = resSubArray[a];
	  String errMsg = String.format("MaxSubArray incorrect for array %s", Arrays.toString(list));
	  assertArrayEquals(errMsg, result, NumberProcessor.maxSubArray(list));
  }
	  
  @Test(timeout=1000) public void MaxSubArray_00() { checkMaxSubArray(0); } 
  @Test(timeout=1000) public void MaxSubArray_01() { checkMaxSubArray(1); }
  @Test(timeout=1000) public void MaxSubArray_02() { checkMaxSubArray(2); }
  @Test(timeout=1000) public void MaxSubArray_03() { checkMaxSubArray(3); }
  @Test(timeout=1000) public void MaxSubArray_04() { checkMaxSubArray(4); }
  @Test(timeout=1000) public void MaxSubArray_05() { checkMaxSubArray(5); }

*/
}