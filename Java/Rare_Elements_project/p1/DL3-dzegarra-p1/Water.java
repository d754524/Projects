import java.awt.Color;
/**
 * 
 * @author Diego Zegarra
 *
 */
public class Water extends Element{
	
	/**
	 * @return the color of water
	 */
	@Override
	public Color getColor() {
		// TODO Auto-generated method stub
		return Color.BLUE;
	}
	
	/**
	 *@return the weight of water 
	 */
	@Override
	public int getWeight() {
		// TODO Auto-generated method stub
		return 50;
	}
	
	int counter=2;
	/**
	 * The fall method allows the element to move from where it was initially put to the bottom of the grid if there are no obstacles
	 */
	@Override
	public void fall(DynamicArray<DynamicArray<Element>> grid, int row, int col)  {
		
		double j =  Math.random()*11;
		

			try {
				
					if(grid.get(row).get(col).getWeight()>grid.get(row+1).get(col).getWeight()) {
					
					
						grid.get(row).set(col, grid.get(row+1).get(col));
						grid.get(row+1).set(col, this);
					
					
					}
			else if(j>5.5){
				
					 if(grid.get(row).get(col).getWeight()==grid.get(row+1).get(col).getWeight()) {
					
						
							if(col-1>=0 ) {
								
									if(grid.get(row).get(col).getWeight()>grid.get(row).get(col-1).getWeight() ) {
											
											grid.get(row).set(col, grid.get(row).get(col-1));
											grid.get(row).set(col-1, this);
											
									}
					
							}
				  
				
					}
			}
			else if(j<5.5) {
					 if(col<=grid.get(row).capacity()-1 ) {
				
										if(grid.get(row).get(col).getWeight()>grid.get(row).get(col+1).getWeight() ) {
				
											grid.get(row).set(col, grid.get(row).get(col+1));
											grid.get(row).set(col+1, this);
											
				
										}
						
								}
				
			}	

				
			}		
			 catch (Exception e) {
					
			}
		
		
		
	}
	
	/**
	 * The push method is used to have the water move depending on how it is pushed.
	 */
	boolean tf;
	@Override
	public boolean push(DynamicArray<DynamicArray<Element>> grid, int row, int col) {
		
	
		return tf;
	}

}
