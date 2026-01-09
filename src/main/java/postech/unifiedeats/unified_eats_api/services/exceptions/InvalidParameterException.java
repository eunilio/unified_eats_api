package postech.unifiedeats.unified_eats_api.services.exceptions;

public class InvalidParameterException extends RuntimeException {
    private final String paramName;

    public InvalidParameterException(String paramName, String message) {
        super(message);
        this.paramName = paramName;
    }

    public String getParamName() {
        return paramName;
    }
}
