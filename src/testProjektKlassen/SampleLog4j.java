package testProjektKlassen;

import org.apache.log4j.Logger;

public class SampleLog4j {

	private static final Logger log = Logger.getLogger(SampleLog4j.class);

	public static void main(String[] args) {

		log.info("Test Message");
		
		// not shown because of rootLogger settings set to debug and trace < debug so theres no output
		log.trace("Trace Test Message");
		
		
		// example of exception logging
		try {
			throw new Exception("New Exception");
		} catch (Exception e) {
			log.error(e);
		}

	}

}
