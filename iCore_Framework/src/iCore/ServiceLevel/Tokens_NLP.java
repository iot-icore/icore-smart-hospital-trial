package iCore.ServiceLevel;

import java.io.*;
import java.net.URL;
import java.net.URLClassLoader;

import opennlp.tools.tokenize.*;
import opennlp.tools.util.*;

/**
 * Tokenizer - split a sentence into tokens.
 *
 * @author erelsgl
 * @see http://incubator.apache.org/opennlp/documentation/manual/opennlp.html#tools.tokenizer
 * @date 13/07/2011
 */
@SuppressWarnings("unused")
public class Tokens_NLP {
	
	/**
	 * Train.
	 *
	 * @param trainfilename the trainfilename
	 * @param modelfilename the modelfilename
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("deprecation")
	private static void train(String trainfilename, String modelfilename) throws IOException {
		System.out.println("Training");
		ObjectStream<String> lineStream = new PlainTextByLineStream(new FileInputStream(trainfilename), "UTF-8");
		ObjectStream<TokenSample> sampleStream = new TokenSampleStream(lineStream);

		TokenizerModel model = TokenizerME.train("en",sampleStream, true, /*cutoff*/5, /*iterations*/100);
		OutputStream modelOut = new BufferedOutputStream(new FileOutputStream(modelfilename));
		model.serialize(modelOut);
	}
	
	/**
	 * Test tokenize.
	 *
	 * @param tokenizer the tokenizer
	 * @param sentence the sentence
	 * @param detokenizer the detokenizer
	 */
	private static void testTokenize(Tokenizer tokenizer, String sentence, Detokenizer detokenizer) {
		String tokens[] = tokenizer.tokenize(sentence);
	}
	

	/**
	 * Test tokenize.
	 *
	 * @param tokenizer the tokenizer
	 * @param sentence the sentence
	 * @param detokenizer the detokenizer
	 * @return the string[]
	 */
	private static String[] testTokenize(TokenizerME tokenizer, String sentence, Detokenizer detokenizer) {
		String tokens[] = tokenizer.tokenize(sentence);
		double tokenProbs[] = tokenizer.getTokenProbabilities();
		return tokens;
	}
	
	/**
	 * Test tokenize3.
	 *
	 * @param tokenizer the tokenizer
	 * @param sentence the sentence
	 * @param detokenizer the detokenizer
	 * @return the string[]
	 */
	private static String[] testTokenize3(TokenizerME tokenizer, String sentence, Detokenizer detokenizer) {

		String[] tokens = testTokenize(tokenizer, sentence, detokenizer);
		return tokens;
	}
	
	/**
	 * Tokenize.
	 *
	 * @param modelfilename the modelfilename
	 * @param detokenizerfilename the detokenizerfilename
	 * @param SR the sr
	 * @return the string[]
	 * @throws InvalidFormatException the invalid format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private static String[] tokenize(String modelfilename, String detokenizerfilename,String SR) throws InvalidFormatException, IOException {
		InputStream modelIn = new FileInputStream(modelfilename);
		
		TokenizerModel model = new TokenizerModel(modelIn);
		TokenizerME tokenizer = new TokenizerME(model);
		
		InputStream detokenizerIn = new FileInputStream(detokenizerfilename);
		
		Detokenizer detokenizer = new DictionaryDetokenizer(new DetokenizationDictionary(detokenizerIn));
		String[] tokens =testTokenize3(tokenizer, SR, detokenizer);
	
		return  tokens;
	}

	/**
	 * Return tokens.
	 *
	 * @param SR the sr
	 * @return the string[]
	 * @throws InvalidFormatException the invalid format exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public String[] ReturnTokens(String SR) throws InvalidFormatException, IOException {
//		String[] tokens = tokenize("/home/swaytha/Desktop/JSP_Files/models/en-token.bin", "/home/swaytha/Desktop/JSP_Files/models/latin-detokenizer.xml", SR);
		String[] tokens = tokenize("C:/Users/ssasidharan/workspace/Jar_Creation/models/en-token.bin","C:/Users/ssasidharan/workspace/Jar_Creation/models/latin-detokenizer.xml",SR);
//		String[] tokens = tokenize("../Jar_Creation/models/en-token.bin","../Jar_Creation/models/latin-detokenizer.xml",SR);
		return tokens;
		
	}

}
