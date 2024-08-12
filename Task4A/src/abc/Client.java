package abc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Enum for Log Levels
enum LogLevel {
    INFO, DEBUG, ERROR;
}

// Command Interface
interface Command {
    void execute(String message, LogLevel level);
}

// LogCommand Class implementing Command interface
class LogCommand implements Command {
    private LogHandler handler;

    public LogCommand(LogHandler handler) {
        this.handler = handler;
    }

    @Override
    public void execute(String message, LogLevel level) {
        handler.handle(message, level);
    }
}

// Abstract LogHandler Class
abstract class LogHandler {
    protected LogHandler nextHandler;

    public void setNextHandler(LogHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(String message, LogLevel level) {
        if (canHandle(level)) {
            logMessage(message);
        } else if (nextHandler != null) {
            nextHandler.handle(message, level);
        }
    }

    protected abstract boolean canHandle(LogLevel level);

    protected abstract void logMessage(String message);
}

// Concrete InfoHandler Class
class InfoHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.INFO;
    }

    @Override
    protected void logMessage(String message) {
        System.out.println("INFO: " + message);
    }
}

// Concrete DebugHandler Class
class DebugHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.DEBUG;
    }

    @Override
    protected void logMessage(String message) {
        System.out.println("DEBUG: " + message);
    }
}

// Concrete ErrorHandler Class
class ErrorHandler extends LogHandler {
    @Override
    protected boolean canHandle(LogLevel level) {
        return level == LogLevel.ERROR;
    }

    @Override
    protected void logMessage(String message) {
        System.out.println("ERROR: " + message);
    }
}

// Logger Class using Iterator Pattern
class Logger {
    private List<Command> commands = new ArrayList<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public void processCommands() {
        Iterator<Command> iterator = commands.iterator();
        while (iterator.hasNext()) {
            Command command = iterator.next();
            command.execute("Processing command", LogLevel.INFO); // Example message
        }
    }
}

// Client Class
public class Client {
    public static void main(String[] args) {
        // Create handlers
        LogHandler infoHandler = new InfoHandler();
        LogHandler debugHandler = new DebugHandler();
        LogHandler errorHandler = new ErrorHandler();

        // Set up the chain of responsibility
        infoHandler.setNextHandler(debugHandler);
        debugHandler.setNextHandler(errorHandler);

        // Create commands
        LogCommand infoCommand = new LogCommand(infoHandler);
        LogCommand debugCommand = new LogCommand(debugHandler);
        LogCommand errorCommand = new LogCommand(errorHandler);

        // Create logger and add commands
        Logger logger = new Logger();
        logger.addCommand(infoCommand);
        logger.addCommand(debugCommand);
        logger.addCommand(errorCommand);

        // Process commands
        logger.processCommands();

        // Example log messages
        infoCommand.execute("This is an info message.", LogLevel.INFO);
        debugCommand.execute("This is a debug message.", LogLevel.DEBUG);
        errorCommand.execute("This is an error message.", LogLevel.ERROR);
    }
}