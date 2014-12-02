package iCore.CVOTemplates;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.SMOreg;
import weka.classifiers.timeseries.WekaForecaster;
import weka.core.Instances;


/**
 * The Class TimeForecast
 * 
 *  @author Swaytha Sasidharan
 *  @version 1.0
 *  @since 01.11.2014 
 */
public class TimeForecast implements Predict_CVO
{



	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.Predict_CVO#Execute()
	 */
	@Override
	public List<Double> Execute() {
		// TODO Auto-generated method stub
		List<Double> classifier_output = null;

		String filepath = weka.core.WekaPackageManager.PACKAGES_DIR.toString()
				+ File.separator + "timeseriesForecasting" + File.separator + "sample-data"
				+ File.separator + "test_location.arff";

		try {
			Instances data = DataAnalysis(filepath);
			classifier_output = Train(data);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return classifier_output;

	}

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.Predict_CVO#DataAnalysis(java.lang.String)
	 */
	@Override
	public Instances DataAnalysis(String filepath) throws IOException {
		// TODO Auto-generated method stub
		Instances data = new Instances(new BufferedReader(new FileReader(filepath)));

		return data;

	}

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.Predict_CVO#AlgorithmSpecifics(java.lang.String, java.util.HashMap)
	 */
	@Override
	public Object AlgorithmSpecifics(String Algorithm, HashMap algoParams) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see iCore.CVOTemplates.Predict_CVO#Train(weka.core.Instances)
	 */
	public List<Double> Train(Instances data) {

		try {

			WekaForecaster forecaster = new WekaForecaster();
			// set the targets to forecast
			forecaster.setFieldsToForecast("Location");

			forecaster.setBaseForecaster(new SMOreg());

			forecaster.getTSLagMaker().setTimeStampField("Date"); // date time stamp
			forecaster.getTSLagMaker().setMinLag(1);
			forecaster.getTSLagMaker().setMaxLag(4); // monthly data

			// add a month of the year indicator field
			forecaster.getTSLagMaker().setAddMonthOfYear(true);

			// add a quarter of the year indicator field
			forecaster.getTSLagMaker().setAddQuarterOfYear(true);

			// build the model
			forecaster.buildForecaster(data, System.out);

			forecaster.primeForecaster(data);

			// forecast for 12 units (months) beyond the end of the training data


			java.util.List<java.util.List<NumericPrediction>> forecast = forecaster.forecast(4, System.out);

			List<Double> output = new ArrayList<Double>();
	
			double temp2;

			for (int i = 0; i < 4; i++) {
				java.util.List<NumericPrediction> predsAtStep = forecast.get(i);
				for (int j = 0; j < 1; j++) {
					NumericPrediction predForTarget = predsAtStep.get(j);
	
					temp2 = Math.round(predForTarget.predicted());
					output.add(temp2);				
				}

			}

			return output;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
//
//	@Override
//	public void Test() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void Save() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void Deploy() {
//		// TODO Auto-generated method stub
//
//	}
//
//	@Override
//	public void Update() {
//		// TODO Auto-generated method stub
//
//	}

}
