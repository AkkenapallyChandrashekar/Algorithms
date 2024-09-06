/**
 * 
 */



import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;



/**
 * @author MYPC
 *
 */




public class Main extends Application {

	/**
	 * @param args
	 */
	
	/**
	The output ArrayList
	 */
static ArrayList<Set> output= new ArrayList<Set>();
static ArrayList<Set> output1= new ArrayList<Set>();
static ArrayList<Set> output2= new ArrayList<Set>();
static ArrayList<Set> output3= new ArrayList<Set>();
static ArrayList<Set> output4= new ArrayList<Set>();
static ArrayList<int[]> finalOutput = new ArrayList<int[]>();
	
	/**
	The below method is used to plot the the Line Chart of Maximum Independent Sets for each instance. 
	The x axis is the number of intervals and y axis is the MIS count.
	 */
	 @Override 
    public void start(Stage stage) throws Exception {

		stage.setTitle("Line Chart - Maximum Independent Set");
        final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Number of intervals");
		yAxis.setLabel("MIS Size of each interval");
        final LineChart<Number,Number> lineChart = 
                new LineChart<Number,Number>(xAxis,yAxis);
       
        lineChart.setTitle("t-Random MIS");
		XYChart.Series series1 = new XYChart.Series<Number, Number>();
        series1.setName("Random set -1");
        XYChart.Series series2 = new XYChart.Series<Number, Number>();
        series2.setName("Random set -2");
        XYChart.Series series3 = new XYChart.Series<Number, Number>();
        series3.setName("Random set -3");
        XYChart.Series series4 = new XYChart.Series<Number, Number>();
        series4.setName("Random set -4");

		for(int i=0;i<finalOutput.size();i++){
			series1.getData().add(new XYChart.Data(finalOutput.get(i)[0], finalOutput.get(i)[1]));
			series2.getData().add(new XYChart.Data(finalOutput.get(i)[0], finalOutput.get(i)[2]));
			series3.getData().add(new XYChart.Data(finalOutput.get(i)[0], finalOutput.get(i)[3]));
			series4.getData().add(new XYChart.Data(finalOutput.get(i)[0], finalOutput.get(i)[4]));
		}

		lineChart.getData().add(series1);
        lineChart.getData().add(series2);
        lineChart.getData().add(series3);
        lineChart.getData().add(series4);

		Scene scene  = new Scene(lineChart,800,600);     
        stage.setScene(scene);
        stage.show();
	}

	/**
	The MIS method implements the MIS algorithm by taking input as random set. 
	 */
	public static double MIS(ArrayList<Set> set) {
		MaximumIndepentSet MISObj= new MaximumIndepentSet();
		ArrayList<Set> sCrossUsPositive= new ArrayList<Set>();
		Set subSet= new Set();
		Set outPut= new Set();
		Double x=0.00;
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
				//System.out.println(String.format("The Output is {%.2f,%.2f}",outPut.left,outPut.right));
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

	/**
	* This method displays the MIS of All intervals of All instances.
	 */
	public static void displayMISOfAllIntervalsOfAllInstance(){
		
		System.out.println("Printing output1");
	    for(int i=0;i<output1.size();i++) {
	    	System.out.println(String.format("{%f,%f}", output1.get(i).left,output1.get(i).right));
	    }
	    System.out.println("Printing output2");
	    for(int i=0;i<output2.size();i++) {
	    	System.out.println(String.format("{%f,%f}", output2.get(i).left,output2.get(i).right));
	    }
	    System.out.println("Printing output3");
	    for(int i=0;i<output3.size();i++) {
	    	System.out.println(String.format("{%f,%f}", output3.get(i).left,output3.get(i).right));
	    }
	    System.out.println("Printing output4");
	    for(int i=0;i<output4.size();i++) {
	    	System.out.println(String.format("{%f,%f}", output4.get(i).left,output4.get(i).right));
	    }

	}
	

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Double x=0.00;
		TRandom trandom=new TRandom();


		trandom.readNumberOfInstances();
		for(int i=0;i<trandom.nOfInstances;i++){
			int[] tempout= new int[5];
			int interval=0;
			interval=trandom.generateRandomNumberOfIntervals();
			tempout[0]=interval;
		    trandom.generateLiValues();
		    trandom.generateViValues();
		    trandom.generateRiValues();
			x=MIS(trandom.set1);
			output1.addAll(output);
			tempout[1]=output.size();
			output.clear();
			x=0.000;
			x=MIS(trandom.set2);
			output2.addAll(output);
			tempout[2]=output.size();
			output.clear();
			x=0.000;
			x=MIS(trandom.set3);
			output3.addAll(output);
			tempout[3]=output.size();
			output.clear();
			x=0.000;
			x=MIS(trandom.set4);
			output4.addAll(output);
			tempout[4]=output.size();
			output.clear();
			finalOutput.add(tempout);
		}
		
		displayMISOfAllIntervalsOfAllInstance();
	    
		launch(args);
		

	}

}
