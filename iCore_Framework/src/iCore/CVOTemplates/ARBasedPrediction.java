package iCore.CVOTemplates;
import java.io.*;


/**
 * The Class ARBasedPrediction.
 * 
 * @author Chayan, Akshay, TUDelft
 * @version 1.0
 * @since 01.11.2014 
 *
 */
public class ARBasedPrediction {
	
	/** The ar order. */
	int arOrder;
	
	/** The ar coefs. */
	double arCoefs[];
	
	/**
	 * Instantiates a new AR based prediction.
	 */
	ARBasedPrediction() {
		this.arOrder = 5;
	}

	/**
	 * Instantiates a new AR based prediction.
	 *
	 * @param arOrder the ar order
	 */
	ARBasedPrediction(int arOrder) {
		this.arOrder = arOrder;
	}

	/*
	 * create AR-based prediction model coefficients using training data
	 */
	/**
	 * Creates the ar prediction model.
	 *
	 * @param trainingData the training data
	 * @throws MatrixOperationsException the matrix operations exception
	 */
	public void createARPredictionModel(double trainingData[]) throws MatrixOperationsException {
		int tDataLen = trainingData.length;
		int nrows = tDataLen - arOrder;
		int ncols = arOrder;
		if(nrows <= ncols)
			throw new MatrixOperationsException("Training Data Size is too small.");		
		
		Matrix A = new Matrix(nrows, ncols);
		double b[] = new double[nrows];
		for (int i=0; i<nrows; i++) {
	        for (int j=0; j<ncols; j++) {
	        	A.setValueAt(i, j, trainingData[i+j]);
	        }
	        b[i] = trainingData[i+arOrder];
        }
		
		arCoefs = new MatrixOperations().minNormOverDetSystem(A, b);
	}
	
	/*
	 * predict single value using AR-model coefficients 
	 */
	/**
	 * Gets the single prediction.
	 *
	 * @param pastData the past data
	 * @return the single prediction
	 * @throws MatrixOperationsException the matrix operations exception
	 */
	public double getSinglePrediction(double pastData[]) throws MatrixOperationsException {
		int dataLen = pastData.length;
		
		if(dataLen != arOrder)
			throw new MatrixOperationsException("Not enough past data. Provide " + arOrder + " data points.");		
		
		double pData = 0;
		for (int i=0; i<arOrder; i++) {
	        pData += pastData[dataLen-1-i]*arCoefs[i];
        }
		pData = Math.round(pData);
		return pData;
	}
	
	/*
	 * predict multiple values blindly using AR-model coefficients 
	 */
	/**
	 * Gets the multiple prediction.
	 *
	 * @param pastData the past data
	 * @param npred the npred
	 * @return the multiple prediction
	 * @throws MatrixOperationsException the matrix operations exception
	 */
	public double[] getMultiplePrediction(double pastData[], int npred) throws MatrixOperationsException {
		int dataLen = pastData.length;
		
		double pData[] = new double[npred];
		for (int i=0; i<npred; i++) {
	        pData[i] = Math.round(getSinglePrediction(pastData));
			
	        for(int j=0; j<dataLen-1; j++) {
	        	pastData[j+1] = pastData[j];
	        }
	        pastData[dataLen-1] = pData[i];
        }
		return pData;
	}
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		int dataLen = 3600;
		double trainingData[] = new double[dataLen];
		double testData[] = new double[dataLen];
		int arOrder = 5;
		double pData;
		
		File file;
		
	    try {
	    	/* Read data from the file */
	    	file = new File("loc_data.txt");
			FileReader inputFil = new FileReader(file);
			BufferedReader in = new BufferedReader(inputFil);
	         
	         for(int i=0; i<dataLen; i++) {
	        	 String s =in.readLine();
	        	 trainingData[i] = Integer.parseInt(s);
	         }
	         for(int i=0; i<dataLen; i++) {
	        	 String s =in.readLine();
	        	 testData[i] = Integer.parseInt(s);
	         }
	         in.close();
	         
	         
	         /* test AR model based prediction */
	         ARBasedPrediction arPred = new ARBasedPrediction(arOrder);
			arPred.createARPredictionModel(trainingData);
			
			int matching=0;
			double pastData[] = new double[arOrder];
			for(int i=0; i<dataLen-arOrder; i++) {
				for(int j=0; j<arOrder; j++) {
					pastData[j] = testData[i+j];
				}
				
				pData = arPred.getSinglePrediction(pastData);
				if(testData[i] == pData)
					matching += 1;
			}
			System.out.println(matching);
		        
	         
	     } catch (FileNotFoundException e) {
	    	 e.printStackTrace();
		 } catch (IOException e) {
			e.printStackTrace();
		 } catch (MatrixOperationsException e) {
			e.printStackTrace();
		 }
	}
}
