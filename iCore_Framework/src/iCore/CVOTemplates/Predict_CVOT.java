package iCore.CVOTemplates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


// TODO: Auto-generated Javadoc
/**
 * The Class Predict_CVOT.
 *
 * @author: Swaytha Sasidharan
 * @version: 1.0
 * @since: 01.11.2014
 */
public class Predict_CVOT implements CVO_Template {
	
	/** The coord. */
	static private HashMap<String, Object> coord = new HashMap<String, Object>();

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#getName()
	 */
	@Override
	public String getName() {
		String cvo_name = "Predict";
		return cvo_name;
	}

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.CVO_Template#execute(java.lang.String)
	 */
	@Override
	public HashMap<String, Object> execute(String VO) {

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
				{
					matching += 1;
					coord.put("x", pData);
					coord.put("y",0);
				}
			}
			System.out.println(matching);


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MatrixOperationsException e) {
			e.printStackTrace();
		}



		return coord;	

	}



}
