package com.knossys.rnd;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.knossys.rnd.lang.string.KSimpleFeatureMaker;

/**
 * @author vvelsen
 */
public class KStrandsProcessor {

	private static Logger M_log = Logger.getLogger(KStrandsProcessor.class.getName());	
	
	private KSimpleFeatureMaker featureMaker=new KSimpleFeatureMaker ();
	
	private ArrayList <List<String>>history=new ArrayList<List<String>>();
	
	/**
	 * @param s
	 */
	public void process(String s) {
    M_log.info("process ()");
    
		List<String> tokens=featureMaker.unigramTokenize(s, true);
		
    featureMaker.show(tokens);
		
		history.add(tokens);
	}
}
