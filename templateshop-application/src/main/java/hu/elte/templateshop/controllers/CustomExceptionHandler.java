package hu.elte.templateshop.controllers;

import hu.elte.templateshop.exceptions.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.io.StringWriter;

@ControllerAdvice
public class CustomExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    public String handleBusinessException(BusinessException ex, Model model) {
        String classNameWhereExceptionOccurred = ex.getStackTrace()[0].getClassName();
        int lineNumber = ex.getStackTrace()[0].getLineNumber();
        LOGGER.error("BusinessException in file:[" + classNameWhereExceptionOccurred + "]" + " at line: " + lineNumber);

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        LOGGER.error("\nSTACKTRACE:\n" + stacktrace);

        model.addAttribute("errorMsg", ex.getErrorMsg());
        return "shared/error";
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(code = HttpStatus.BAD_GATEWAY)
    public String handleRuntimeException(RuntimeException ex, Model model) {
        String classNameWhereExceptionOccurred = ex.getStackTrace()[0].getClassName();
        int lineNumber = ex.getStackTrace()[0].getLineNumber();
        LOGGER.error("RuntimeException in file:[" + classNameWhereExceptionOccurred + "]" + " at line: " + lineNumber);

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();
        LOGGER.error("\nSTACKTRACE:\n" + stacktrace);

        model.addAttribute("errorMsg", "An unexpected exception occurred during runtime.");
        return "shared/error";
    }
}
