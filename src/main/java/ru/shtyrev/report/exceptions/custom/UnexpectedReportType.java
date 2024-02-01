package ru.shtyrev.report.exceptions.custom;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnexpectedReportType extends RuntimeException {
    public UnexpectedReportType() {

    }

    public UnexpectedReportType(String message) {
        super(message);
    }

    public UnexpectedReportType(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedReportType(Throwable cause) {
        super(cause);
    }

    protected UnexpectedReportType(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
