/**
Name        : Wong Kang Fei
Matric No.  : A0138862W
PLab Acc.   : plab1338
 */

import java.util.*;

public class Orders {

	private ArrayList<LinkedList<Item>> processors;
	public void run() {
		// implement your "main" method here
		
		// read input
		Scanner sc = new Scanner(System.in);

		int numProc = sc.nextInt();
		int numOrder = sc.nextInt();
		
		// store list of processors
		processors = new ArrayList<LinkedList<Item>>();
		
		// init processors
		for(int i = 0; i< numProc; i++){
			processors.add(new LinkedList<Item>());
		}
	
		int orderCount = 1;
		int timePassed = 0;
		
		// given each order
		for(int i = 0; i<numOrder; i++){
			int arrival = sc.nextInt();
			int duration = sc.nextInt();

			//System.out.println(timePassed + ":"+arrival);
			
			// we calculate the time passed against each order arrival
			while(timePassed <= arrival){
				// for each item in the processors
				for(int j = 0; j<numProc; j++){ // you are not 
allowed to use array to solve the main part of problem
					if(!processors.get(j).isEmpty()){ // if the 
processor is not empty 
						// we decrease the 
duration/processing time of the front
						
processors.get(j).peek().decDuration();
						
						// if any of the item in the 
processor is ready
						// we display the order result
						
if(processors.get(j).peek().getDuration() <= 0){
							processors.get(j).poll();
							
System.out.println(orderCount + " " + (j+1) +" "+ timePassed);
							orderCount++;
							//you need to break here to 
prevent the order from being ordered to multiple processors
						}
						
					}
				}

				// increment timePassed
				timePassed++;
			}

			// check for the next available processor to queue the new 
item
			LinkedList<Item> nextProcFinishing = null;
			for(int j = 0; j<numProc; j++){
				if(processors.get(j).isEmpty()){
					// if a processor is empty, we push new item 
into the queue, break loop early
					processors.get(j).offer(new Item(duration));
					nextProcFinishing = null;
					break;
				}else{

					// otherwise, we check for the next earliest 
finishing processor
					if(nextProcFinishing == null){
						nextProcFinishing = 
processors.get(j);
					}else{
						nextProcFinishing = 
processors.get(j).peek().getDuration() < nextProcFinishing.peek().getDuration()? 
processors.get(i) : nextProcFinishing;
						//Exception here, what if peek() is 
null?
					}
				}
			}

			
			if(nextProcFinishing != null){
				nextProcFinishing.offer(new Item(duration));
			}
		}

		
		boolean isAllProcEmpty = false;
		// continue poll out all the item in queue until all of them is 
empty
		// display result
		// NOTE: got bug, cannot poll out all the items.... T_T
		// why you need to make all processors empty?
		while(!isAllProcEmpty){
			for(int i=0; i<numProc; i++){
				if(!processors.get(i).isEmpty()){
					processors.get(i).peek().decDuration();

					if(processors.get(i).peek().getDuration() <= 
0){
						processors.get(i).poll();
						System.out.println(orderCount + " "+ 
(i+1) + " " +timePassed);
						orderCount++;
					}
				}
			}
			
			timePassed++;

			boolean atLeastOneIsNotEmpty = false;

			for(int i=0; i<numProc; i++){
				if(!processors.get(i).isEmpty()){
					atLeastOneIsNotEmpty = true;
					break;
				}
			}

			isAllProcEmpty = true;
		}

		//System.out.println(processors.get(0));

	}

	public static void main(String[] args) {
		Orders o = new Orders();
		o.run();
	}
}

// class to represent each item
class Item{
	private int duration;

	public Item(int duration){
		this.duration = duration;
	}
	// decrease internal duration by 1
	public void decDuration(){
		this.duration--;
	}

	public int getDuration(){
		return this.duration;
	}
}

