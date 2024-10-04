package ph.edu.cdsga.sms.ums.controller;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.HttpServerErrorException;
import ph.edu.cdsga.sms.ums.common.response.FailedResponse;
import ph.edu.cdsga.sms.ums.common.response.ResponsePayload;
import ph.edu.cdsga.sms.ums.enums.ErrorResponse;
import ph.edu.cdsga.sms.ums.exception.AuthenticationException;
import ph.edu.cdsga.sms.ums.exception.ServiceException;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String appVersion;

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public FailedResponse any() {
        return this.errorResponse(ErrorResponse.INTERNAL_SERVER_ERROR.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public FailedResponse uppException(Exception e) {
        return any();
    }

    @ExceptionHandler(HttpServerErrorException.class)
    @ResponseBody
    public FailedResponse httpServerErrorException(HttpServerErrorException e) {
        return this.errorResponse(ErrorResponse.INTERNAL_SERVER_ERROR.getCode(),  e.getMessage(), e.getStatusCode());
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseBody
    public FailedResponse noSuchElement() {
        return this.errorResponse(ErrorResponse.NOT_FOUND.getCode(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public FailedResponse dataIntegrityViolation() {
        return this.errorResponse(ErrorResponse.INTERNAL_SERVER_ERROR.getCode(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public FailedResponse numberFormat() {
        return this.errorResponse(ErrorResponse.BAD_REQUEST.getCode(), HttpStatus.BAD_REQUEST);
    }

    protected FailedResponse errorResponse(String errorCode, HttpStatus status) {
        String errStatus = ErrorResponse.getMessageByCode(errorCode);
        String statusDesc = !ObjectUtils.isEmpty(errStatus)  ? errStatus : status.getReasonPhrase();
        ResponsePayload responsePayload = new ResponsePayload();
        responsePayload.setStatusCode(errorCode);
        responsePayload.setStatusDesc(statusDesc);
        return new FailedResponse(responsePayload, status.value(), statusDesc, appName, appVersion);
    }

    @ExceptionHandler(MismatchedInputException.class)
    @ResponseBody
    public FailedResponse mismatchedInputException(MismatchedInputException e) {
        return this.errorResponse(ErrorResponse.BAD_REQUEST.getCode(), e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public FailedResponse authenticationException(AuthenticationException e) {
        return this.checkStatus(e.getStatus(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public FailedResponse serviceExceptionException(ServiceException e) {
        return this.checkStatus(e.getStatus(), e.getMessage());
    }

    private FailedResponse checkStatus(int code, String msg){
        if(code == 400) {
            return this.errorResponse(ErrorResponse.BAD_REQUEST.getCode(), msg, HttpStatus.BAD_REQUEST);
        }
        if(code == 403) {
            return this.errorResponse(ErrorResponse.FORBIDDEN.getCode(), msg, HttpStatus.FORBIDDEN);
        }
        if(code == 404) {
            return this.errorResponse(ErrorResponse.NOT_FOUND.getCode(), msg, HttpStatus.NOT_FOUND);
        }
        if(code == 500) {
            return this.errorResponse(ErrorResponse.INTERNAL_SERVER_ERROR.getCode(), msg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return any();
    }

    protected FailedResponse errorResponse(String errorCode, String errorMessage, HttpStatus status) {
        String errStatus = ErrorResponse.getMessageByCode(errorCode);
        ResponsePayload responsePayload = new ResponsePayload();
        responsePayload.setStatusCode(errorCode);
        responsePayload.setStatusDesc(!ObjectUtils.isEmpty(errStatus)  ? errStatus : status.getReasonPhrase());
        return new FailedResponse(responsePayload, status.value(), errorMessage, appName, appVersion);
    }

}
