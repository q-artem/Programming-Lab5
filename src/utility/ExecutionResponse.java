package utility;

import models.HumanBeing;

public class ExecutionResponse {
    private final boolean exitCode;
    private final String message;
    private final Object responseObj;

    public ExecutionResponse(boolean code, String s, Object obj) {
        exitCode = code;
        message = s;
        responseObj = obj;
    }

    public ExecutionResponse(String s, Object obj) {
        this(true, s, obj);
    }

    public boolean getExitCode() {
        return exitCode;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return String.valueOf(exitCode) + ";" + message + ";" + (responseObj == null ? "null" : responseObj.toString());
    }

}