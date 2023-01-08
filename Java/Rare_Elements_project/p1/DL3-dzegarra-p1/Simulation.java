//TO DO: Add your name (as an author), complete the required methods.

/**
 *  The simulator. This tracks the elements in a grid
 *  and coordinates that with the display.
 *  
 *  @author Dave Feinberg, K. Raven Russell, and Diego Zegarra
 */
public class Simulation {
	
	//******************************************************
	//*******  DO NOT EDIT ANYTHING IN THIS SECTION  *******
	//*******        But do read this section!       *******
	//******************************************************
	
	/**
	 *  The default number of rows the grid should have.
	 */
	public static final int INIT_ROWS = 120;
	
	/**
	 *  The default number of columns the grid should have.
	 */
	public static final int INIT_COLS = 80;

	/**
	 *  The grid that represents the location of each element.
	 */
	private final DynamicArray<DynamicArray<Element>> grid;
	
	/**
	 *  The GUI display.
	 */
	private final Display display;
	
	//******************************************************
	//* END DO-NOT-EDIT SECTION -- DO NOT ADD MORE FIELDS! *
	//******************************************************
	int findNullRow,findNUllCol;
	/**
	 *  Sets up the instance variables (grid and display).
	 *  
	 *  @param withDisplay whether or not the display should be created (for testing purposes)
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Simulation(boolean withDisplay)  {
		
		
		grid = new DynamicArray<DynamicArray<Element>>(INIT_ROWS);
		
		for(int i=0;i<INIT_ROWS;i++) {
			
				DynamicArray e = new DynamicArray(INIT_COLS);
				grid.set(i,e);
				
				for(int j=0;j<e.capacity();j++) {
					try {
						grid.get(i).set(j, new Empty());
					} catch (Exception e1) {
						
						
					}
				}
				
		}
		
		display = new Display("Project 1 Simulation",INIT_ROWS,INIT_COLS);
	
	}

	/**
	 *  This is called when the user clicks on a location using the given tool.
	 *  
	 *  @param row the row where the action happened
	 *  @param col the column where the actio happened
	 *  @param newElem the element the user has created to put there
	 * @throws Exception 
	 */
	public void locationClicked(int row, int col, Element newElem) {
		
		
		try {
			grid.get(row).set(col, newElem);
		} catch (Exception e) {
			
			
		}
	}
	
	/**
	 * Copies each element of grid's color into the display.
	 * @throws Exception 
	 */
	public void updateDisplay()  {
		
		for(int i=0;i<grid.size();i++) {
			try {
				for(int j=0;j<grid.get(0).size();j++) {
				display.setColor(i, j, grid.get(i).get(j).getColor());	
				
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 *  Resizes the grid (if needed) to a bigger or smaller size.
	 *  
	 *  @param numRows the new number of rows the grid should have
	 *  @param numCols the new number of columns the grid should have
	 *  @return whether or not anything was changed
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean resize(int numRows, int numCols) {
		
		boolean tf=true;
	try {	
		if(numRows<1 || numCols<1) {
			
			System.out.println("Invalid!");
			return false;
		}
		
		 if(numRows > grid.size()) {
			
		
			int diffInRows = numRows-grid.size();
			
			
			for(int i=0;i<diffInRows;i++) {
				
				DynamicArray ar = new DynamicArray(numCols);
	
				for(int j=0;j<ar.capacity();j++) {
					
					ar.set(j, new Empty());
				}
				
				grid.add(ar);
				
			}
			
	
		}
		 
		 if(numRows<grid.capacity()) {
			 
			
			 
			 for(int i=grid.size()-1;i>numRows;i--) {
					grid.remove(i);
					
				} 
			 
			 
		 }
		 
		 if(numCols > grid.get(0).size()) {
			 int diffInCols = numCols-grid.get(0).size();
			 
			 for(int i=0;i<diffInCols;i++) {
					
					
					for(int j=0;j<grid.size();j++) {
						
						grid.get(j).add(new Empty());
						
					} 
	
				} 
 
		 }

		 if(numCols < grid.get(0).size()) {
			
			 int diffInCols = grid.get(0).size()-numCols;
		
			 
			 	for(int i=0;i<diffInCols;i++) {
			 		
			 		
			 		
			 		for(int j=0; j<grid.size() ; j++) {
			 			
			 			
			 			if(grid.get(j).get(grid.get(j).size()-1).pushLeft(grid, j, grid.get(j).size()-1)) {
			 				
			 				grid.get(j).get(grid.get(j).size()-1).pushLeft(grid, j, grid.get(j).size()-1);
			 				
			 			}
			 			else {
			 				int k=j;
			 				
			 				if(k==-1)break;
			 				
			 				while(!(grid.get(k).get(grid.get(k).size()-1).pushLeft(grid, k, grid.get(k).size()-1))) {
		 					
			 					grid.get(k).get(grid.get(k).size()-1).pushUp(grid, k, grid.get(k).size()-1);
			 					
			 					k--;
			 					
			 				}
			 				
			 				grid.get(k).get(grid.get(k).size()-1).pushLeft(grid, k, grid.get(k).size()-1);
			 				
			 			}
			 			
			 		}
			 		

			 		for(int p=grid.size()-1;p>=0;p--) {
			 			
		 				grid.get(p).remove(grid.get(p).size()-1);
		 				
		 			}
			 			
			 	}
			 	

		 }
	}
	catch(Exception e) {
		
	}
		
		return tf;
		
	}

	
	/**
	 *  Indicates the private post where you have shown off your
	 *  new element(s).
	 *  
	 *  @return the post where you describe your new element
	 */
	public static String newElementPost() {
		//[GUI:Advanced] change this to return the FULL URL of
		//the post where the grader can find your new element
		//demo, but ONLY if you completed the [GUI:Advanced] part
		//of the project.
		return null;
	}
	
	//******************************************************
		//*******  DO NOT EDIT ANYTHING BELOW THIS LINE  *******
		//*******        But do read this section!       *******
		//******************************************************

		/**
		 *  Causes one random particle to maybe do something. Called
		 *  repeatedly.
		 */
		public void step() {
			int row = (int)(Math.random()*grid.size());
			int col = (int)(Math.random()*grid.get(row).size()) ;
			
			grid.get(row).get(col).fall(grid, row, col);
		}
		
		/**
		 *  Game loop of the program (step, redraw, handlers, etc.).
		 */
		public void run() {
			while (true) {
				//step
				for (int i = 0; i < display.getSpeed(); i++) step();
				
				//redraw everything
				updateDisplay();
				display.repaint();
				
				//wait for redrawing and for mouse
				display.pause(1);
				
				//handle person actions (resize and tool usage)
				if(display.handle(this) && display.pauseMode()) {
					//for debugging
					updateDisplay();
					display.repaint();
					display.pause(5000);
				}
			}
		}
		
		/**
		 *  Convenience method for GTA testing. Do not use this in
		 *  your code... for testing purposes only.
		 *  
		 *  @return the private grid element
		 */
		public DynamicArray<DynamicArray<Element>> getGrid() {
			return grid;
		}
		
		/**
		 *  Main method that kicks off the simulator.
		 *  
		 *  @param args not used
		 */
		public static void main(String[] args) {
			(new Simulation(true)).run();
		}
	}
