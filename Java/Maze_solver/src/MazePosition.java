
public class MazePosition {

	private static boolean x;
	private int row;
	private int column;
	
	public MazePosition(int row, int column) {
		
		this.row=row;
		this.column=column;
		
	}
	
	public int getRow() {
		
		return row;
	}
	public int getCol() {
		
		return column;
	}
	
	public MazePosition left() {
		
		return new MazePosition(row,column-1);
	}
	public MazePosition right() {
		
		return new MazePosition(row,column+1);
	}
	
	public MazePosition up() {
		
		return new MazePosition(row-1,column);
	}
	
	public MazePosition down() {
		
		return new MazePosition(row+1,column);
	}
	
	/*
	*@param: o - The method "equals" takes an object as parameter 
	*
	*@return: The "equals" method return true if the object taken as a parameter is an instance
	*		  of MazePosition and has the same number of rows and columns as the MazePosition object
	*         calling this method.
	*/
	public boolean equals(Object o) {
		
		if(o instanceof MazePosition) {
			
			o = (MazePosition)o;
			int ro = ((MazePosition) o).getRow();
			int col = ((MazePosition) o).getCol();
			
			if(this.getRow()==ro && this.getCol()==col) {
				x=true;
			}
			else {
				x=false;
			}
			
		}else {
			x=false;
		}

		return x;
	
	}
	
	
}
