package ru.shtyrev.report.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ReportNotStartedException extends RuntimeException {
    public ReportNotStartedException() {
        super();
    }

    public ReportNotStartedException(String message) {
        super(message);
    }

    public ReportNotStartedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReportNotStartedException(Throwable cause) {
        super(cause);
    }

    protected ReportNotStartedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
