package com.knossys.rnd.lang.string;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author vvelsen
 */
public class KSimpleFeatureMaker implements KFeatureMakerInterface {
	
	private static Logger M_log = Logger.getLogger(KSimpleFeatureMaker.class.getName());

	private ArrayList<KFilterBase> filters=new ArrayList<KFilterBase>();
	
	/**
	 * @return
	 */
	public ArrayList<KFilterBase> getFilters () {
		return (filters);
	}

	/**
	 * @param aSource
	 * @return
	 */
	private String runExpandOnPunctuation(String aSource) {
    String formatted1=aSource.replace(".", " . ");
    
    String formatted2=formatted1.replace("?", " ? ");
    
    String formatted3=formatted2.replace("!", " ! ");
    
    String formatted4=formatted3.replace(",", " , ");
    
    //M_log.info("Expanded: " + formatted4);
    
		return formatted4;
	}	
	
	/**
	 *
	 */
	private Boolean runExclusionFilters(String aSource) {
		// M_log.info ("runExclusionFilters ()");

		ArrayList<KFilterBase> fList = getFilters();
		if (fList.size() == 0)
			return (true);

		for (int j = 0; j < fList.size(); j++) {
			KFilterBase filter = fList.get(j);

			if (filter.getNoMore() == true) {
				return (false);
			}

			if (filter.evaluate(aSource) == false)
				return (false);
		}

		return (true);
	}

	/**
	 *
	 */
	private String runCleanFilters(String aSource) {
		// M_log.info ("runCleanFilters ()");

		ArrayList<KFilterBase> fList = getFilters();

		if (fList.size() == 0) {
			return (aSource);
		}

		for (int j = 0; j < fList.size(); j++) {
			KFilterBase filter = fList.get(j);

			aSource = filter.clean(aSource);
			if (aSource == null) // One of the filters detected that this entire term is invalid
				return (null);
		}

		return (aSource);
	}

	/**
	 *
	 */
	private List<String> runCheckFilters(List<String> rawTokens) {
		List<String> out = new ArrayList<String>();

		ArrayList<KFilterBase> fList = getFilters();

		if (fList.size() == 0) {
			return (rawTokens);
		}

		for (int j = 0; j < fList.size(); j++) {
			KFilterBase filter = fList.get(j);

			for (int t = 0; t < rawTokens.size(); t++) {
				String token = rawTokens.get(t);
				if (filter.check(token) == true)// In other words: keep
				{
					// M_log.info ("Keeping: " + token);
					out.add(new String(token));
				}
				// else
				// M_log.info ("Throwing out: " + token);
			}
		}

		return (out);
	}

	/**
	 *
	 */
	public List<String> unigramTokenizeBasic(String aSource) {
		// M_log.info ("unigramTokenizeBasic (String)");

		String cleaned = aSource;

		ArrayList<String> out = new ArrayList<String>();

		if (aSource == null || (aSource.trim().length() == 0))
			return out;

		if (cleaned != null) {
			String[] split = cleaned.split("\\s+");

			for (int i = 0; i < split.length; i++) {

				out.add(split[i]);
			}
		}

		return (out);
	}

	/**
	 *
	 */
	public List<String> unigramTokenizeOnCharacter(String aSource, String aCharacter) {
		// M_log.info ("unigramTokenizeOnCharacter (String,String)");

		String cleaned = aSource;

		ArrayList<String> out = new ArrayList<String>();

		if (aSource == null || (aSource.trim().length() == 0))
			return out;

		String[] split = cleaned.split("\\" + aCharacter);

		for (int i = 0; i < split.length; i++) {
			out.add(split[i]);
		}

		return (out);
	}

	/**
	 *
	 */
	public List<String> unigramTokenize(String aSource, Boolean caseFold) {
		// M_log.info ("unigramTokenize (String)");

		String cleaned = aSource;

		ArrayList<String> out = new ArrayList<String>();

		if (aSource == null || (aSource.trim().length() == 0))
			return out;
				
		if (runExclusionFilters(aSource) == false)
			return out;
		
		String expanded=runExpandOnPunctuation (aSource);

		cleaned = runCleanFilters(expanded);
		if (cleaned != null) {
			cleaned = cleaned.trim().toLowerCase();

			String[] split = cleaned.split("\\s+");

			for (int i = 0; i < split.length; i++) {
				if (caseFold == true)
					out.add(split[i].toLowerCase());
				else
					out.add(split[i]);
			}
		}

		return (runCheckFilters(out));
	}

	/**
	 *
	 */
	public List<String> unigramTokenize(List<String> oldTokens, String aSource, Boolean caseFold) {
		// M_log.info ("unigramTokenize (List<String>,String)");

		String cleaned = aSource;

		if (oldTokens == null) {
			M_log.info("Internal error: null token list!");
			return (null);
		}

		if ((aSource) == null || (aSource.trim().length() == 0)) {
			// M_log.info ("Found empty string!");
			return oldTokens;
		}

		if (runExclusionFilters(aSource) == false)
			return oldTokens;

		cleaned = runCleanFilters(aSource);

		if (cleaned != null) {
			cleaned = cleaned.trim().toLowerCase();

			String[] split = cleaned.split("\\s+");

			// M_log.info ("Split into "+ split.length + " raw tokens");

			for (int i = 0; i < split.length; i++) {
				if (caseFold == true)
					oldTokens.add(split[i].toLowerCase());
				else
					oldTokens.add(split[i]);
			}
		}

		return (runCheckFilters(oldTokens));
	}

	/**
	 * @param tokens
	 */
	public void show(List<String> tokens) {
		for (int i=0;i<tokens.size();i++) {
			String aToken=tokens.get(i);
			M_log.info("Token ["+i+"]: " + aToken);
		}		
	}
}
