

import java.util.ArrayList;

public class Main1 {
	
	static ArrayList<Set> output= new ArrayList<Set>();
	
	/**
	The MIS method implements the MIS algorithm by taking input as random set. 
	 */
	public static int MIS(ArrayList<Set> set) {
		MaximumIndepentSet MISObj= new MaximumIndepentSet();
		ArrayList<Set> sCrossUsPositive= new ArrayList<Set>();
		Set subSet= new Set();
		Set outPut= new Set();
		int x=0;
		Double median;
		MISObj.set=set;
		MISObj.convertMIS();
		//******************************************Algorithm Implementation*********************************
		subSet=MISObj.selectRandomSet();
		median=(double)subSet.right;
		//median=MISObj.medianOfSet(subSet);
		MISObj.partitionSet(median);
		if(!MISObj.sNegative.isEmpty()) {
			x=MIS(MISObj.sNegative);
			MISObj.deleteFromSCrossingSet(x);
		}
		MISObj.deleteFromSCrossingSetLeaveLeftMostRightEndPoint();
		if(MISObj.sPositive.isEmpty()) {
			if(MISObj.sCrossing.size()==1) {
				outPut=MISObj.sCrossing.get(0);
				System.out.println(String.format("The Output is {%d,%d}",outPut.left,outPut.right));
				output.add(outPut);
				x=outPut.right;
			}
		}
		else {
			sCrossUsPositive=MISObj.sCrossing;
			sCrossUsPositive.addAll(MISObj.sPositive);
			x=MIS(sCrossUsPositive);
		}	
		return x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ArrayList<Set> set= new ArrayList<Set>();
		MaximumIndepentSet MISObj= new MaximumIndepentSet();
		set=MISObj.ReadInputCSV();
		int x=0;
		x=MIS(set);
		MISObj.writeOutput(output);
	}
}
