package ru.shtyrev.report.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReportAlreadyStartedException extends RuntimeException {
    public ReportAlreadyStartedException() {
        super();
    }

    public ReportAlreadyStartedException(String message) {
        super(message);
    }

    public ReportAlreadyStartedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportAlreadyStartedException(Throwable cause) {
        super(cause);
    }

    protected ReportAlreadyStartedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
