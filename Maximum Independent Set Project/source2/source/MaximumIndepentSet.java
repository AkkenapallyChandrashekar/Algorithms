/**
 * 
 */


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author MYPC
 *
 */

 /**
 * The class Set is created in order to store each set of every interval.
 *The  Comparator method is used to compare between between two sets and return the maximum one. 
  */
class Set{
	double left=0.00;
	double right=0.00;
	public static Comparator<Set> SetComparator = new Comparator<Set>() {
		public int compare(Set s1, Set s2) {
			double temp=s1.right-s2.right;
			int n=(int)temp;
	    	return n;
	    }
	};
    
}

public class MaximumIndepentSet {
	
	ArrayList<Set> set= new ArrayList<Set>();
	ArrayList<Set> sCrossing= new ArrayList<Set>();
	ArrayList<Set> sPositive= new ArrayList<Set>();
	ArrayList<Set> sNegative= new ArrayList<Set>();
	Set subSet= new Set();
	

	public ArrayList<Set> convertMIS(){
		for(int i=0;i<this.set.size();i++) {
			if(this.set.get(i).left>this.set.get(i).right) {
				double temp=this.set.get(i).left;
				this.set.get(i).left=this.set.get(i).right;
				this.set.get(i).right=temp;
			}
		}
		return this.set;
	}
	
	/**
	*The selectRandomSet is select any random set of entire set. For example {8,2} set is randomly select from set {{8,2},{3,6},{4,9},{5,1}}
	 */
	public Set selectRandomSet() {
		Collections.sort(this.set, Set.SetComparator);
		Set subSet= new Set();
		Collections.sort(this.set, Set.SetComparator);
		subSet=this.set.get(this.set.size()-1);
        return subSet;
		
	}

	 /**
	 *The medianOfSet is a method which takes the input set and calculates the median of it.
	  */   	
	public Double medianOfSet(Set subSet) {
		Double median=((double)(subSet.left+subSet.right)/2);
		return median;
	}
	
	/**
	The partitionSet method helps to partition the main set into scrossing, snegative and spositive by taking calculated median as input
	 */
	public void partitionSet(Double median) {
		for(int i=0;i<this.set.size();i++) {
			this.subSet=this.set.get(i);
			 if((median>=this.subSet.left)&&(median<=this.subSet.right)) {
				 this.sCrossing.add(subSet);
			 }
			 else if(median>this.subSet.right) {
				 this.sNegative.add(subSet);
			 }
			 else if(median<this.subSet.left) {
				 this.sPositive.add(subSet);
			 }
		}
	}
	
	/**
	*The deleteFromScrossingSet is method which helps to delete from scrossing any intervals containing x.
	 */
	public void deleteFromSCrossingSet(double deleteitem) {
		for(int i=0;i<this.sCrossing.size();i++) {
			if((deleteitem>=this.sCrossing.get(i).left)&&(deleteitem<=this.sCrossing.get(i).right)) {
				this.sCrossing.remove(i);
			}
		}
	}
	
	/**
	*The deleteFromSCrossingSetLeaveLeftMostRightEndPoint()  is method which deletes from S| all but the segment 
	*with leftmost right endpoint, if any
	 */
	public void deleteFromSCrossingSetLeaveLeftMostRightEndPoint() {
		Collections.sort(this.set, Set.SetComparator);
		if(this.sCrossing.size()>1) {
			double min=this.sCrossing.get(0).right;
			for(int i=0;i<this.sCrossing.size();i++) {
				if(this.sCrossing.get(i).right!=min) {
					this.sCrossing.remove(i);
				}	
			}
		}
		
			
	}
	
	/**
	The display function is used to display the calculated MIS for random set given as input
	 */
	public void displayMIS() {
		System.out.println("\nPrinting set\n");
		if(this.set.size()!=0) {
		for(int i=0;i<this.set.size();i++) {
			System.out.println(String.format("{%.2f,%.2f} ", this.set.get(i).left,this.set.get(i).right));
		}}
		System.out.println("\nPrinting sCrossing\n");
		if(this.sCrossing.size()!=0) {
		for(int i=0;i<this.sCrossing.size();i++) {
			System.out.printf(String.format("{%.2f,%.2f}", this.sCrossing.get(i).left,this.sCrossing.get(i).right));
		}}
		System.out.println("\nPrinting sNegative\n");
		if(this.sNegative.size()!=0) {
		for(int i=0;i<this.sNegative.size();i++) {
			System.out.printf(String.format("{%.2f,%.2f}", this.sNegative.get(i).left,this.sNegative.get(i).right));
		}}
		System.out.println("\nPrinting sPositive\n");
		if(this.sPositive.size()!=0) {
		for(int i=0;i<this.sPositive.size();i++) {
			System.out.printf(String.format("{%.2f,%.2f}", this.sPositive.get(i).left,this.sPositive.get(i).right));
		}}
	}
	
	/**
	*The writeOutput method helps to write the ouput data into 
	 */
	public void writeOutput(ArrayList<Set> outputset) {
		try {
			String fileName="output-"+String.valueOf(outputset.size())+".csv";
			String output ="";
			for(int i=0;i<outputset.size();i++) {
				if(output.equals("")) {
					output=String.valueOf(outputset.get(i).left)+","+String.valueOf(outputset.get(i).right);
				}
				else {
				output =output+","+String.valueOf(outputset.get(i).left)+","+String.valueOf(outputset.get(i).right);
				}
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, false));// We are trying to write the data into the file
			writer.write(output);//We are trying to write the output into the file.
			writer.close();
		} catch (IOException e) {
			System.out.print(e.getMessage());
		}
	}
	
}
