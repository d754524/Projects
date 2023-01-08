import java.util.ArrayList;
import java.util.Collection;

public class pruebita {

	public static void main(String[] args) {
		
		
//		ArrayList<String> ar = new ArrayList<String>();
//		ar.add("diego");
//		ar.add("*****");
//		ar.add("osngs");
//		Maze is1 = new Maze(ar);
		
		
//		ArrayList<String> data = new ArrayList<String>();
//		  data.add("** *******");
//	      data.add("*        *");
//	      data.add("*   *    *");
//	      data.add("*        *");
//	      data.add("* *      *");
//	      data.add("*     *  *");
//	      data.add("*   *  * *");
//	      data.add("*   *   **");
//	      data.add("*        *");
//	      data.add("********G*");
//	      Maze m = new Maze(data);
//	      Collection<MazePosition> solution = new ArrayList<MazePosition>();
//	      MazeSolver ms1 = new MazeSolver(m);
//	      MazePosition mz1 = new MazePosition(0,2);
//	      
//	      for(String x: ms1.solve(mz1, solution)) {
//	    	  System.out.print(x+" ");
//	      }
	      
		ArrayList<String> al_data = new ArrayList<String>();
	      al_data.add("****");
	      al_data.add("*  G");
	      al_data.add("****");
	      Maze m = new Maze(al_data);
	      Collection<MazePosition> solution = new ArrayList<MazePosition>();
	      MazeSolver ms1 = new MazeSolver(m);
	      MazePosition mz1 = new MazePosition(1,1);
	      for(String x: ms1.solve(mz1, solution)) {
	    	  System.out.print(x+" ");
	      }
	}
	
}
