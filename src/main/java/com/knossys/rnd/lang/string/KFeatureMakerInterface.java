package com.knossys.rnd.lang.string;

import java.util.List;

public interface KFeatureMakerInterface {

	/**
	 *
	 */
	public List<String> unigramTokenize(String aSource, Boolean caseFold);

	/**
	 *
	 */
	public List<String> unigramTokenize(List<String> oldTokens, String aSource, Boolean caseFold);
}
