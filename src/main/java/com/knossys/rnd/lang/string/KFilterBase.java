/** 
 * Author: Martin van Velsen <vvelsen@cs.cmu.edu>
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU Lesser General Public License as 
 *  published by the Free Software Foundation, either version 3 of the 
 *  License, or (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package com.knossys.rnd.lang.string;

import java.util.logging.Logger;

/**
 * @author vvelsen
 */
public class KFilterBase {
	
	private static Logger M_log = Logger.getLogger(KFilterBase.class.getName());

	private Boolean noMore = false;

	/**
	 *
	 */
	public Boolean evaluate(String aText) {
		M_log.info("evaluate (" + aText + ")");

		if (noMore == true)
			return (false);

		return (true);
	}

	/**
	 *
	 */
	public String clean(String aText) {
		M_log.info("clean (" + aText + ")");

		return (aText);
	}

	/**
	 *
	 */
	public Boolean check(String aText) {
		M_log.info("check (" + aText + ")");

		return (true);
	}

	/**
	 *
	 */
	public void reset() {
		noMore = false;
	}

	/**
	 *
	 */
	public Boolean getNoMore() {
		return noMore;
	}

	/**
	 *
	 */
	public void setNoMore(Boolean noMore) {
		this.noMore = noMore;
	}
}
