package ru.shtyrev.report.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.shtyrev.report.exceptions.custom.*;

@ControllerAdvice
public class ReportExceptionHandler {
    @ExceptionHandler(value = ReportNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleReportNotFoundException(ReportNotFoundException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = ReportAlreadySolvedException.class)
    public ResponseEntity<ExceptionResponse> handleReportNotFoundException(ReportAlreadySolvedException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ReportAlreadyStartedException.class)
    public ResponseEntity<ExceptionResponse> handleReportNotFoundException(ReportAlreadyStartedException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ReportNotStartedException.class)
    public ResponseEntity<ExceptionResponse> handleReportNotFoundException(ReportNotStartedException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnexpectedReportType.class)
    public ResponseEntity<ExceptionResponse> handleUnexpectedReportType(UnexpectedReportType e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}
