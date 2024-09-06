/**
 * 
 */


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

/**
 * @author MYPC
 *
 */


public class TRandom {
	
	int nOfIntervals=0;
	int nOfInstances=0;
	ArrayList<Set> set1; //This set is used to store the li and ri values for t 1/n
	ArrayList<Set> set2;//This set is used to store the li and ri values of  t 1/sqrt(n)
	ArrayList<Set> set3;//This set is used to store the li and ri values of  t 1/logn
	ArrayList<Set> set4;//This set is used to store the li and ri values of  t 1/4
	double[][] Vi;

	/**
	 * This generateRandomNumberOfIntervals is a function which helps to generate random random number of intervals within the range
	 * of 5 and 35 for each instance. 
	 */
	
	public int generateRandomNumberOfIntervals() {

		Random r = new Random();
		int low = 5;
		int high = 35;
		this.nOfIntervals = r.nextInt(high - low) + low;
		// System.out.println(this.nOfIntervals);
		this.Vi=new double[this.nOfIntervals][4];
		return this.nOfIntervals;
	}

   /**
    * The readNumberOfInstances function is used to read the number of instances from user as input. 
    */
	public void readNumberOfInstances(){
		Scanner sc=new Scanner(System.in);
		System.out.println("\nEnter the number of instances: ");
		this.nOfInstances=sc.nextInt();
		System.out.println(String.format("The entered number of instances is %d",this.nOfInstances));
	}
	/**
	*The generateRandomNumber is a function which takes the input parameters as low and high and generates a random number within
	*that range
	 */
	public double generateRandomNumber(double low, double high) {
		Random r = new Random();
		//double temp=(double)(high - low);
		double result = low +(high-low)*r.nextDouble();
		return result;
	}
	/**
	*
	The generateLiValues is a function which generates different Li values for each interval of every instance.
	 */
	public void generateLiValues(){

		set1= new ArrayList<Set>();
		set2= new ArrayList<Set>();
		set3= new ArrayList<Set>();
		set4= new ArrayList<Set>();

		for(int i=0;i<this.nOfIntervals;i++) {
			Set tempset= new Set();
			tempset.left=generateRandomNumber(0,1);
			this.set1.add(tempset);
			this.set2.add(tempset);
			this.set3.add(tempset);
			this.set4.add(tempset);
		}
		
	}
	/**
	*The generateRiValue method helps to generate the Ri values for the corresponding Li values.
	 */
	public void generateRiValues() {
		for(int i=0;i<this.set1.size();i++) {
			Set set=new Set();
			set.left=this.set1.get(i).left;
		    double temp=(double)(this.set1.get(i).left+Vi[i][0]);
			set.right=Math.min(temp,1);
			this.set1.set(i, set);
		}
		
		for(int i=0;i<this.set2.size();i++) {
			Set set=new Set();
			set.left=this.set2.get(i).left;
			double temp=(double)(this.set2.get(i).left+Vi[i][1]);
			set.right=minValue(temp,1);
			this.set2.set(i, set);
		}
		for(int i=0;i<this.set3.size();i++) {
			Set set=new Set();
			set.left=this.set3.get(i).left;
			double temp=(double)(this.set3.get(i).left+Vi[i][2]);
			set.right=minValue(temp,1);
			this.set3.set(i, set);
		}
		for(int i=0;i<this.set4.size();i++) {
			Set set=new Set();
			set.left=this.set4.get(i).left;
			double temp=(double)(this.set4.get(i).left+Vi[i][3]);
			set.right=minValue(temp,1);
			this.set4.set(i, set);
		}
	}
	/**
	* The minValue method is used calculate the minimum value of(li+vi and 1)
	 */
	public double minValue(double sum, double a) {
		if(a<sum) {
			return a;
		}
		else {
			return sum;
		}
	}
	/**
	*The generateViValues method helps us to generate the random Vi values in between[0,t] for different set of t values.
	 */
	public void generateViValues() {
		for(int i=0;i<this.nOfIntervals;i++) {
			for(int j=0;j<4;j++) {
				this.Vi[i][j]=getRandomViValue(j);
			}
		}
		//System.out.println(Vi.length);
	}
	
	/**
	*The generateRandomViValues method helps us to generate the random Vi values in between[0,t] for different set of t values
	*where t value is used based on n-{1/n, 1/sqrt(n), 1/logn, 1/4}
	 */
	public double getRandomViValue(int n) {
		double temp=0.0000;
		if(n==0) {
			double temp1=((double)(1)/this.nOfIntervals);
			temp=generateRandomNumber(0,temp1);
		}
		else if(n==1) {
			double temp1=((double)1/(Math.sqrt(this.nOfIntervals)));
			temp=generateRandomNumber(0,temp1);
		}
		else if(n==2) {
			double temp1=((double)1/Math.log(this.nOfIntervals));
			temp=generateRandomNumber(0,temp1);
		}
		else if(n==3) {
			double temp1=((double)1/4);
			temp=generateRandomNumber(0,temp1);
		}
		return temp;
	}

	/**
	*The display function is used to print the generate vi, li, ri values for all intervals of each instance
	 */
	public void display() {
		for(int j=0;j<this.Vi[0].length;j++) {
			System.out.println(String.format("\nPrinting V%d", j+1));
			for(int i=0;i<this.Vi.length;i++) {
				System.out.printf(" %f ",Vi[i][j]);
			}
			System.out.println();
		}
		
		System.out.println("\nPrinting set 1");
		for(int i=0;i<this.set1.size();i++) {
			System.out.printf(" {%f,%f} ", this.set1.get(i).left,this.set1.get(i).right);
		}
		
		System.out.println("\nPrinting set 2");
		for(int i=0;i<this.set2.size();i++) {
			System.out.printf(" {%f,%f} ", this.set2.get(i).left,this.set2.get(i).right);
		}
		
		System.out.println("\nPrinting set 3");
		for(int i=0;i<this.set3.size();i++) {
			System.out.printf(" {%f,%f} ", this.set3.get(i).left,this.set3.get(i).right);
		}
		
		System.out.println("\nPrinting set 4");
		for(int i=0;i<this.set4.size();i++) {
			System.out.printf(" {%f,%f} ", this.set4.get(i).left,this.set4.get(i).right);
		}
	}
}
