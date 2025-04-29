package utility;

import models.HumanBeing;

public class ExecutionResponse {
    private final boolean exitCode;
    private final String message;

    public ExecutionResponse(boolean code, String s) {
        exitCode = code;
        message = s;
    }

    public ExecutionResponse(String s) {
        this(true, s);
    }

    public boolean getExitCode() {
        return exitCode;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return String.valueOf(exitCode) + ";" + message;
    }

}