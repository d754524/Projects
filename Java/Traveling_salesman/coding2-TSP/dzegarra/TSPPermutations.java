//This is mostly done for you. See the "YOUR CODE HERE" spots below.

import edu.uci.ics.jung.graph.Graph;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 *  This algorithm attempts to solve the TSP problem by
 *  checking all possible travel plans and choosing the
 *  smallest one found.
 *  
 *  @author Katherine (Raven) Russell and Diego Zegarra
 */
class TSPPermutations extends TSPAlg {
	//*********************************************************************
	//*******                                                       *******
	//*******   You should not need to edit anything in this part.  *******
	//*******   However, you may need to read everything here.      *******
	//*******                                                       *******
	//*********************************************************************
	
	/**
	 *  A iterator over all permutations of cities (except
	 *  the first city which is "locked in").
	 */
	Iterator<List<City>> permutationItr;
	
	/**
	 *  The current permutation being examined by the algorithm.
	 */
	List<City> currentPermutation = null;
	
	/**
	 *  {@inheritDoc}
	 */
	public void reset(Graph<City, Flight> graph) {
		super.reset(graph);
		permutationItr = null;
		currentPermutation = null;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void start() {
		super.start();
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void finish() {
		System.out.println("TSP Via Permutation:");
		if(visitOrder != null) {
			this.visitOrder.add(0, this.startingCity);
		}
		super.finish();
	}
	
	//*********************************************************************
	//*******                                                       *******
	//*******    You have a number of things to do in this part.    *******
	//*******                                                       *******
	//*********************************************************************
	
	/**
	 *  Get the next permutation. Note that a permutation does
	 *  NOT include the starting city since this should be "locked
	 *  in place". If you include the starting city in the permuations
	 *  you end up examining "rotations" of trips you have already
	 *  looked at.
	 *  
	 *  @return the next permutation of cities (other than the starting city)
	 */
	int counter = 0;
	
	public List<City> permutate() {
		//YOUR CODE HERE
		Collection<City> citiesInGraph = graph.getVertices();	//Collection of vertices of the graph

		List<City> tempList = new ArrayList<>();
		Iterator<City> graphIterator = citiesInGraph.iterator();	//Iterator for cities in the graph

		while(graphIterator.hasNext()){
			City tempCity = graphIterator.next();
			if(tempCity==startingCity){
				continue;
			}
			tempList.add(tempCity);
		}
		
		permutationItr = new PermutationIterator<>(tempList);

		while(permutationItr.hasNext()){
	
			for(int i =0; i<counter ; i++){
				permutationItr.next();
			}
			
			if(permutationItr.hasNext()){
				List<City> t = permutationItr.next();
				counter++;
				return t;
			}
			else{
				counter=0;
				return null;
			}
			
		}

			
		/*
		Basic instructions:
			- if there is not permutation iterator, get one and continue
			- if the permutation iterator doesn't have anything remaining,
				return null
			- otherwise return the next permutation from the permutation
				iterator
		
		Details:
			- The code for the permuation iterator is in PermutationIterator.java
			- It is a basic Java Iterator, so it has everything you'd expect from one of those
			- To create a new permuation iterator, you need to:
				(1) get all the vertices in the graph, the graph is a JUNG graph so see
					http://jung.sourceforge.net/doc/api/edu/uci/ics/jung/graph/Graph.html,
					for documentation, especially the parent interface (HyperGraph) methods
				(2) from the list of vertices, remove the starting city, see description
					of this method for why
				(3) create a permuation iterator for the remaining cities and assign
					it to the appropriate instance variable
		*/
		
		//dummy return, replace this!
		return null;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public boolean setupNextStep() {
		//YOUR CODE HERE
		currentPermutation = permutate();
		if(currentPermutation!=null){
			return true;
		}
	
		/*
		Basic instructions:
			- set the current permutation to the next permutation
				(using the method you wrote above to get the next permuation)
			- if there is no next permuation, return false
			- otherwise return true
		*/
		
		//dummy return, replace this!
		return false;
	}
	
	/**
	 *  {@inheritDoc}
	 */
	public void doNextStep() {
		//YOUR CODE HERE
		
		int count = 0;
		int totalCost = 0;
		Iterator<City> iteratorForCurrentPermutation = currentPermutation.listIterator();	//Iterator for the current permutation
		Collection<Flight> prev = null;
		Collection<Flight> curr = null;
		Collection<Flight> tempStorage = null;

		if(iteratorForCurrentPermutation.hasNext()){
			tempStorage = graph.getOutEdges(iteratorForCurrentPermutation.next());	//Edges of the first city in the current permutation
		}

		while(iteratorForCurrentPermutation.hasNext()){
			
			prev = 	tempStorage;	//Edges of a city before another city in the permutation. City 1.
			Iterator IteratorOverPrevCity = prev.iterator();	// Iterator over the edges of the city above

			if(iteratorForCurrentPermutation.hasNext()){	//Checks to see if there is a city after the city stored in 'prev'
				curr = graph.getOutEdges(iteratorForCurrentPermutation.next());	//If there is another city, this line get the edges of this new city. City 2.
				
					while(IteratorOverPrevCity.hasNext()){
						
						Flight tempEdge = (Flight)IteratorOverPrevCity.next();	//Store an edge temporarily

						if(curr.contains(tempEdge)){	//Checks which edge City 1 and City 2 share so that we can highlight it and add it to the total cost.
							
							
							tempEdge.setColor(COLOR_ACTIVE_EDGE);
							totalCost+=tempEdge.getCost();
							break;
							
						}
						
					}	
			}		
			tempStorage = curr;
		}
		
		Iterator it = currentPermutation.iterator();	//Iterator over the current permutation
		Collection<Flight> edgesOfFirstCity = null;	//Edges of the first city in the permutation
		Collection<Flight> edgesOfLastCity = null;	//Edges of the last city in the permutation
		Collection<Flight> edgesOfStartingCity = graph.getOutEdges(startingCity);	//Edges of starting city

		for(int i=0;i<1;i++){	
			if(it.hasNext()){
				edgesOfFirstCity = graph.getOutEdges((City)it.next());	//Stores the edges of the first city in the permutation
			}
		}

		it = edgesOfFirstCity.iterator();	//Iterator over the edges of the first city

		while(it.hasNext()){	// Looks for the edge that connects the starting city and the first city in the permutation
			Flight temp = (Flight)it.next();
			if(edgesOfStartingCity.contains(temp)){
				temp.setColor(COLOR_ACTIVE_EDGE);
				totalCost+= temp.getCost();
				break;
			}
		}

		it = currentPermutation.iterator();	//Iterator over the current permutation

		while(it.hasNext()){
			edgesOfLastCity = graph.getOutEdges((City)it.next());	//Stores the edges of the last city in the permutation
		}
		it = edgesOfLastCity.iterator();
		while(it.hasNext()){	// Looks for the edge that connects the starting city and the last city in the permutation
			Flight temp = (Flight)it.next();
			if(edgesOfStartingCity.contains(temp)){
				temp.setColor(COLOR_ACTIVE_EDGE);
				totalCost+= temp.getCost();
				break;
			}
		}


		if(totalCost<visitOrderCost){	//Checks if the total cost of the current permutation is less than the previous cost.
			visitOrderCost = totalCost;
			visitOrder = currentPermutation;
			counter=0;
		}
		
	
		
		/*
		Basic instructions:
			- using the current permuation, determine the cost of the route
			- if it is better than the best route found so far, store it in
				the visit order list and visit order cost (from the parent
				TSPAlg class)
			- while you're doing this, highlight the edges for the current
				permutation
		
		Details:
			- a permutation is just a list of cities (excluding the starting city)
			- to get the cost of a flight between two cities you need to use the graph
			- the graph is a JUNG graph so see http://jung.sourceforge.net/doc/api/edu/uci/ics/jung/graph/Graph.html,
				for documentation, especially the parent interface (HyperGraph) methods
			- an edge in the graph is a flight, so to find out how to get the cost of
				a flight see the Flight class (in Flight.java) and the parent class
				GraphEdge (in GraphEdge.java)
			- to set the color of an edge in the graph, see methods in those same Flight
				and GraphEdge classes (the color should be COLOR_ACTIVE_EDGE from TSPAlg)
			- DO NOT FORGET to add in the cost from the start city to the first city in
				the permutation AND back again!
		*/
	}

}
