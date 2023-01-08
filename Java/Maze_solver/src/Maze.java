import java.util.ArrayList;

public class Maze {

	private static boolean x;
	private char[][] data;
	private static int counter=0;
	
	public Maze(char[][] data) {
		
		this.data=data;
		
	}
	public Maze(ArrayList<String> data) {
		ArrayList<Character> ar1 = new ArrayList<Character>();
		
		for(int i=0;i<data.size();i++) {
			
			for(int k=0;k<data.get(i).length();k++) {
			
				ar1.add(data.get(i).charAt(k));
	
			}
		}
		

	this.data = new char[data.size()][data.get(0).length()];
			
		for(int i=0;i<data.size();i++) {
			for(int k=0;k<data.get(i).length();k++) {
			
				this.data[i][k]=ar1.get(counter++);
			}
		}
		counter=0;
	}
	/*
	*@param: p - The method "isClear" takes a MazePosition object as parameter	 
	*
	*@return: The "isClear" method return true is position p on the Maze is an unobstructed position. False otherwise.
	*
	*/
	public boolean isClear(MazePosition p) {
	
		try {
			
			int r= p.getRow();
			int c= p.getCol();
			
			if(this.data[r][c]==' ') {
				x=true;
			}else {
				x=false;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
			x=false;
			
		}
		
		return x;
	
	}

	/*
	*@param: p - The method "isGoal" takes a MazePosition object as parameter	 
	*
	*@return: The "isGoal" method return true is position p on the Maze is the goal. False otherwise.
	*/
	public boolean isGoal(MazePosition p) {
		
		try {
			
			int r= p.getRow();
			int c= p.getCol();
			
			if(this.data[r][c]=='G') {
				x=true;
			}else {
				x=false;
			}
		}
		catch(IndexOutOfBoundsException e) {
			
			x=false;
			
		}
		
		return x;
			
	}
		
}
