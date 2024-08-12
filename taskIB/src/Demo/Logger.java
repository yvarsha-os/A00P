package demo;

public class Logger {
    private static Logger instance;

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) {
            synchronized (Logger.class) {
                if (instance == null) {
                    instance = new Logger();
                }
            }
        }
        return instance;
    }

    public void log(String message) {
        System.out.println("LOG: " + message);
    }

    public static void main(String[] args) {
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();
        System.out.println("Logger1 hashcode: " + logger1.hashCode());
        System.out.println("Logger2 hashcode: " + logger2.hashCode());
        logger1.log("This is a log message.");
        logger2.log("This is another log message.");
    }
}
