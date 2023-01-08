import java.awt.Color;
/**
 * 
 * @author Diego Zegarra
 *
 */
public class Sand extends Element{
	
	/**
	 * @return the color of sand
	 */
	@Override
	public Color getColor() {
		
		return Color.YELLOW;
	}
	
	/**
	 * @return the weight of sand 
	 */
	@Override
	public int getWeight() {
		
		return 131072;
	}
	
	int counter =6;
	/**
	 * 
	 * The fall method allows the element to move from where it was initially put to the bottom of the grid if there are no obstacles.
	 *
	 */
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
	double j =  Math.random()*11;	
	
	try {

					if(grid.get(row).get(col).getWeight()>grid.get(row+1).get(col).getWeight()) {
				
				
							grid.get(row).set(col, grid.get(row+1).get(col));
								grid.get(row+1).set(col, this);

					}
			
			
			

					else if(j>5.8) {
		 		
							if(grid.get(row).get(col).getWeight()>grid.get(row+1).get(col-1).getWeight()) {
					
									grid.get(row).set(col, grid.get(row+1).get(col-1));
										grid.get(row+1).set(col-1, this);
							}
				
							else if(grid.get(row).get(col).getWeight()>grid.get(row+1).get(col+1).getWeight()){
					
									grid.get(row).set(col, grid.get(row+1).get(col+1));
									grid.get(row+1).set(col+1, this);
							}
		
					}
		
					else if(j<5.8) {
				
							if(grid.get(row).get(col).getWeight()>grid.get(row+1).get(col+1).getWeight()){
						
									grid.get(row).set(col, grid.get(row+1).get(col+1));
									grid.get(row+1).set(col+1, this);
							}
				
							else if(grid.get(row).get(col).getWeight()>grid.get(row+1).get(col-1).getWeight()) {
						
									grid.get(row).set(col, grid.get(row+1).get(col-1));
									grid.get(row+1).set(col-1, this);
							}
						

					}
	
		}
				
			
	
		 catch (Exception e) {
		
			
		}
	
	}
	
	boolean tf=false;
	/**
	 * The push method is used to have the sand move depending on how it is pushed.
	 */
	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {

		return tf;
	}

	
	
	
}
