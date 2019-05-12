package com.knossys.rnd;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.knossys.rnd.lang.string.KSimpleFeatureMaker;
import com.knossys.rnd.strands.KStrand;

/**
 * @author vvelsen
 */
public class KStrandsProcessor {

	private static Logger M_log = Logger.getLogger(KStrandsProcessor.class.getName());	
	
	private KSimpleFeatureMaker featureMaker=new KSimpleFeatureMaker ();
	
	private ArrayList <List<String>>history=new ArrayList<List<String>>();

	private ArrayList<KStrand> strands=new ArrayList<KStrand>();
	
  private KStrandTracker strandTracker=new KStrandTracker ();
  
  private KStrandMatcher strandMatcher=new KStrandMatcher (strands);
	
	/**
	 * @param s
	 */
	public void process(String s) {
    M_log.info("process ()");
    
		List<String> tokens=featureMaker.unigramTokenize(s, true);
		
    featureMaker.show(tokens);
		
		history.add(tokens);
		
		KStrand match=strandMatcher.match(tokens);
		
		strandTracker.switchTo(match);
	}

	/**
	 * @return
	 */
	public ArrayList<KStrand> getStrands() {
		return strands;
	}
}
