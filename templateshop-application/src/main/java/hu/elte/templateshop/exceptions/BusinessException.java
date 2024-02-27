package hu.elte.templateshop.exceptions;

public class BusinessException extends RuntimeException {
    private final String errorMsg;

    public BusinessException(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
