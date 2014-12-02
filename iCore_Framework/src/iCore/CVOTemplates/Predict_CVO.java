package iCore.CVOTemplates;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import weka.core.Instances;

public interface Predict_CVO<T> {

		abstract List<Double> Execute();
		abstract T DataAnalysis(String filepath) throws IOException;
		abstract T AlgorithmSpecifics(String Algorithm, HashMap algoParams);
		abstract T Train(Instances data);
		
		/*To be added as seen fit */
//		abstract void Test();
//		abstract void Save();
//		abstract void Deploy();
//		abstract void Update();
		
//		public void Evaluate();

}
