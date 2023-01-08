import java.util.ArrayList;
import java.util.Collection;

public class MazeSolver {
	private static boolean x= false;
	ArrayList<MazePosition> collOfMaze = new ArrayList<>();
	ArrayList<String> directions = new ArrayList<String>();
	ArrayList<MazePosition> ar1 = new ArrayList<MazePosition>();
	private Maze maz;
	
	public MazeSolver(Maze m) {
		this.maz=m;
		
	}
	
	/*
	*@param: p - The method "conta" takes a MazePosition object as parameter	 
	*
	*@return: The "conta" method returns true if the the MazePosition object taken as parameter in not in the
	*	      ArrayList containing all visited positions. Returns false if it is in the ArrayList containing 
	*         all visited positions.
	*/
	public boolean conta(MazePosition p) {
		
		for(int i=0;i<collOfMaze.size();i++) {
			
			if(collOfMaze.get(i).equals(p)) {
				 x= false;
				 break;
			}
			else {
				x=true;
				
			}
		}
		return x;
	}
	
	/*
	*@param: p - The method "solve" takes a MazePosition object as parameter and a collection of visited positions	 
	*
	*@return: The "solve" method returns an ArrayList with directions to the Goal. It'll return Goal! if the initial
	*         position is the Goal. It'll return null if no path is found of if the initial position is 
	*         an obstacle.
	*/
	public ArrayList<String> solve(MazePosition p, Collection<MazePosition> visited){
		 
		 collOfMaze.add(p);
		 if(maz.isGoal(p)) {
			directions.add("Goal!");
			return directions;
		}
		 else if(!(maz.isGoal(p) || maz.isClear(p))) {
			 return null;
		 }

		else if((maz.isClear(p.up())||maz.isGoal(p.up()))&& conta(p.up())) {
			directions.add("Up");
			 solve(p.up(),collOfMaze);
		}
		
			
		else if((maz.isClear(p.left())||maz.isGoal(p.left()))&& conta(p.left())) {
			directions.add("Left");
				 solve(p.left(),collOfMaze);
			}
			
		
		else if((maz.isClear(p.down())||maz.isGoal(p.down()))&& conta(p.down())) {
				directions.add("Down");
				 solve(p.down(),collOfMaze);
			}
			
	
		else if((maz.isClear(p.right())||maz.isGoal(p.right())) && conta(p.right())) {
				directions.add("Right");
				
				 solve(p.right(),collOfMaze);
			}
			
			
		else if(!(conta(p.up()) && conta(p.left()) && conta(p.down()) && conta(p.right()))) {
			
			return null;
		}
		
	 
		 if(!(maz.isGoal(collOfMaze.get(collOfMaze.size()-1)))) {
			
			 MazePosition mz2=collOfMaze.get(0);
			
			 directions.clear();
			 collOfMaze.clear();
			 
			 return solveComplx(mz2,collOfMaze);
			
		}
		 
		return directions;
	
	}
	
	/*
	 * The ArrayList ar1 holds the last visited position when when pointer gets stuck in the maze. This
	*  way, when looking for a different path to the goal, the pointer will not access the last positions where
	*  it ended up getting stuck.
	*  
	*@param: p - The method "checkIfInAr1" takes a MazePosition object as parameter	 
	*
	*@return: the "checkIfInAr1" method will return true if a new position is not in the ar1 ArrayList. False otherwise.
	*	      
	*/
	public boolean checkIfInAr1(MazePosition p) {
		
		for(int i=0;i<ar1.size();i++) {
			
			if(ar1.get(i).equals(p)) {
				 x= false;
				 break;
			}
			else {
				x=true;		
			}
			
		}
		return x;
			
	}
	
	/*
	 * The method "solveComplx" is pretty much the same method as "solve" The difference is that when the
	 * pointer gets stuck using the "solve" method, this method will find a path to the Goal in a different 
	 * way. For this method, two ArrayLists are used for positions. ar1 and collOfMaze. ar1 stores last 
	 * positions of paths where the pointer gets stuck, and collOfMaze holds every position the pointer visits, but
	 * unlike ar1 that stores positions untill the goal is reached, collOfMaze deletes the path that did not take the 
	 * pointer to the goal and begins storing new paths. When the Goal is reached, collOfMaze will have the path to the 
	 * goal stored.
	 * 
	*@param: p - It takes a Mazeposition object and a collection of visited positions as parameter.
	*
	*@return: The "solveComplx" method will return an ArrayList of directions that will be passed to the last if 
	*		  statement in the "solve" method.
	*	      
	*/
		 public ArrayList<String> solveComplx(MazePosition p, Collection<MazePosition> visited){
			 
		
			 collOfMaze.add(p);
			 
			 if(maz.isGoal(p)) {
				solve(p,collOfMaze);
				return directions;
			}
			 
			 else if(!(maz.isGoal(p) || maz.isClear(p))) {
				 return null;
			 }
			 

			else if((maz.isClear(p.up())||maz.isGoal(p.up())) && checkIfInAr1(p.up())&& conta(p.up())) {
				directions.add("Up");
				solveComplx(p.up(),collOfMaze);
			}
			
				
			else if((maz.isClear(p.left())||maz.isGoal(p.left())) && checkIfInAr1(p.left())&& conta(p.left())) {
				directions.add("Left");
				solveComplx(p.left(),collOfMaze);
				}
				
			
			else if((maz.isClear(p.down()) || maz.isGoal(p.down())) && checkIfInAr1(p.down())&& conta(p.down())) {
					directions.add("Down");
					solveComplx(p.down(),collOfMaze);
				}
				

			else if((maz.isClear(p.right())||maz.isGoal(p.right())) && checkIfInAr1(p.right())&& conta(p.right())) {
					directions.add("Right");
					solveComplx(p.right(),collOfMaze);
				}
				
				
			else if(!(conta(p.up()) && conta(p.left()) && conta(p.down()) && conta(p.right()))) {
				
				return null;
			}	 
		 
			 
			if(!(maz.isGoal(collOfMaze.get(collOfMaze.size()-1)))) {
					
				 MazePosition mz2=collOfMaze.get(0);
				
					 ar1.add(collOfMaze.get(collOfMaze.size()-1));
					 
				 collOfMaze.clear();
				 directions.clear();
				 solveComplx(mz2,collOfMaze);
				 
			}
		 
		return directions;
		
	}

}
