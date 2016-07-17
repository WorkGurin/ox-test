package com.ox.exception;

public class InquiryNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -1226439803994500725L;

    public InquiryNotFoundException(String msg) {
        super(msg);
    }

}
