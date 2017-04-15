package Logger;

import org.apache.log4j.Logger;

/**
 * Created by Vladislav on 4/15/2017.
 */
public class LoggerApp {
    private static final Logger log = Logger.getLogger(LoggerApp.class);

    private LoggerApp(){}

    /**
     * @return Logger object
     */
    public static Logger getLogger(){
        return log;
    }
}
