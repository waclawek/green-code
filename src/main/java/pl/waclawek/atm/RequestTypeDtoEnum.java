package pl.waclawek.atm;

import org.openapi.quarkus.atmservice_json.model.Task;


public enum RequestTypeDtoEnum {
    FAILURE_RESTART("FAILURE_RESTART", 1),
    PRIORITY("PRIORITY", 2 ),
    SIGNAL_LOW("SIGNAL_LOW", 3),
    STANDARD("STANDARD", 4);

    final int priority;
    final String requestType;

    RequestTypeDtoEnum(String requestType, int priorityValue) {
        this.requestType = requestType;
        this.priority = priorityValue;
    }

    public int getPriorityValue() {
        return priority;
    }

    public static RequestTypeDtoEnum mapFromExternal(Task.RequestTypeEnum requestType) {
        switch (requestType) {
            case FAILURE_RESTART -> {
                return FAILURE_RESTART;
            }
            case PRIORITY -> {
                return PRIORITY;
            }
            case SIGNAL_LOW -> {
                return SIGNAL_LOW;
            }
            default ->{
                return STANDARD;
            }
        }
    }
}
