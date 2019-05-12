package com.knossys.rnd;

import java.util.logging.Logger;
import java.util.Scanner;

/**
 * @author vvelsen
 */
public class KStrandsConsole {

	private static Logger M_log = Logger.getLogger(KStrandsConsole.class.getName());

	private KStrandsProcessor processor = new KStrandsProcessor();

	/**
	 * 
	 */
	private void run() {
		Scanner in = new Scanner(System.in);

		while (true) {
			M_log.info("S >");
			String s = in.nextLine();
			M_log.info("You entered string: " + s);

			if (s.toLowerCase().indexOf("quit") != -1) {
				M_log.info("Exiting ...");
				in.close();
				System.exit(0);
			}

			processor.process(s);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		KStrandsConsole strandsConsole = new KStrandsConsole();
		strandsConsole.run();
	}
}
