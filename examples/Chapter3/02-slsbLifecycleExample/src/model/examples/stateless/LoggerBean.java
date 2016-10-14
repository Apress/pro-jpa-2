package examples.stateless;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

@Stateless
public class LoggerBean implements Logger {
    private java.util.logging.Logger logger;

    @PostConstruct
    public void init() {
        logger = java.util.logging.Logger.getLogger("notification");
    }

    public void logMessage(String message) {
        logger.info(message);
    }
}

